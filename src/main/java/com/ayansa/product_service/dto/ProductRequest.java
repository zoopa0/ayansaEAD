package com.ayansa.product_service.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Generates all getters, setters, equals, and hashCode
@NoArgsConstructor // Generates a constructor with no arguments
@AllArgsConstructor // Generates the 4-argument constructor your tests need
public class ProductRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    private double price;

    @Min(value = 0, message = "Stock quantity cannot be negative")
    private int stockQty;

    @NotBlank(message = "Category is required")
    private String category;
}