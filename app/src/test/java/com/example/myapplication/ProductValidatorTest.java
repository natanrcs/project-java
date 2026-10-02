package com.example.myapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.OptionalDouble;

public class ProductValidatorTest {

    @Test
    public void isValidName_acceptsNameWithMinimumLength() {
        assertTrue(ProductValidator.isValidName("abc"));
    }

    @Test
    public void isValidName_rejectsNull() {
        assertFalse(ProductValidator.isValidName(null));
    }

    @Test
    public void isValidName_rejectsEmpty() {
        assertFalse(ProductValidator.isValidName(""));
    }

    @Test
    public void isValidName_rejectsTooShortName() {
        assertFalse(ProductValidator.isValidName("ab"));
    }

    @Test
    public void isValidName_ignoresSurroundingWhitespace() {
        assertFalse(ProductValidator.isValidName("  ab  "));
        assertTrue(ProductValidator.isValidName("  abc  "));
    }

    @Test
    public void parsePrice_acceptsDotSeparator() {
        assertEquals(10.5, ProductValidator.parsePrice("10.5").getAsDouble(), 0.0001);
    }

    @Test
    public void parsePrice_acceptsCommaSeparator() {
        assertEquals(10.5, ProductValidator.parsePrice("10,5").getAsDouble(), 0.0001);
    }

    @Test
    public void parsePrice_acceptsZero() {
        assertEquals(0.0, ProductValidator.parsePrice("0").getAsDouble(), 0.0001);
    }

    @Test
    public void parsePrice_trimsWhitespace() {
        assertEquals(3.0, ProductValidator.parsePrice("  3  ").getAsDouble(), 0.0001);
    }

    @Test
    public void parsePrice_rejectsNull() {
        assertEquals(OptionalDouble.empty(), ProductValidator.parsePrice(null));
    }

    @Test
    public void parsePrice_rejectsBlank() {
        assertEquals(OptionalDouble.empty(), ProductValidator.parsePrice("   "));
    }

    @Test
    public void parsePrice_rejectsLoneSeparator() {
        assertEquals(OptionalDouble.empty(), ProductValidator.parsePrice("."));
        assertEquals(OptionalDouble.empty(), ProductValidator.parsePrice(","));
    }

    @Test
    public void parsePrice_rejectsNegative() {
        assertEquals(OptionalDouble.empty(), ProductValidator.parsePrice("-1"));
    }

    @Test
    public void parsePrice_rejectsText() {
        assertEquals(OptionalDouble.empty(), ProductValidator.parsePrice("abc"));
    }

    @Test
    public void parsePrice_rejectsNanAndInfinity() {
        assertEquals(OptionalDouble.empty(), ProductValidator.parsePrice("NaN"));
        assertEquals(OptionalDouble.empty(), ProductValidator.parsePrice("Infinity"));
    }
}
