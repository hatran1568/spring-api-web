package com.example.example130921.dao.repository.impl;

import com.example.example130921.dao.entity.Order;
import com.example.example130921.dao.repository.AbstractRepository;
import com.example.example130921.dao.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepositoryImpl extends AbstractRepository implements OrderRepository {

    @Override
    public Optional<List<Order>> getAllOrders() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ").append(attributeNamesForSelect(Order.class));
        sql.append(" FROM `order`");
        sql.append(" WHERE is_deleted = 0");
        List<Order> orders = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(Order.class));
        return Optional.ofNullable(orders);
    }

    @Override
    public Optional<Order> findById(int id) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ").append(attributeNamesForSelect(Order.class));
        sql.append(" FROM `order`");
        sql.append(" WHERE order_id = ? AND is_deleted = 0");
        Order order = jdbcTemplate.queryForObject(sql.toString(), new BeanPropertyRowMapper<>(Order.class), new Object[]{id});
        return Optional.ofNullable(order);

    }

    @Override
    public void add(Order order) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO `order`(customer_id, employee_id, order_date)");
        sql.append(" VALUE(?,?,?)");
        jdbcTemplate.update(sql.toString(),order.getCustomerId(), order.getEmployeeId(), order.getOrderDate());
    }

    @Override
    public void deleteById(int id) {
        String sql = "UPDATE `order` SET is_deleted = 1 WHERE order_id = ?";
        jdbcTemplate.update(sql,id);
    }

    @Override
    public void updateById(int id, Order order) {
        String sql = "UPDATE `order` SET customer_id = ?, employee_id = ?, order_date = ?, modified = NOW()";
        jdbcTemplate.update(sql, order.getCustomerId(), order.getEmployeeId(), order.getOrderDate());
    }
}
