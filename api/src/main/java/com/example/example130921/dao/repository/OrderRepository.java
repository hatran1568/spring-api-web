package com.example.example130921.dao.repository;

import com.example.example130921.dao.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Optional<List<Order>> getAllOrders();

    Optional<Order> findById(int id);

    void add(Order o);

    void deleteById(int id);
    void updateById(int id, Order order);
}
