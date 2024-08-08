package com.viswa.DepartmentalStoreApplication.service;


import com.viswa.DepartmentalStoreApplication.model.Customerdetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Qualifier("customerTemplate")
    @Autowired
    private MongoTemplate customerTemplate;

    public Customerdetails addCustomer(Customerdetails customer) {
        return customerTemplate.save(customer);
    }

    public Customerdetails updateCustomer(String id, Customerdetails customerDetails) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Customerdetails existingCustomer = customerTemplate.findOne(query, Customerdetails.class);

        if (existingCustomer != null) {
            existingCustomer.setStoreName(customerDetails.getStoreName());
            existingCustomer.setPhoneNumber(customerDetails.getPhoneNumber());
            existingCustomer.setEmailId(customerDetails.getEmailId());
            existingCustomer.setAddress(customerDetails.getAddress());
            customerTemplate.save(existingCustomer);
            return existingCustomer;
        } else {
            throw new RuntimeException("Customer not found with id " + id);
        }
    }

    public void deleteCustomer(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        customerTemplate.remove(query, Customerdetails.class);
    }

    public Customerdetails getCustomer(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        return customerTemplate.findOne(query, Customerdetails.class);
    }

    public List<Customerdetails> getAllCustomers() {
        return customerTemplate.findAll(Customerdetails.class);
    }
}