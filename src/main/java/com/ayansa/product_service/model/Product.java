package com.ayansa.product_service.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
@Entity
@Table(name = "products")
public class Product {
    // ── Getters and Setters ───────────────────────────────────
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Product name must not be blank")
    @Column(nullable = false)
    private String name;
    @Positive(message = "Price must be greater than zero")
    @Column(nullable = false)
    private double price;
    // ── Constructors ──────────────────────────────────────────
    public Product() {}


    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public void setId(Long id) { this.id = id; }
    public void setName(String n) { this.name = n; }
    public void setPrice(double p) { this.price = p; }
}