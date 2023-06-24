package com.hello.enter.api.exception;

public class MemberNotFound extends SuperException {

    private static final String MESSAGE = "등록된 정보가 없습니댜.";

    public MemberNotFound() {
        super(MESSAGE);
    }

    @Override
    public int errorCode() {
        return 404;
    }
}
