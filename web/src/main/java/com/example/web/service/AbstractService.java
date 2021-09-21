package com.example.web.service;

import com.example.web.exchange.ApiExchangeService;
import com.example.web.utils.ObjectValidator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

public abstract class AbstractService {
    @Autowired
    protected Environment env;

    @Autowired
    protected ObjectValidator validator;

    @Autowired
    protected ApiExchangeService apiExchangeService;

    protected ObjectMapper objectMapper;

    protected String backApiUrl;

    @PostConstruct
    public void init() {
        objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        backApiUrl = env.getProperty("api.backend.url");
    }
}
