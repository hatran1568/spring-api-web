package com.example.example130921.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private int categoryId;
    private String categoryName;
    private Date created;
    private Date modified;
    private Boolean isDeleted;
}
