package com.example.myapplication;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

public class ProductTest {

    private Locale originalLocale;

    @Before
    public void setUp() {
        originalLocale = Locale.getDefault();
        Locale.setDefault(Locale.US);
    }

    @After
    public void tearDown() {
        Locale.setDefault(originalLocale);
    }

    @Test
    public void constructor_storesFields() {
        Product product = new Product(7, "Coffee", 12.5);

        assertEquals(7, product.id);
        assertEquals("Coffee", product.name);
        assertEquals(12.5, product.price, 0.0001);
    }

    @Test
    public void toString_formatsNameAndPriceWithTwoDecimals() {
        assertEquals("Coffee - $ 12.50", new Product(1, "Coffee", 12.5).toString());
    }

    @Test
    public void toString_roundsPriceToTwoDecimals() {
        assertEquals("Tea - $ 3.46", new Product(2, "Tea", 3.456).toString());
    }

    @Test
    public void toString_usesDefaultLocaleSeparator() {
        Locale.setDefault(Locale.GERMANY);

        assertEquals("Tea - $ 3,00", new Product(2, "Tea", 3).toString());
    }
}
