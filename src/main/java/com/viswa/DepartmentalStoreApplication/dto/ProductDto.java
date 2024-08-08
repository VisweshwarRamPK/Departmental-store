package com.viswa.DepartmentalStoreApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String id;
    private String productName;
    private String productType;
    private int stocks;
    private double price;
    private double mrp;
}
