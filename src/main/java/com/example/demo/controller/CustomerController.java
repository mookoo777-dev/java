package com.example.demo.controller;

import com.example.demo.model.elasticsearch.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public Iterable<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Optional<Customer> getCustomerById(@PathVariable String id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable String id, @RequestBody Customer customerDetails) {
        return customerService.updateCustomer(id, customerDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomer(id);
    }
}
