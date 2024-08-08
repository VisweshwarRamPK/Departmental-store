package com.viswa.DepartmentalStoreApplication.config;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MultipleMongoConfig {

    //properties

    @Primary
    @Bean(name = "departmentDBProperties")
    @ConfigurationProperties(prefix = "spring.data.mongodb.department")
    public MongoProperties getDepartmentProps() throws Exception{
        return new MongoProperties();
    }

    @Bean(name = "customerDBProperties")
    @ConfigurationProperties(prefix = "spring.data.mongodb.customer")
    public MongoProperties getCustomerProps() throws Exception{
        return new MongoProperties();
    }

    //templates -----------------------------------------------------------------------------------------------------------------------------------------------
    @Primary
    @Bean(name = "departmentTemplate")
    public MongoTemplate departmentMongoTemplate() throws Exception{
        return new MongoTemplate(departmentMongoDatabaseFactory(getDepartmentProps()));
    }

    @Bean(name = "customerTemplate")
    public MongoTemplate customerMongoTemplate() throws Exception{
        return new MongoTemplate(customerMongoDatabaseFactory(getCustomerProps()));
    }

    //Factory----------------------------------------------------------------------------------------------------------------------------------------------------

    @Primary
    @Bean
    public MongoDatabaseFactory departmentMongoDatabaseFactory(MongoProperties mongoProperties) throws Exception{
        return new SimpleMongoClientDatabaseFactory(
                mongoProperties.getUri()
        );
    }

    @Bean
    public MongoDatabaseFactory customerMongoDatabaseFactory(MongoProperties mongoProperties) throws Exception{
        return new SimpleMongoClientDatabaseFactory(
                mongoProperties.getUri()
        );
    }

}
