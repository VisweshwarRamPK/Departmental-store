package com.viswa.DepartmentalStoreApplication.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {"com.viswa.DepartmentalStoreApplication.service"},mongoTemplateRef = CustomerConfig.MONGO_TEMPLATE)
public class CustomerConfig {
    protected static final String MONGO_TEMPLATE = "customerTemplate";
}