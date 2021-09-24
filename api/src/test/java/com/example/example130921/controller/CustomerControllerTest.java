package com.example.example130921.controller;

import com.example.example130921.dto.request.CustomerRequest;
import com.example.example130921.dto.response.CustomerResponse;
import com.example.example130921.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @Autowired
    private MockMvc mockMvc;

    private CustomerResponse customer1;
    private CustomerResponse customer2;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
        customer1 = new CustomerResponse(1,"a","0123");
        customer2 = new CustomerResponse(2,"b","0123");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetAllCustomers() throws Exception {
        Mockito.when(customerService.getCustomers()).thenReturn(Optional.of(Arrays.asList(customer1)));

        this.mockMvc.perform(get("/customers"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void testGetById() throws Exception{
        Mockito.when(customerService.getById(customer1.getCustomerId())).thenReturn(Optional.of(customer1));

        this.mockMvc.perform(get("/customers/1"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.customerId", is(1)))
                    .andExpect(jsonPath("$.customerName", is(customer1.getCustomerName())));
    }

    @Test
    void testGetByIdNotFound() throws Exception{
        Mockito.when(customerService.getById(1)).thenReturn(Optional.empty());

        this.mockMvc.perform(get("/customers/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testAdd() throws Exception{
        CustomerRequest request = new CustomerRequest("a", "0123");

        Mockito.when(customerService.add(request)).thenReturn(Optional.of(customer1));

        this.mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void updateById() {
    }

    @Test
    void deleteById() {
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}