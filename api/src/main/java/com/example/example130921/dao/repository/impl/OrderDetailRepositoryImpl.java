package com.example.example130921.dao.repository.impl;

import com.example.example130921.dao.entity.Employee;
import com.example.example130921.dao.entity.OrderDetail;
import com.example.example130921.dao.repository.AbstractRepository;
import com.example.example130921.dao.repository.OrderDetailRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;
import java.util.Optional;

public class OrderDetailRepositoryImpl extends AbstractRepository implements OrderDetailRepository {
    @Override
    public Optional<List<OrderDetail>> getAllOrderDetails() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ").append(attributeNamesForSelect(OrderDetail.class));
        sql.append(" FROM ").append(getSimpleNameTable(OrderDetail.class));
        sql.append(" WHERE is_deleted = 0");
        List<OrderDetail> orderDetails = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(OrderDetail.class));
        return Optional.ofNullable(orderDetails);
    }

    @Override
    public Optional<List<OrderDetail>> getByOrderId(int orderId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ").append(attributeNamesForSelect(OrderDetail.class));
        sql.append(" FROM ").append(getSimpleNameTable(OrderDetail.class));
        sql.append(" WHERE order_id = ? AND is_deleted = 0");

        List<OrderDetail> orderDetails = jdbcTemplate.query(sql.toString(),
                                                            new BeanPropertyRowMapper<>(OrderDetail.class),
                                                            new Integer[]{orderId});
        return Optional.ofNullable(orderDetails);
    }

    @Override
    public Optional<OrderDetail> getByOrderIdAndProductId(int orderId, int productId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ").append(attributeNamesForSelect(OrderDetail.class));
        sql.append(" FROM ").append(getSimpleNameTable(OrderDetail.class));
        sql.append(" WHERE order_id = ? AND product_id = ? AND is_deleted = 0");

        OrderDetail orderDetail = jdbcTemplate.queryForObject(sql.toString(),
                new BeanPropertyRowMapper<>(OrderDetail.class),
                new Integer[]{orderId, productId});
        return Optional.ofNullable(orderDetail);
    }

    @Override
    public void add(OrderDetail orderDetail) {
        String sql = "INSERT INTO order_detail(order_id, product_id, quantity) VALUE (?, ?, ?)";
        jdbcTemplate.update(sql,orderDetail.getOrderId(), orderDetail.getProductId(), orderDetail.getQuantity());
    }

    @Override
    public void deleteByOrderIdAndProductId(int orderId, int productId) {
        String sql = "UPDATE order_detail SET is_deleted = 1 " +
                "WHERE order_id = ? AND product_id = ?";
        jdbcTemplate.update(sql, new Integer[]{orderId, productId});
    }
}
