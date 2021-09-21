package com.example.example130921.dao.repository.impl;

import com.example.example130921.dao.entity.Customer;
import com.example.example130921.dao.entity.Product;
import com.example.example130921.dao.repository.AbstractRepository;
import com.example.example130921.dao.repository.ProductRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;
import java.util.Optional;

public class ProductRepositoryImpl extends AbstractRepository implements ProductRepository {
    @Override
    public Optional<List<Product>> getAllProducts() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ").append(attributeNamesForSelect(Product.class));
        sql.append(" FROM ").append(getSimpleNameTable(Product.class));
        List<Product> products = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(Product.class));
        return Optional.ofNullable(products);
    }

    @Override
    public Optional<Product> findById(int productId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ").append(attributeNamesForSelect(Product.class));
        sql.append(" FROM ").append(getSimpleNameTable(Product.class));
        sql.append(" WHERE product_id = ?");
        Product product = jdbcTemplate.queryForObject(sql.toString(),
                                                    new BeanPropertyRowMapper<>(Product.class),
                                                    new Integer[]{productId});
        return Optional.ofNullable(product);
    }

    @Override
    public void add(Product product) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ").append(getSimpleNameTable(Product.class));
        sql.append("(product_name, category_id, unit_price)");
        sql.append(" VALUES (?, ?, ?)");
        jdbcTemplate.update(sql.toString(), new Object[]{product.getProductName(), product.getCategoryId(), product.getUnitPrice()});
    }

    @Override
    public void updateById(int productId, Product product) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ").append(getSimpleNameTable(Product.class));
        sql.append("SET product_name = ?, category_id = ?, unit_price = ? ");
        sql.append(" WHERE product_id = ?");
        jdbcTemplate.update(sql.toString(), new Object[]{product.getProductName(),
                                                        product.getCategoryId(),
                                                        product.getUnitPrice(),
                                                        productId});
    }

    @Override
    public void deleteById(int productId) {
        String sql = "UPDATE product SET is_deleted = 1 WHERE product_id = ?";
        jdbcTemplate.update(sql, productId);

    }




}
