package com.example.example130921.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
    private int orderId;
    private int productId;
    private int quantity;
    private Date created;
    private Date modified;
    private Boolean isDeleted;
}
