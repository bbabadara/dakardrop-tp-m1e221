package org.dakardrop.inventory.valueobject;

import org.dakardrop.inventory.exception.InvalidSKUException;



public record SKU(String value) {

        private static final String REGEX = "^[A-Z]{3}-\\d{3,}$";
        public SKU {
            if (!value.matches(REGEX) || value == null) {
                throw new InvalidSKUException("Le format du SKU est invalide (Format attendu : AAA-123....) : " + value);
            }
        }
    }

