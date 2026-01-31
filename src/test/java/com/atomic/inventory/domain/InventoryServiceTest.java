package com.atomic.inventory.domain;

import com.atomic.inventory.infrastructure.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InventoryServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private InventoryService inventoryService;

    @Test
    void should_decrease_stock_successfully() {
        // Given
        Long productId = 1L;
        Product product = Product.builder().id(productId).name("Laptop").quantity(10).build();
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenAnswer(i -> i.getArguments()[0]);

        // When
        inventoryService.reduceStock(productId, 3);

        // Then
        assertThat(product.getQuantity()).isEqualTo(7);
        verify(productRepository).save(product);
    }

    @Test
    void should_throw_exception_when_insufficient_stock() {
        // Given
        Long productId = 1L;
        Product product = Product.builder().id(productId).name("Laptop").quantity(2).build();
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // When & Then
        assertThrows(RuntimeException.class, () -> inventoryService.reduceStock(productId, 5));
        verify(productRepository, never()).save(any());
    }
}
