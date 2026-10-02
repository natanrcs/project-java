package com.example.myapplication;

import java.util.OptionalDouble;

public final class ProductValidator {

    public static final int MIN_NAME_LENGTH = 3;

    private ProductValidator() {
    }

    public static boolean isValidName(String name) {
        return name != null && name.trim().length() >= MIN_NAME_LENGTH;
    }

    /**
     * Parses a price typed by the user, accepting both "." and "," as decimal separator.
     * Returns empty when the text is blank, not a number or negative.
     */
    public static OptionalDouble parsePrice(String text) {
        if (text == null) {
            return OptionalDouble.empty();
        }

        String normalized = text.trim().replace(',', '.');
        if (normalized.isEmpty()) {
            return OptionalDouble.empty();
        }

        try {
            double price = Double.parseDouble(normalized);
            if (Double.isNaN(price) || Double.isInfinite(price) || price < 0) {
                return OptionalDouble.empty();
            }
            return OptionalDouble.of(price);
        } catch (NumberFormatException e) {
            return OptionalDouble.empty();
        }
    }
}
