package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.example.demo.repository.elasticsearch")
public class ElasticsearchConfig {
    // Add your Elasticsearch connection beans here
}
