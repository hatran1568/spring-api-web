package com.example.example130921.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
    private int customerId;
    private String customerName;
    private String phone;

}
