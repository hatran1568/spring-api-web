package com.example.example130921.dao.repository.impl;

import com.example.example130921.dao.entity.Employee;
import com.example.example130921.dao.repository.AbstractRepository;
import com.example.example130921.dao.repository.EmployeeRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepositoryImpl extends AbstractRepository implements EmployeeRepository {
    @Override
    public Optional<List<Employee>> getAllEmployee() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ").append(attributeNamesForSelect(Employee.class));
        sql.append(" FROM ").append(getSimpleNameTable(Employee.class));
        List<Employee> employees = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(Employee.class));
        return Optional.ofNullable(employees);
    }

    @Override
    public Optional<Employee> findById(int id) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ").append(attributeNamesForSelect(Employee.class));
        sql.append(" FROM ").append(getSimpleNameTable(Employee.class));
        sql.append(" WHERE employee_id = ?");

        Employee employee = jdbcTemplate.queryForObject(sql.toString(), new BeanPropertyRowMapper<>(Employee.class), new Integer[]{id});
        return Optional.ofNullable(employee);
    }

    @Override
    public Optional<List<Integer>> getIdList() {
        String sql = "SELECT employee_id FROM employee";
        List<Integer> idList = jdbcTemplate.queryForList(sql, Integer.class);
        return Optional.ofNullable(idList);
    }
}
