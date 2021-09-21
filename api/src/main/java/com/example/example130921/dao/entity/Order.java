package com.example.example130921.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Integer orderId;
    private Integer customerId;
    private Integer employeeId;
    private Date orderDate;
    private Date created;
    private Date modified;
    private Boolean isDeleted;
}
