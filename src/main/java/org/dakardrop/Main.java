package org.dakardrop;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import org.dakardrop.inventory.entity.Product;
import org.dakardrop.inventory.exception.CurrencyMismatchException;
import org.dakardrop.inventory.exception.InsufficientStockException;
import org.dakardrop.inventory.valueobject.Money;
import org.dakardrop.inventory.valueobject.SKU;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        Product sneakers = new Product(
                new SKU("SNK-001"),
                "Nike Air",
                "Sneakers importées",
                new Money(
                        new BigDecimal("35000"),
                        "XOF"
                )
        );

        sneakers.addStock(10);

        sneakers.removeStock(3);

        System.out.println(sneakers);

        System.out.println("\n=== TEST STOCK INSUFFISANT ===");

        try {

            sneakers.removeStock(100);

        } catch (InsufficientStockException e) {

            System.out.println(e.getMessage());
        }

        System.out.println("\n=== TEST DEVISES ===");

        try {

            Money xof =
                    new Money(
                            new BigDecimal("5000"),
                            "XOF"
                    );

            Money usd =
                    new Money(
                            new BigDecimal("10"),
                            "USD"
                    );

            xof.add(usd);

        } catch (CurrencyMismatchException e) {

            System.out.println(e.getMessage());
        }

        System.out.println("\n=== TEST MONTANT NEGATIF ===");

        try {

            new Money(
                    new BigDecimal("-5000"),
                    "XOF"
            );

        } catch (IllegalArgumentException e) {

            System.out.println(e.getMessage());
        }
    }
}