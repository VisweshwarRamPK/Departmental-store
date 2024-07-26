package com.viswa.DepartmentalStoreApplication.dto;

import lombok.Data;

import java.util.Date;
@Data

public class ProductDto {
    private String id;
    private String productName;
    private String productType;
    private int stocks;
    private double price;
    private double mrp;
}
