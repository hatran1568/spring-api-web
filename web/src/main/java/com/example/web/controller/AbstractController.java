package com.example.web.controller;

import com.example.web.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public abstract class AbstractController<S> {
    @Autowired
    protected S service;

    /**
     * @param response Optional of response object
     * @param <T>      Class type
     * @return ResponseEntity
     */
    protected <T> ResponseEntity<T> response(Optional<T> response) {
        return new ResponseEntity<T>(response.orElseThrow(() -> {
            throw new ResourceNotFoundException();
        }), HttpStatus.OK);
    }
}
