package com.example.example130921.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private int productId;
    private String productName;
    private int categoryId;
    private Double unitPrice;
    private Date created;
    private Date modified;
    private Boolean isDeleted;
}
