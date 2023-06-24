package com.hello.enter.api.controller;

import com.hello.enter.api.exception.SuperException;
import com.hello.enter.api.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice
@Slf4j
public class ExceptionController {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse exceptionHandler(MethodArgumentNotValidException e) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .code("400")
                .message("잘못된 요청입니다.")
                .errorMessage(new HashMap<>())
                .build();

        e.getFieldErrors()
                .forEach(ex -> errorResponse.addErrors(e.getStatusCode().toString(), ex.getDefaultMessage()));

        return errorResponse;
    }

    @ResponseBody
    @ExceptionHandler(SuperException.class)
    public ResponseEntity<ErrorResponse> errorResponse(SuperException e) {
        int errorCode = e.errorCode();

        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(e.getMessage())
                .code(String.valueOf(errorCode))

                .build();


        return ResponseEntity.status(e.errorCode())
                .body(errorResponse);
    }
}
