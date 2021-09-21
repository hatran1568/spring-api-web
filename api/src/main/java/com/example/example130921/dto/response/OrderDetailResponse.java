package com.example.example130921.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailResponse {

    @NotNull(message = "orderId cannot be null")
    private Integer orderId;

    @NotNull(message = "productId cannot be null")
    private Integer productId;

    @PositiveOrZero
    private Integer quantity;
}
