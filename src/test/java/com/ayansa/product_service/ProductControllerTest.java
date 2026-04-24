package com.ayansa.product_service;

import com.ayansa.product_service.model.Product;
import com.ayansa.product_service.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ProductControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired ProductRepository repo;
    @Autowired ObjectMapper mapper;

    private Long savedId;

    @BeforeEach
    void setUp() {
        // This works because we added the 4-arg constructor to Product.java
        Product p = repo.save(new Product("Test Laptop", 999.0, 10, "Electronics"));
        savedId = p.getId();
    }

    @Test
    void getAll_returns200() throws Exception {
        mockMvc.perform(get("/api/v1/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThan(0))));
    }

    @Test // Added missing @Test annotation
    void getById_returns200_whenExists() throws Exception {
        mockMvc.perform(get("/api/v1/products/" + savedId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Test Laptop")))
                .andExpect(jsonPath("$.category", is("Electronics")));
    }

    @Test
    void create_returns201_withLocation() throws Exception {
        // Fixed: Use a single-line string or a proper Text Block
        String body = """
        {"name":"Headphones","price":89.99,"stockQty":50,"category":"Audio"}
        """;

        mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated()) // Matches 201 Created in Swagger
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name", is("Headphones")));
    }

    @Test
    void update_returns200() throws Exception {
        // Fixed: Removed the illegal line break inside the name "Pro Laptop"
        String body = """
        {"name":"Pro Laptop","price":1299.0,"stockQty":5,"category":"Electronics"}
        """;

        mockMvc.perform(put("/api/v1/products/" + savedId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Pro Laptop")))
                .andExpect(jsonPath("$.price", is(1299.0)));
    }

    @Test
    void delete_returns204() throws Exception {
        mockMvc.perform(delete("/api/v1/products/" + savedId))
                .andExpect(status().isNoContent()); // Matches 204 No Content
    }

    @Test
    void getById_returns404_whenNotFound() throws Exception {
        mockMvc.perform(get("/api/v1/products/9999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void create_returns400_whenNameBlank() throws Exception {
        // Fixed: Removed the line break in the "Name is required" expected string
        String invalid = """
        {"name":"","price":10.0,"stockQty":1,"category":"Tech"}
        """;

        mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalid))
                .andExpect(status().isBadRequest());
    }

    @Test
    void create_returns400_whenPriceInvalid() throws Exception {
        String invalid = """
        {"name":"Widget","price":-1,"stockQty":1,"category":"Tech"}
        """;

        mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalid))
                .andExpect(status().isBadRequest());
    }
}