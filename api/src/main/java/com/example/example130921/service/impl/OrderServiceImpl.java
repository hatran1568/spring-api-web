package com.example.example130921.service.impl;

import com.example.example130921.dao.entity.Order;
import com.example.example130921.dao.repository.OrderRepository;
import com.example.example130921.dto.request.OrderRequest;
import com.example.example130921.dto.response.OrderResponse;
import com.example.example130921.exception.RequestParamInvalidException;
import com.example.example130921.exception.ResourceNotFoundException;
import com.example.example130921.service.AbstractService;
import com.example.example130921.service.CustomerService;
import com.example.example130921.service.EmployeeService;
import com.example.example130921.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl extends AbstractService implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmployeeService employeeService;

    public Optional<List<OrderResponse>> getAllOrders(){
        List<Order> orders = orderRepository.getAllOrders().orElseThrow(() -> {
            throw new ResourceNotFoundException();
        });
        return Optional.of(orders.stream()
                .map(order -> new OrderResponse(order.getOrderId(),
                                                order.getCustomerId(),
                                                order.getEmployeeId(),
                                                order.getOrderDate()))
                .collect(Collectors.toList()));
    }

    @Override
    public Optional<OrderResponse> getById(int orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> {
            throw new ResourceNotFoundException();
        });
        return Optional.of(new OrderResponse(order.getOrderId(),
                                             order.getCustomerId(),
                                             order.getEmployeeId(),
                                             order.getOrderDate()));
    }

    @Override
    public void add(OrderRequest orderRequest) {
        String message = validator.validateRequestThenReturnMessage(orderRequest);
        if (!ObjectUtils.isEmpty(message)) {
            throw new RequestParamInvalidException(message);
        }

        boolean isValidCustomerId = customerService.isValidId(orderRequest.getCustomerId());
        boolean isValidEmployeeId = employeeService.isValidId(orderRequest.getEmployeeId());
        if (isValidCustomerId && isValidEmployeeId) {
            orderRepository.add(objectMapper.convertValue(orderRequest, Order.class));
        }
    }


    @Override
    public void deleteById(int orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public void updateById(int orderId, OrderRequest orderRequest) {
        String message = validator.validateRequestThenReturnMessage(orderRequest);
        if (!ObjectUtils.isEmpty(message)) {
            throw new RequestParamInvalidException(message);
        }

        boolean isValidCustomerId = customerService.isValidId(orderRequest.getCustomerId());
        boolean isValidEmployeeId = employeeService.isValidId(orderRequest.getEmployeeId());
        if (isValidCustomerId && isValidEmployeeId) {
            orderRepository.updateById(orderId, objectMapper.convertValue(orderRequest, Order.class));
        }
    }


}
