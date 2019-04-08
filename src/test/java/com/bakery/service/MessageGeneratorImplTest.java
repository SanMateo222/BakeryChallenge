package com.bakery.service;

import com.bakery.model.BakeryAvailableProduct;
import com.bakery.model.Pack;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MessageGeneratorImplTest {


    private MessageGenerator generator;

    @Before
    public void setUp() {
        generator = new MessageGeneratorImpl();
    }
    @Test
    public void givenProductAndQuantity_whenProductDoesntExistInDatabase_thenResponseContainsErrorMessage() {

        BakeryAvailableProduct availableProduct = null;
        Integer quantity = 5;
        Assert.assertEquals(generator.generateMessage(availableProduct,quantity),MessageGeneratorImpl.NON_EXISTING_PRODUCT);

    }

    @Test
    public void givenProductAndQuantity_whenQuantityZero_thenResponseContainsErrorMessage() {


        BakeryAvailableProduct availableProduct = productCreator("VS5");
        Integer quantity = 0;
        Assert.assertEquals(generator.generateMessage(availableProduct,quantity),MessageGeneratorImpl.NO_PRODUCTS_ORDERED);

    }

    @Test
    public void givenProductAndQuantity_whenPackListEmptyAndQuantityBelowSmallestPackage_thenResponseContainsErrorMessage() {

        BakeryAvailableProduct availableProduct = productCreator("VS5");
        Integer quantity = 2;
        Assert.assertEquals(generator.generateMessage(availableProduct,quantity,new ArrayList<>()),MessageGeneratorImpl.ORDER_BELOW_SMALLEST_PACKAGE);

    }


    @Test
    public void givenProductAndQuantity_whenPackListEmptyAndQuantityCantFitIntoPackages_thenResponseContainsErrorMessage() {

        BakeryAvailableProduct availableProduct = productCreator("VS5");
        Integer quantity = 4;
        Assert.assertEquals(generator.generateMessage(availableProduct,quantity,new ArrayList<>()),MessageGeneratorImpl.ORDER_CANNOT_FIT_INTO_PACKAGES);

    }


    private BakeryAvailableProduct productCreator(String productCode) {

        BakeryAvailableProduct expectedProduct = new BakeryAvailableProduct();
        List<Pack> packList = new ArrayList<>();

        switch (productCode) {
            case "VS5":
                expectedProduct.setProductCode(productCode);
                expectedProduct.setProductName("Vegemite Scroll");
                packList.add(new Pack(new BigDecimal("6.99"), 3));
                packList.add(new Pack(new BigDecimal("8.99"), 5));
                expectedProduct.setAvailablePacks(packList);
                return expectedProduct;

            case "MB11":
                expectedProduct.setProductCode(productCode);
                expectedProduct.setProductName("Blueberry Muffin");
                packList.add(new Pack(new BigDecimal("9.95"), 2));
                packList.add(new Pack(new BigDecimal("16.95"), 5));
                packList.add(new Pack(new BigDecimal("24.95"), 8));
                expectedProduct.setAvailablePacks(packList);
                return expectedProduct;

            case "CF":
                expectedProduct.setProductCode(productCode);
                expectedProduct.setProductName("Croissant");
                packList.add(new Pack(new BigDecimal("5.95"), 3));
                packList.add(new Pack(new BigDecimal("9.95"), 5));
                packList.add(new Pack(new BigDecimal("16.99"), 9));
                expectedProduct.setAvailablePacks(packList);
                return expectedProduct;
            default:
                return null;
        }
    }
}