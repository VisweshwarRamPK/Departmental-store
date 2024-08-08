package com.viswa.DepartmentalStoreApplication.service;


import com.viswa.DepartmentalStoreApplication.dto.ProductDto;
import com.viswa.DepartmentalStoreApplication.mapper.ProductMapper;
import com.viswa.DepartmentalStoreApplication.model.Product;
import com.viswa.DepartmentalStoreApplication.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ProductService {

    /*public String createProduct(ProductDto productdto) {
        try {
            Product product1 = ProductMapper.productDtotoProduct(productdto);
            Product product2 = productrepo.findByProductName(product1.getProductName());
            if (product2 == null) {
                productrepo.save(product1);
            } else {
                product2.setStocks(product2.getStocks() + product1.getStocks());
                productrepo.save(product2);
            }
            return "productsaddedsuccessfully";
        } catch(Exception e) {
            return "productcannotbeadded";
        }
    }

    public void updateProduct(Product product) {
        Product product1 = productrepo.findByProductName(product.getProductName());
        product1.setProductName(product.getProductName());
        product1.setProductType(product.getProductType());
        product1.setId(product.getId());
        product1.setMrp(product.getMrp());
        product1.setProductType(product.getProductType());
        product1.setStocks(product.getStocks());
        productrepo.save(product1);
    }*/

    @Qualifier("departmentTemplate")
    @Autowired
    private MongoTemplate departmentTemplate;

    public Product addProduct(ProductDto productDto) {
        Product product = mapDtoToProduct(productDto);
        return departmentTemplate.save(product);
    }

    public Product updateProduct(String id, ProductDto productDto) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Product existingProduct = departmentTemplate.findOne(query, Product.class);

        if (existingProduct != null) {
            existingProduct.setProductName(productDto.getProductName());
            existingProduct.setProductType(productDto.getProductType());
            existingProduct.setStocks(productDto.getStocks());
            existingProduct.setPrice(productDto.getPrice());
            existingProduct.setMrp(productDto.getMrp());
            departmentTemplate.save(existingProduct);
            return existingProduct;
        } else {
            throw new RuntimeException("Product not found with id " + id);
        }
    }

    public void deleteProduct(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        departmentTemplate.remove(query, Product.class);
    }

    public Product getProduct(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        return departmentTemplate.findOne(query, Product.class);
    }

    public List<Product> getAllProducts() {
        return departmentTemplate.findAll(Product.class);
    }


    private Product mapDtoToProduct(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setProductName(productDto.getProductName());
        product.setProductType(productDto.getProductType());
        product.setStocks(productDto.getStocks());
        product.setPrice(productDto.getPrice());
        product.setMrp(productDto.getMrp());
        return product;
    }



}