package com.viswa.DepartmentalStoreApplication.controller;

import com.viswa.DepartmentalStoreApplication.model.Customerdetails;
import com.viswa.DepartmentalStoreApplication.repository.CustomerRepository;
import com.viswa.DepartmentalStoreApplication.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/add-customer")
    public Customerdetails addCustomer(@RequestBody Customerdetails customer) {
        return customerService.addCustomer(customer);
    }

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/update-customer/{id}")
    public Customerdetails updateCustomer(@PathVariable String id, @RequestBody Customerdetails customer) {
        return customerService.updateCustomer(id, customer);
    }

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping("/remove-customer/{id}")
    public void deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomer(id);
    }

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/get-customer/{id}")
    public Customerdetails getCustomer(@PathVariable String id) {
        return customerService.getCustomer(id);
    }

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/get-all-customers")
    public List<Customerdetails> getAllCustomers() {
        return customerService.getAllCustomers();
    }

}