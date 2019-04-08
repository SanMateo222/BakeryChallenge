package com.bakery.service;

import com.bakery.model.Packing;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PriceCalculatorImplTest {

    private PriceCalculator generator;

    @Before
    public void setUp() {
        generator = new PriceCalculatorImpl();
    }

    @Test
    public void given_VS5_Product_whenOrderWithNoPackages_thenOrderContainsNullPrice() {
        List<Packing> expectedPackings = new ArrayList<>();
        expectedPackings.add(new Packing(new BigDecimal("6.99"), 0, 0));

        Assert.assertNull(generator.calculateTotalPrice(expectedPackings));
    }

    @Test
    public void given_VS5_Product_whenOrderWithMultipleSamePackages_thenOrderContainsCorrectPricing() {
        List<Packing> expectedPackings = new ArrayList<>();
        expectedPackings.add(new Packing(new BigDecimal("6.99"), 3, 4));

        Assert.assertEquals(new BigDecimal("27.96"),
                generator.calculateTotalPrice(expectedPackings));
    }

    @Test
    public void given_VS5_Product_whenOrderWithMultipleDifferentPackages_thenOrderContainsCorrectPricing() {
        List<Packing> expectedPackings = new ArrayList<>();
        expectedPackings.add(new Packing(new BigDecimal("6.99"), 3, 1));
        expectedPackings.add(new Packing(new BigDecimal("8.99"), 5, 2));

        Assert.assertEquals(new BigDecimal("24.97"),
                generator.calculateTotalPrice(expectedPackings));
    }

    @Test
    public void given_CF_Product_whenOrderWithMultipleSamePackages_thenOrderContainsCorrectPricing() {
        List<Packing> expectedPackings = new ArrayList<>();
        expectedPackings.add(new Packing(new BigDecimal("5.95"), 3, 2));

        Assert.assertEquals(new BigDecimal("11.90"),
                generator.calculateTotalPrice(expectedPackings));

    }

    @Test
    public void given_CF_Product_whenOrderWithTwoDifferentPackages_thenOrderContainsCorrectPricing() {
        List<Packing> expectedPackings = new ArrayList<>();
        expectedPackings.add(new Packing(new BigDecimal("5.95"), 3, 1));
        expectedPackings.add(new Packing(new BigDecimal("9.95"), 5, 1));


        Assert.assertEquals(new BigDecimal("15.90"),
                generator.calculateTotalPrice(expectedPackings));
    }

    @Test
    public void given_CF_Product_whenOrderWithMultipleDifferentPackages_thenOrderContainsCorrectPricing() {
        List<Packing> expectedPackings = new ArrayList<>();
        expectedPackings.add(new Packing(new BigDecimal("5.95"), 3, 1));
        expectedPackings.add(new Packing(new BigDecimal("9.95"), 5, 1));
        expectedPackings.add(new Packing(new BigDecimal("16.99"), 9, 2));

        Assert.assertEquals(new BigDecimal("49.88"),
                generator.calculateTotalPrice(expectedPackings));
    }

}