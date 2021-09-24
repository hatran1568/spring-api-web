package com.example.example130921.service.impl;

import com.example.example130921.dao.entity.Customer;
import com.example.example130921.dao.repository.CustomerRepository;
import com.example.example130921.dto.request.CustomerRequest;
import com.example.example130921.dto.response.CustomerResponse;
import com.example.example130921.exception.ResourceNotFoundException;
import com.example.example130921.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testGetCustomers() {
        Customer customer1 = new Customer(1, "aaa", "012345", null, null, false);
        Customer customer2 = new Customer(2, "bbb", "012345", null, null, false);
        Optional<List<Customer>> expectedCustomerList = Optional.of(Arrays.asList(customer1,customer2));

        Mockito.when(customerRepository.getAllCustomers()).thenReturn(expectedCustomerList);
        Optional<List<CustomerResponse>> customers = customerService.getCustomers();

        assertEquals(customers.get().size(), expectedCustomerList.get().size());


    }

    @Test
    public void testGetByIdSuccess() {
        Customer customer1 = new Customer(1, "aaa", "012345", null, null, false);
        Mockito.when(customerRepository.findById(1)).thenReturn(Optional.of(customer1));
        Optional<CustomerResponse> customer = customerService.getById(1);

        assertTrue(customer.isPresent());
        assertEquals(customer.get(), objectMapper.convertValue(customer1, CustomerResponse.class));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testGetByIdNotFound(){
        Mockito.when(customerRepository.findById(1)).thenReturn(Optional.empty());

        Optional<CustomerResponse> customerResponse = customerService.getById(1);
    }

    @Test
    public void testAdd() {
        Customer customer1 = new Customer(1, "aaa", "012345", null, null, false);
        Mockito.when(customerRepository.add(customer1)).thenReturn(customer1);

        Optional<CustomerResponse> addedCustomer = customerService.add(objectMapper.convertValue(customer1, CustomerRequest.class));
        assertTrue(addedCustomer.isPresent());
        assertEquals(addedCustomer.get(), objectMapper.convertValue(customer1, CustomerResponse.class));
    }


    @Test
    public void deleteById() {
        Customer customer1 = new Customer(1, "aaa", "012345", null, null, false);
        Mockito.when(customerRepository.deleteById(customer1.getCustomerId())).thenReturn(customer1);

        Optional<CustomerResponse> deletedCustomer = customerService.deleteById(1);

        assertTrue(deletedCustomer.isPresent());
        assertEquals(deletedCustomer.get(), objectMapper.convertValue(customer1, CustomerResponse.class));
    }


}