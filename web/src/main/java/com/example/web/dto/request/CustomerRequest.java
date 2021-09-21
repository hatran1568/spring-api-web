package com.example.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    @NotEmpty(message = "customer name cannot be empty")
    private String customerName;
    @NotEmpty(message = "phone number cannot be empty")
    private String phone;
}

