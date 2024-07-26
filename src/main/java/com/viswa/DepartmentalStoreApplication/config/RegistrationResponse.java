package com.viswa.DepartmentalStoreApplication.config;

public class RegistrationResponse {

    private String message;

    public RegistrationResponse(String message) {
        this.message = message;
    }

    // Getter and setter for message
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}