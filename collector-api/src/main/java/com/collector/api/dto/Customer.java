package com.collector.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private String id;
    private int index;
    private String customerId;
    private String firstName;
    private String lastName;
}
