package com.example.example130921.service.impl;

import com.example.example130921.dao.entity.Employee;
import com.example.example130921.dao.entity.Product;
import com.example.example130921.dao.repository.ProductRepository;
import com.example.example130921.exception.ConstraintViolationException;
import com.example.example130921.service.AbstractService;
import com.example.example130921.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

public class ProductServiceImpl extends AbstractService implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public boolean isValidId(int productId) {
        try{
            Optional<Product> product = productRepository.findById(productId);
            return product.isPresent();
        } catch (EmptyResultDataAccessException e){
            throw new ConstraintViolationException("Foreign key constraint not satisfied with productId: " + productId);
        }
    }
}
