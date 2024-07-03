package com.collector.customer.controller;

import com.collector.api.dto.Customer;
import com.collector.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    public ResponseEntity getCustomers() throws IOException {
        return ResponseEntity.ok(this.customerService.getAllCustomer());
    }

    @GetMapping("/all")
    public ResponseEntity findAllCustomers() {
        return ResponseEntity.ok(this.customerService.findAllCustomers());
    }

    @PostMapping
    public ResponseEntity createCustomer(Customer customer) throws IOException {
        return ResponseEntity.ok(this.customerService.insertCustomer(customer));
    }
}
