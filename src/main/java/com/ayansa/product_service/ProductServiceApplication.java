package com.ayansa.product_service;

import com.ayansa.product_service.model.Product;
import com.ayansa.product_service.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.List;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "Product Service API",
		version = "1.0.0",
		description = "RESTful Product Catalogue — Lab 2"
))

public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}


	@Bean
	CommandLineRunner seedData(ProductRepository repo) {
		return args -> {

			if (repo.count() == 0) {

				repo.saveAll(List.of(
						new Product("Laptop", 1200.00, 10, "Electronics"),
						new Product("Monitor", 350.00, 15, "Electronics"),
						new Product("Keyboard", 85.00, 30, "Accessories"),
						new Product("Mouse", 45.00, 25, "Accessories"),
						new Product("Desk Chair", 200.00, 8, "Furniture"),
						new Product("Notebook", 5.50, 100, "Stationery")
				));

			}
		};
	}
}