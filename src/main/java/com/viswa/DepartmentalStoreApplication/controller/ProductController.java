package com.viswa.DepartmentalStoreApplication.controller;


import com.viswa.DepartmentalStoreApplication.dto.ProductDto;
import com.viswa.DepartmentalStoreApplication.model.Product;
import com.viswa.DepartmentalStoreApplication.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department-store")
public class ProductController {

    /*@Autowired
    private ProductService productService;

    // referring that it's belongs to department db
    @Qualifier("departmentTemplate")
    @Autowired
    private MongoTemplate productMongoTemplate;

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/create_product")
    public ResponseEntity<String> createProduct(@RequestBody ProductDto productDto) {
        try {
            Product product = new Product();
            product.setProductName(productDto.getProductName());
            product.setPrice(productDto.getPrice());
            // Map other fields as necessary

            productMongoTemplate.save(product);

            return ResponseEntity.status(HttpStatus.CREATED).body("Product added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Product could not be added");
        }
    }

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/getAllproducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        try {
            List<Product> products = productMongoTemplate.findAll(Product.class);
            return ResponseEntity.status(HttpStatus.OK).body(products);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/updateproducts")
    public ResponseEntity<String> updateProduct(@RequestBody Product product) {
        try {
            productMongoTemplate.save(product);
            return ResponseEntity.status(HttpStatus.OK).body("Product updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Product could not be updated");
        }
    }

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping("/deleteproducts")
    public ResponseEntity<String> deleteAllProducts() {
        try {
            productMongoTemplate.dropCollection(Product.class);
            return ResponseEntity.status(HttpStatus.OK).body("All products deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Products could not be deleted");
        }
    }*/

    @Autowired
    private ProductService productService;

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/add-product")
    public Product addProduct(@RequestBody ProductDto productDto) {
        return productService.addProduct(productDto);
    }

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/update-product/{id}")
    public Product updateProduct(@PathVariable String id, @RequestBody ProductDto productDto) {
        return productService.updateProduct(id, productDto);
    }

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping("/delete-product/{id}")
    public void deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
    }

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/get-product/{id}")
    public Product getProduct(@PathVariable String id) {
        return productService.getProduct(id);
    }

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/get-all-products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

}
