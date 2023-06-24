package com.hello.enter.api.exception;

public abstract class SuperException extends RuntimeException {
    public SuperException(String message) {
        super(message);
    }

    public abstract int errorCode();

}
