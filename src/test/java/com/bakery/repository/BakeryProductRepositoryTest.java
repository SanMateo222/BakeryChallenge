package com.bakery.repository;

import com.bakery.model.BakeryAvailableProduct;
import com.bakery.model.Pack;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;

public class BakeryProductRepositoryTest {


    @Test
    public void givenCorrectProductId_whenSingleProductIsSearchedById_thenCorrectProductIsRetreivedFromRepository() {
        BakeryAvailableProduct product = new BakeryProductRepository().findByProductCode("VS5");

        BakeryAvailableProduct expectedProduct = new BakeryAvailableProduct();
        expectedProduct.setProductCode("VS5");
        expectedProduct.setProductName("Vegemite Scroll");
        List<Pack> packList = new ArrayList<>();
        Pack pack1 = new Pack(new BigDecimal("6.99"), 3);
        Pack pack2 = new Pack(new BigDecimal("8.99"), 5);
        packList.add(pack1);
        packList.add(pack2);

        expectedProduct.setAvailablePacks(packList);

        Assert.assertEquals(expectedProduct.getProductCode(), product.getProductCode());
        Assert.assertEquals(expectedProduct.getProductName(), product.getProductName());
        Assert.assertEquals(expectedProduct.getAvailablePacks().get(0).getPackagePrice(), product.getAvailablePacks().get(0).getPackagePrice());
        Assert.assertEquals(expectedProduct.getAvailablePacks().get(0).getProductsInPackage(), product.getAvailablePacks().get(0).getProductsInPackage());
        Assert.assertEquals(expectedProduct.getAvailablePacks().get(1).getPackagePrice(), product.getAvailablePacks().get(1).getPackagePrice());
        Assert.assertEquals(expectedProduct.getAvailablePacks().get(1).getProductsInPackage(), product.getAvailablePacks().get(1).getProductsInPackage());
    }

    @Test
    public void givenIncorrectProductId_whenSingleIncorrectProductIsSearchedById_thenNoProductIsRetreivedFromRepository() {
        Assert.assertNull(new BakeryProductRepository().findByProductCode("VS5-Incorrect"));
    }

    @Test
    public void whenAllProductsAreSearched_thenOutputIsNotEmpty() {
        List<BakeryAvailableProduct> all = new BakeryProductRepository().findAll();
        assertFalse(all.isEmpty());
    }

}