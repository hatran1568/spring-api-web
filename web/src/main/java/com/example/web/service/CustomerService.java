package com.example.web.service;

import com.example.web.dto.request.CustomerRequest;
import com.example.web.dto.response.CustomerResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Optional<List<CustomerResponse>> getCustomers(HttpServletRequest httpServletRequest);
    Optional<CustomerResponse> getById(HttpServletRequest httpServletRequest, int id);
    void add(HttpServletRequest httpServletRequest, CustomerRequest customerRequest);
    void updateById(HttpServletRequest httpServletRequest, int customerId, CustomerRequest customerRequest);
    void deleteById(HttpServletRequest httpServletRequest, int customerId);
}
