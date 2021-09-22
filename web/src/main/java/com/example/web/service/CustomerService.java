package com.example.web.service;

import com.example.web.dto.request.CustomerRequest;
import com.example.web.dto.response.CustomerResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Optional<List<CustomerResponse>> getCustomers(HttpServletRequest httpServletRequest);
    Optional<CustomerResponse> getById(HttpServletRequest httpServletRequest, String id);
    Optional<CustomerResponse> add(HttpServletRequest httpServletRequest, CustomerRequest customerRequest);
    Optional<CustomerResponse> updateById(HttpServletRequest httpServletRequest, String customerId, CustomerRequest customerRequest);
    Optional<CustomerResponse> deleteById(HttpServletRequest httpServletRequest, String customerId);
}
