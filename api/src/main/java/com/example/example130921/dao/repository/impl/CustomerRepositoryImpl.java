package com.example.example130921.dao.repository.impl;

import com.example.example130921.dao.entity.Customer;
import com.example.example130921.dao.repository.AbstractRepository;
import com.example.example130921.dao.repository.CustomerRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepositoryImpl extends AbstractRepository implements CustomerRepository {
    @Override
    public Optional<List<Customer>> getAllCustomers() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ").append(attributeNamesForSelect(Customer.class));
        sql.append(" FROM ").append(getSimpleNameTable(Customer.class));
        sql.append(" WHERE is_deleted = 0");
        List<Customer> customers = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(Customer.class));
        return Optional.ofNullable(customers);
    }

    @Override
    public Optional<Customer> findById(int id) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ").append(attributeNamesForSelect(Customer.class));
        sql.append(" FROM ").append(getSimpleNameTable(Customer.class));
        sql.append(" WHERE customer_id = ? AND is_deleted = 0");

        Customer customer = jdbcTemplate.queryForObject(sql.toString(), new BeanPropertyRowMapper<>(Customer.class), new Integer[]{id});
        return Optional.ofNullable(customer);
    }

    @Override
    public Optional<List<Integer>> getIdList() {
        String sql = "SELECT customer_id FROM customer";
        List<Integer> idList = jdbcTemplate.queryForList(sql, Integer.class);
        return Optional.ofNullable(idList);
    }

    @Override
    public void add(Customer customer) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ").append(getSimpleNameTable(Customer.class));
        sql.append("(customer_name, phone) VALUES(?, ?)");
        jdbcTemplate.update(sql.toString(), new Object[]{customer.getCustomerName(), customer.getPhone()});
    }

    @Override
    public void updateById(int id, Customer customer) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ").append(getSimpleNameTable(Customer.class));
        sql.append(" SET customer_name = ?, phone = ?");
        sql.append(" WHERE customer_id = ?");
        jdbcTemplate.update(sql.toString(), new Object[]{customer.getCustomerName(), customer.getPhone(), id});
    }

    @Override
    public void deleteById(int id) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ").append(getSimpleNameTable(Customer.class));
        sql.append(" SET is_deleted = 1");
        sql.append(" WHERE customer_id = ?");
        jdbcTemplate.update(sql.toString(), id);
    }


}
