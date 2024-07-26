package com.viswa.DepartmentalStoreApplication.mapper;

import com.viswa.DepartmentalStoreApplication.dto.ProductDto;
import com.viswa.DepartmentalStoreApplication.model.Product;

import java.util.Date;

public class ProductMapper {
    public static Product productDtotoProduct(ProductDto dto){
        Product product = new Product();
        product.setId(dto.getId());
        product.setProductName(dto.getProductName());
        product.setProductType(dto.getProductType());
        product.setMrp(dto.getMrp());
        product.setPrice(dto.getPrice());
        product.setStocks(dto.getStocks());
        product.setStockArrivedDate(new Date());
        return product;


    }
}
