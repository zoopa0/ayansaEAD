package com.ayansa.product_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data // Generates getters, setters, and toString automatically
@NoArgsConstructor // Required by JPA
@AllArgsConstructor // Generates the 5-argument constructor (including ID)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;

    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    @Column(nullable = false)
    private double price;

    @Min(value = 0, message = "Stock quantity cannot be negative")
    private int stockQty;

    @NotBlank(message = "Category is required")
    private String category;

    // Manually adding the 4-argument constructor used in ProductControllerTest
    public Product(String name, double price, int stockQty, String category) {
        this.name = name;
        this.price = price;
        this.stockQty = stockQty;
        this.category = category;
    }

    // Manually adding the 2-argument constructor used in ProductServiceTest
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}