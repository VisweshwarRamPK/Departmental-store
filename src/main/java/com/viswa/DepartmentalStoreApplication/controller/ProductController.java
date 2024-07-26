package com.viswa.DepartmentalStoreApplication.controller;


import com.viswa.DepartmentalStoreApplication.dto.ProductDto;
import com.viswa.DepartmentalStoreApplication.model.Product;
import com.viswa.DepartmentalStoreApplication.repository.ProductRepo;
import com.viswa.DepartmentalStoreApplication.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController


@NoArgsConstructor
@AllArgsConstructor
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    ProductRepo productrepo;
    @Operation( security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/create_product")
    public ResponseEntity<String> createProduct(@RequestBody ProductDto product) {
        try{
        String msg = "";

            msg = productService.createProduct(product);



        if(msg.equals("productsaddedsuccessfully")) {
            return ResponseEntity.status(HttpStatus.CREATED).body("product added successfully");
        }
        else
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("products could not be added");
        }}
        catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("products could not be added");
        }
    }@Operation( security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/getAllproducts")
    public List<Product> getAllproducts()
    {
        return productrepo.findAll();

    }
    @Operation( security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/updateproducts")
    public void updateAllproducts(@RequestBody Product product)
    {
        productService.updateProduct(product);
    }
    @Operation( security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping("/deleteproducts")
    public void deleteAllproducts()
    {
        productrepo.deleteAll();

    }
}
