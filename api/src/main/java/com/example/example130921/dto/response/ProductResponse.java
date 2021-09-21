package com.example.example130921.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private int productId;
    private String productName;
    private int categoryId;
    private Double unitPrice;
}
