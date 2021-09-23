package com.example.web.exception.handler;


import com.example.web.exception.ConstraintViolationException;
import com.example.web.exception.RequestParamInvalidException;
import com.example.web.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Tran Thi Nguyet Ha
 * @since 09/22/2021
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ConstraintViolationException.class})
    public ModelAndView handleConstraintViolationException(ConstraintViolationException e) {
        return new ModelAndView("redirect:/error");
    }


    @ExceptionHandler({RequestParamInvalidException.class})
    public ModelAndView handleRequestParamInvalidException(RequestParamInvalidException e) {
        return new ModelAndView("redirect:/error");
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ModelAndView handleResourceNotFoundException(ResourceNotFoundException e) {
        return new ModelAndView("redirect:/error/system-error");
    }

    @ExceptionHandler({Exception.class})
    public ModelAndView handleCommonException(Exception e) {
        e.printStackTrace();
        return new ModelAndView("redirect:/error/system-error");
    }
}
