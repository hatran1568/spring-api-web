package com.example.example130921.service.impl;

import com.example.example130921.dao.entity.Employee;
import com.example.example130921.dao.repository.EmployeeRepository;
import com.example.example130921.exception.ConstraintViolationException;
import com.example.example130921.service.AbstractService;
import com.example.example130921.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl extends AbstractService implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public boolean isValidId(int employeeId) {
        try{
            Optional<Employee> employee = employeeRepository.findById(employeeId);
            return employee.isPresent();
        } catch (EmptyResultDataAccessException e){
            throw new ConstraintViolationException("Foreign key constraint not satisfied with employeeId: " + employeeId);
        }

    }
}
