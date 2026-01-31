package com.atomic.inventory.domain;

import com.atomic.inventory.infrastructure.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final ProductRepository productRepository;

    @Transactional
    public void reduceStock(Long productId, Integer amount) {
        Product product = productRepository.findByIdWithLock(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getQuantity() < amount) {
            throw new RuntimeException("Insufficient stock");
        }

        product.setQuantity(product.getQuantity() - amount);
        productRepository.save(product);
    }
}
