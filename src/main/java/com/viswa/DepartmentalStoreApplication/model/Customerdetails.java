package com.viswa.DepartmentalStoreApplication.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("Customerdetails")

public class Customerdetails {
    private String id;
    private String storeName;
    private String phoneNumber;
    private String emailId;
    private String address;
}
