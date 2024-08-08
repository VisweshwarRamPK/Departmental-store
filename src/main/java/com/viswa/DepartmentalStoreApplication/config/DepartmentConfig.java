package com.viswa.DepartmentalStoreApplication.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {"com.viswa.DepartmentalStoreApplication.service"},mongoTemplateRef = DepartmentConfig.MONGO_TEMPLATE)
public class DepartmentConfig {
    protected static final String MONGO_TEMPLATE = "departmentTemplate";
}