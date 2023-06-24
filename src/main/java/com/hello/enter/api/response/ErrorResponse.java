package com.hello.enter.api.response;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
public record ErrorResponse(String code, String message, Map<String, String> errorMessage) {

    public void addErrors(String code, String message) {
        errorMessage.put(code, message);
    }
}
