package com.example.example130921.exception.handler;

import com.example.example130921.dto.response.ErrorResponse;
import com.example.example130921.exception.ConstraintViolationException;
import com.example.example130921.exception.RequestParamInvalidException;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
