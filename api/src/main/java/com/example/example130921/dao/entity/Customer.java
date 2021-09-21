package com.example.example130921.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer {
    private int customerId;
    private String customerName;
    private String phone;
    private Date created;
    private Date modified;
    private Boolean isDeleted;


}
