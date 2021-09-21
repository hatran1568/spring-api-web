package com.example.example130921.exception;

public class RequestParamInvalidException extends RuntimeException {
    public RequestParamInvalidException(String message) {
        super(message);
    }
}
