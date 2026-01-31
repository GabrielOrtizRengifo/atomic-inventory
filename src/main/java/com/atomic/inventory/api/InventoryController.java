package com.atomic.inventory.api;

import com.atomic.inventory.domain.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/{id}/reduce")
    public ResponseEntity<Void> reduceStock(
            @PathVariable Long id,
            @RequestBody @Valid DecreaseStockRequest request) {
        
        inventoryService.reduceStock(id, request.amount());
        return ResponseEntity.ok().build();
    }
}
