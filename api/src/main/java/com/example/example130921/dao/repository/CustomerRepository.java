package com.example.example130921.dao.repository;

import com.example.example130921.dao.entity.Customer;
import com.example.example130921.dto.request.CustomerRequest;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    Optional<List<Customer>> getAllCustomers();

    Optional<Customer> findById(int id);

    Optional<List<Integer>> getIdList();

    void add(Customer customer);

    void updateById(int id, Customer customer);

    void deleteById(int id);
}
