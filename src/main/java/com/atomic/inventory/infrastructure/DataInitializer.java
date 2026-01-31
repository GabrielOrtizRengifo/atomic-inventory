package com.atomic.inventory.infrastructure;

import com.atomic.inventory.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final ProductRepository productRepository;

    @Override
    public void run(String... args) {
        // Creamos un producto inicial para que el usuario pueda probar Swagger
        // inmediatamente
        Product laptop = Product.builder()
                .name("Laptop Gamer")
                .quantity(50)
                .build();

        productRepository.save(laptop);

        System.out.println(">> Base de datos inicializada con un producto (ID: 1)");
    }
}
