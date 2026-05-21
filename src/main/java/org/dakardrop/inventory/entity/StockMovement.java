package org.dakardrop.inventory.entity;

import org.dakardrop.inventory.valueobject.SKU;

import java.time.LocalDateTime;
import java.util.UUID;

public class StockMovement {

    private final UUID id;

    private final SKU sku;

    private final MovementType type;

    private final int quantity;

    private final LocalDateTime occurredAt;

    public StockMovement(
            SKU sku,
            MovementType type,
            int quantity
    ) {

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "Quantité invalide"
            );
        }

        this.id = UUID.randomUUID();
        this.sku = sku;
        this.type = type;
        this.quantity = quantity;
        this.occurredAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "StockMovement{" +
                "type=" + type +
                ", quantity=" + quantity +
                ", occurredAt=" + occurredAt +
                '}';
    }
}