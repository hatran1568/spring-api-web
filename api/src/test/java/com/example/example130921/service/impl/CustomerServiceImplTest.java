package com.example.example130921.service.impl;

import com.example.example130921.dao.entity.Customer;
import com.example.example130921.dao.repository.CustomerRepository;
import com.example.example130921.dto.response.CustomerResponse;
import com.example.example130921.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceImplTest {

    @MockBean
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testGetCustomers() {
        Customer customer1 = new Customer(1, "aaa", "012345", null, null, false);
        Customer customer2 = new Customer(2, "bbb", "012345", null, null, false);
        Optional<List<Customer>> expectedCustomerList = Optional.of(Arrays.asList(customer1,customer2));

        Mockito.doReturn(expectedCustomerList).when(customerRepository).getAllCustomers();

        Optional<List<CustomerResponse>> customers = customerService.getCustomers();

        assertEquals(customers.get().size(), expectedCustomerList.get().size());


    }

    @Test
    void testGetByIdSuccess() {
        Customer customer1 = new Customer(1, "aaa", "012345", null, null, false);
        Mockito.doReturn(Optional.of(customer1)).when(customerRepository).findById(1);

        Optional<CustomerResponse> customer = customerService.getById(1);

        assertTrue(customer.isPresent());
        assertSame(customer.get(), objectMapper.convertValue(customer1, CustomerResponse.class));
    }

    @Test
    void add() {
    }

    @Test
    void updateById() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void isValidId() {
    }
}