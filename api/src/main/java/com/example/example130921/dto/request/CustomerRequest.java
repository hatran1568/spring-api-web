package com.example.example130921.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    @NotEmpty
    private String customerName;
    @NotEmpty
    private String phone;
}
