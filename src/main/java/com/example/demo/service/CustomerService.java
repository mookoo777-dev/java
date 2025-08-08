package com.example.demo.service;

import com.example.demo.model.elasticsearch.Customer;
import com.example.demo.repository.elasticsearch.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(String id) {
        return customerRepository.findById(id);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(String id, Customer customerDetails) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        customer.setName(customerDetails.getName());
        customer.setAddress(customerDetails.getAddress());
        return customerRepository.save(customer);
    }

    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }
}
