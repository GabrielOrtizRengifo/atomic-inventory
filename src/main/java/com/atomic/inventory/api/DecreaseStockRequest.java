package com.atomic.inventory.api;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DecreaseStockRequest(
        @NotNull(message = "Amount is required")
        @Min(value = 1, message = "Amount must be at least 1")
        Integer amount
) {}
