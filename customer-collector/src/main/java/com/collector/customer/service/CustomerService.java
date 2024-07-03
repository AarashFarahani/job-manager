package com.collector.customer.service;

import com.collector.api.dto.Customer;
import com.collector.customer.entity.CustomerEntity;
import com.collector.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private static final String CSV_FILE_PATH = "data/customers.csv";
    private final CustomerRepository customerRepository;

    public List<Customer> getAllCustomer() throws IOException {
        var csvFile = new ClassPathResource(CSV_FILE_PATH).getFile();
        var customers = new ArrayList<Customer>();

        try (var streamLines = Files.lines(Paths.get(csvFile.getPath()), Charset.defaultCharset())) {
            var lines = streamLines.toList();

            for (int i = 1; i < lines.size(); i++) {
                var line = lines.get(i);
                var splitted = line.split(",");
                var id = UUID.randomUUID().toString();
                var customer = new Customer(id,
                        Integer.valueOf(splitted[0]),
                        String.valueOf(splitted[1]),
                        String.valueOf(splitted[2]),
                        String.valueOf(splitted[3]));
                customers.add(customer);
            }
        }

        return customers;
    }

    public List<Customer> findAllCustomers() {
        return this.customerRepository.findAll().stream()
                .map(a-> new Customer(a.getId(), a.getIndex().intValue(), a.getCustomerId(), a.getFirstName(), a.getLastName()))
                .toList();
    }

    public String insertCustomer(Customer customer) {
        var customerEntity = new CustomerEntity();
        customerEntity.setCustomerId(customer.getCustomerId());
        customerEntity.setFirstName(customer.getFirstName());
        customerEntity.setLastName(customer.getLastName());
        customerEntity.setIndex(customer.getIndex());

        return customerEntity.getId();
    }
}
