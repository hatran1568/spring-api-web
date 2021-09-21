package com.example.example130921.exception;

public class ConstraintViolationException extends RuntimeException{
    public ConstraintViolationException(String message) {
        super(message);
    }
}
