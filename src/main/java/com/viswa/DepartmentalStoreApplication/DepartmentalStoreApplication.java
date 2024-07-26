package com.viswa.DepartmentalStoreApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Departmental Store Application",version = "3.1.0",description = "Departmental Store application"))
public class DepartmentalStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepartmentalStoreApplication.class, args);
	}
	//"hi"
}
