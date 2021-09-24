package com.example.web.controller;

import com.example.web.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;



    @Test
    void getCustomers() {
    }

    @Test
    void getCustomerDetail() {
    }

    @Test
    void showFormAdd() {

    }

    @Test
    void addCustomer() {
    }

    @Test
    void showFormEdit() {
    }

    @Test
    void updateCustomer() {
    }

    @Test
    void deleteCustomer() {
    }
}