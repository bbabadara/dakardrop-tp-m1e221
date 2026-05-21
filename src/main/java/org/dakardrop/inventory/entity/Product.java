package org.dakardrop.inventory.entity;

import org.dakardrop.inventory.exception.InsufficientStockException;
import org.dakardrop.inventory.exception.InvalidStockException;
import org.dakardrop.inventory.valueobject.Money;
import org.dakardrop.inventory.valueobject.SKU;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Product {

    private final UUID id;

    private final SKU sku;

    private final String name;

    private final String description;

    private final Money unitPrice;

    private int stock;

    private final List<StockMovement> movements;

    public Product(
            SKU sku,
            String name,
            String description,
            Money unitPrice
    ) {

        validateName(name);

        this.id = UUID.randomUUID();
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
        this.stock = 0;
        this.movements = new ArrayList<>();
    }

    public void addStock(int quantity) {

        validateQuantity(quantity);

        stock += quantity;

        movements.add(
                new StockMovement(
                        sku,
                        MovementType.IN,
                        quantity
                )
        );
    }

    public void removeStock(int quantity) {

        validateQuantity(quantity);

        if (stock < quantity) {
            throw new InsufficientStockException(
                    "Stock insuffisant pour "
                            + name
            );
        }

        stock -= quantity;

        movements.add(
                new StockMovement(
                        sku,
                        MovementType.OUT,
                        quantity
                )
        );
    }

    public boolean isLowStock(int threshold) {
        return stock < threshold;
    }

    private void validateQuantity(int quantity) {

        if (quantity <= 0) {
            throw new InvalidStockException(
                    "La quantité doit être positive"
            );
        }
    }

    private void validateName(String name) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "Nom obligatoire"
            );
        }
    }

    public int stock() {
        return stock;
    }

    public String name() {
        return name;
    }

    public SKU sku() {
        return sku;
    }

    public Money unitPrice() {
        return unitPrice;
    }

    public List<StockMovement> movements() {
        return List.copyOf(movements);
    }

    @Override
    public String toString() {

        return "Product{" +
                "sku=" + sku.value() +
                ", name='" + name + '\'' +
                ", stock=" + stock +
                ", price=" + unitPrice.amount() +
                " " + unitPrice.currency() +
                '}';
    }
}
