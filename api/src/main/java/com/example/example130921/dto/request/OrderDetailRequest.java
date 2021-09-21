package com.example.example130921.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.sql.In;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailRequest {
    @NotEmpty
    private Integer orderId;
    @NotEmpty
    private Integer productId;
    @PositiveOrZero
    private Integer quantity;
}
