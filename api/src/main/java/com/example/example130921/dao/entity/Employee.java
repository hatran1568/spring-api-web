package com.example.example130921.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private int employeeId;
    private String employeeName;
    private String phone;
    private Date dob;
    private Date created;
    private Date modified;
    private Boolean isDeleted;
}
