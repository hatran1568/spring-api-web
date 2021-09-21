package com.example.web.exception;

public class RequestParamInvalidException extends RuntimeException {
    public RequestParamInvalidException(String message) {
        super(message);
    }
}
