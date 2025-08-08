package com.example.demo.repository.elasticsearch;

import com.example.demo.model.elasticsearch.Customer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ElasticsearchRepository<Customer, String> {
}
