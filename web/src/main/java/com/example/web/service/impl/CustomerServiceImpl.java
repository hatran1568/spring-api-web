package com.example.web.service.impl;

import com.example.web.dto.request.CustomerRequest;
import com.example.web.dto.response.CustomerResponse;
import com.example.web.exception.RequestParamInvalidException;
import com.example.web.service.AbstractService;
import com.example.web.service.CustomerService;
import com.example.web.utils.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.ModelAndView;

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
    public Optional<CustomerResponse> getById(HttpServletRequest httpServletRequest, String id) {
        if (!StringUtils.isNumeric(id)){
            throw new RequestParamInvalidException("Request id invalid: " + id);
        }
        CustomerResponse response = apiExchangeService.get(httpServletRequest,
                apiExchangeService.createURL(backApiUrl, "customers", id),
                CustomerResponse.class);

        if (response == null) {
            return Optional.empty();
        }
        return Optional.of(response);
    }

    @Override
    public Optional<CustomerResponse> add(HttpServletRequest httpServletRequest, CustomerRequest request) {
        if (request == null) {
            throw new RequestParamInvalidException("request parameter can not be null");
        }
        String message = validator.validateRequestThenReturnMessage(request);
        if (!ObjectUtils.isEmpty(message)) {
            throw new RequestParamInvalidException(message);
        }
        CustomerResponse response = apiExchangeService.post(httpServletRequest,
                                                    backApiUrl + "/customers",
                                                        request,
                                                        CustomerResponse.class);
        if (response == null) {
            return Optional.empty();
        }
        return Optional.of(response);
    }

    @Override
    public Optional<CustomerResponse> updateById(HttpServletRequest httpServletRequest, String customerId, CustomerRequest request) {
        if (request == null) {
            throw new RequestParamInvalidException("request parameter can not be null");
        }
        String message = validator.validateRequestThenReturnMessage(request);
        if (!ObjectUtils.isEmpty(message)) {
            throw new RequestParamInvalidException(message);
        }
        ResponseEntity<CustomerResponse> response = apiExchangeService.put(httpServletRequest,
                backApiUrl + "/customers/" + customerId,
                request,
                CustomerResponse.class);
        if (response == null) {
            return Optional.empty();
        }
        return Optional.of(response.getBody());
    }

    @Override
    public Optional<CustomerResponse> deleteById(HttpServletRequest httpServletRequest, String id) {
        if (!StringUtils.isNumeric(id)){
            throw new RequestParamInvalidException("Request id invalid: " + id);
        }
        ResponseEntity<CustomerResponse> response = apiExchangeService.delete(httpServletRequest,
                                                    backApiUrl + "/customers/" + id,
                                                    id,
                                                    CustomerResponse.class);
        return Optional.of(response.getBody());
    }
}
