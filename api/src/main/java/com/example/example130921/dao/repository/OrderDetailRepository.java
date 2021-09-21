package com.example.example130921.dao.repository;

import com.example.example130921.dao.entity.OrderDetail;

import java.util.List;
import java.util.Optional;

public interface OrderDetailRepository {
    Optional<List<OrderDetail>> getAllOrderDetails();

    Optional<List<OrderDetail>> getByOrderId(int orderId);

    Optional<OrderDetail> getByOrderIdAndProductId(int orderId, int productId);

    void add(OrderDetail orderDetail);

    void deleteByOrderIdAndProductId(int orderId, int productId);


}
