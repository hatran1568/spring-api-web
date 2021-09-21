package com.example.web.exception.handler;


import com.example.web.dto.response.ErrorResponse;
import com.example.web.exception.ConstraintViolationException;
import com.example.web.exception.RequestParamInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>(new ErrorResponse("E01", e.getMessage()), null, HttpStatus.FAILED_DEPENDENCY);
    }


    @ExceptionHandler({RequestParamInvalidException.class})
    public ResponseEntity<ErrorResponse> handleRequestParamInvalidException(RequestParamInvalidException e) {
        return new ResponseEntity<>(new ErrorResponse("E02", e.getMessage()), null, HttpStatus.BAD_REQUEST);
    }
}
