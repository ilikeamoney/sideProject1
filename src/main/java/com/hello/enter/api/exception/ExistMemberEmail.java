package com.hello.enter.api.exception;

public class ExistMemberEmail extends SuperException{

    private static final String MESSAGE = "이미 존재하는 이메일 입니다.";

    public ExistMemberEmail() {
        super(MESSAGE);
    }

    @Override
    public int errorCode() {
        return 409;
    }
}
