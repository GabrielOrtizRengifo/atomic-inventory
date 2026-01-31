package com.atomic.inventory;

import com.atomic.inventory.domain.InventoryService;
import com.atomic.inventory.domain.Product;
import com.atomic.inventory.infrastructure.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ConcurrencyInventoryTest {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void should_handle_concurrent_stock_reduction() throws InterruptedException {
        // Given: Product with 100 in stock
        Product product = Product.builder().name("Energy Drink").quantity(100).build();
        Product savedProduct = productRepository.save(product);
        Long productId = savedProduct.getId();

        // 10 threads trying to reduce 10 each
        int threadCount = 10;
        int reductionAmount = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        // When
        for (int i = 0; i < threadCount; i++) {
            executorService.execute(() -> {
                try {
                    inventoryService.reduceStock(productId, reductionAmount);
                } catch (Exception e) {
                    System.out.println("Error reducing stock: " + e.getMessage());
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);

        // Then
        Product updatedProduct = productRepository.findById(productId).orElseThrow();
        System.out.println("Final stock: " + updatedProduct.getQuantity());
        
        // This will likely FAIL if we haven't implemented locking yet (Step 4)
        // because of race conditions.
        assertThat(updatedProduct.getQuantity()).isEqualTo(0);
    }
}
