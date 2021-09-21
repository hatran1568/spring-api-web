package com.example.example130921.service;

import com.example.example130921.dao.entity.Order;
import com.example.example130921.dto.request.OrderRequest;
import com.example.example130921.dto.response.OrderResponse;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Optional<List<OrderResponse>> getAllOrders();

    Optional<OrderResponse> getById(int orderId);

    void add(OrderRequest order);

    void deleteById(int orderId);

    void updateById(int orderId, OrderRequest orderRequest);
}
