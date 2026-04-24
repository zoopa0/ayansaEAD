package com.ayansa.product_service;

import com.ayansa.product_service.dto.ProductResponse;
import com.ayansa.product_service.model.Product;
import com.ayansa.product_service.repository.ProductRepository;
import com.ayansa.product_service.service.ProductService;
import com.ayansa.product_service.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void findById_returnsProduct_whenProductExists() {
        // Arrange
        Product laptop = new Product("Laptop", 1200.0);
        laptop.setId(1L);

        when(productRepository.findById(1L)).thenReturn(Optional.of(laptop));

        // Act
        ProductResponse result = productService.findById(1L);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Laptop");
        assertThat(result.getPrice()).isEqualTo(1200.0);
    }

    @Test
    void findById_throwsException_whenProductNotFound() {
        // Arrange - Simulate the database returning nothing
        when(productRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        // We expect the Service to throw ResourceNotFoundException because of .orElseThrow()
        assertThrows(ResourceNotFoundException.class, () -> {
            productService.findById(99L);
        });
    }
}