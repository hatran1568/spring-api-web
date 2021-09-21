package com.example.example130921.service;

import com.example.example130921.dao.entity.Customer;
import com.example.example130921.dto.request.CustomerRequest;
import com.example.example130921.dto.response.CustomerResponse;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Optional<List<CustomerResponse>> getCustomers();
    Optional<CustomerResponse> getById(int id);
    void add(CustomerRequest customerRequest);
    void updateById(int customerId, CustomerRequest customerRequest);
    void deleteById(int customerId);
    boolean isValidId(int customerId);
}
