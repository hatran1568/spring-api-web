package com.example.web.service.impl;

import com.example.web.dto.request.CustomerRequest;
import com.example.web.dto.response.CustomerResponse;
import com.example.web.service.AbstractService;
import com.example.web.service.CustomerService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl extends AbstractService implements CustomerService {

    @Override
    public Optional<List<CustomerResponse>> getCustomers(HttpServletRequest httpServletRequest) {
        CustomerResponse[] responses = apiExchangeService.get(httpServletRequest,
                                                        apiExchangeService.createURL(backApiUrl, "customers"),
                                                        CustomerResponse[].class);
        if (responses == null || responses.length == 0) {
            return Optional.empty();
        }
        return Optional.of(Arrays.asList(responses));
    }

    @Override
    public Optional<CustomerResponse> getById(HttpServletRequest httpServletRequest, int id) {

        CustomerResponse response = apiExchangeService.get(httpServletRequest,
                                                        apiExchangeService.createURL(backApiUrl, "customers","{id}"),
                                                        CustomerResponse.class);
        if (response == null) {
            return Optional.empty();
        }
        return Optional.of(response);
    }

    @Override
    public void add(HttpServletRequest httpServletRequest, CustomerRequest customerRequest) {

    }

    @Override
    public void updateById(HttpServletRequest httpServletRequest, int customerId, CustomerRequest customerRequest) {

    }

    @Override
    public void deleteById(HttpServletRequest httpServletRequest, int customerId) {

    }
}
