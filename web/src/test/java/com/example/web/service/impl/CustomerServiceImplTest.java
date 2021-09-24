package com.example.web.service.impl;

import com.example.web.dto.response.CustomerResponse;
import com.example.web.exchange.ApiExchangeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    private static final String BACK_API_URL = "http://localhost:8080";

    @Mock
    private ApiExchangeService apiExchangeService;

    @InjectMocks
    private CustomerServiceImpl service;

    private HttpServletRequest httpServletRequest;
    private CustomerResponse customer1;

    @BeforeEach
    void setUp() {
        httpServletRequest = new MockHttpServletRequest();
        customer1 = new CustomerResponse(1,"a","0123");
    }

    @Test
    void getCustomers() {
        Mockito.when(apiExchangeService.get(httpServletRequest,
                                            apiExchangeService.createURL(BACK_API_URL, "customers"),
                                            CustomerResponse[].class))
                .thenReturn(new CustomerResponse[]{customer1});

        Optional<List<CustomerResponse>> list = service.getCustomers(httpServletRequest);
        assertTrue(list.isPresent());
        assertEquals(list.get().size(), 1);
    }

    @Test
    void getById() {
        Mockito.when(apiExchangeService.get(httpServletRequest,
                                            apiExchangeService.createURL(BACK_API_URL, "customer/1"),
                                            CustomerResponse.class))
                .thenReturn(customer1);

        Optional<CustomerResponse> response = service.getById(httpServletRequest, "1");
        assertTrue(response.isPresent());
        assertEquals(response.get().getCustomerId(), 1);
    }
}