package com.viswa.DepartmentalStoreApplication.model;


import org.springframework.data.mongodb.core.mapping.Document;

@Document("Carts")
public class Carts {
    private String quantity;
    private String product_name;
}
