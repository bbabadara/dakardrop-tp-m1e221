package org.dakardrop.inventory.exception;


public class CurrencyMismatchException
        extends RuntimeException {

    public CurrencyMismatchException(String message) {
        super(message);
    }
}