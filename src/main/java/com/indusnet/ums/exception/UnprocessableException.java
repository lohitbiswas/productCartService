package com.indusnet.ums.exception;

public class UnprocessableException extends RuntimeException{
    public UnprocessableException(String message) {
        super(message);
    }
}