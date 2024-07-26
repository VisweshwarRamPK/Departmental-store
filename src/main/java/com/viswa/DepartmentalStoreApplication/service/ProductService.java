package com.viswa.DepartmentalStoreApplication.service;


import com.viswa.DepartmentalStoreApplication.dto.ProductDto;
import com.viswa.DepartmentalStoreApplication.mapper.ProductMapper;
import com.viswa.DepartmentalStoreApplication.model.Product;
import com.viswa.DepartmentalStoreApplication.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class ProductService {
    @Autowired
    ProductRepo productrepo;
    public String createProduct(ProductDto productdto) {
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
    }

}
