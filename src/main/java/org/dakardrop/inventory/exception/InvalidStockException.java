package org.dakardrop.inventory.exception;

public class InvalidStockException
        extends RuntimeException {

    public InvalidStockException(String message) {
        super(message);
    }
}
