package com.viswa.DepartmentalStoreApplication.repository;

import com.viswa.DepartmentalStoreApplication.model.Customerdetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customerdetails,String> {

}
