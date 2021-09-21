package com.example.example130921.dao.repository;

import com.example.example130921.dao.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    Optional<List<Employee>> getAllEmployee();

    Optional<Employee> findById(int id);

    Optional<List<Integer>> getIdList();
}
