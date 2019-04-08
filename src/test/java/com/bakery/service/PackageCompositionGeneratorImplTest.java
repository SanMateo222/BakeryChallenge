package com.bakery.service;

import com.bakery.model.BakeryAvailableProduct;
import com.bakery.model.Pack;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PackageCompositionGeneratorImplTest {

    private PackageCompositionGenerator generator;

    @Before
    public void setUp() {
        generator = new PackageCompositionGeneratorImpl();
    }

    @Test
    public void given_CF_Product_whenCalledWithQuantityFittingExactlyOneSmallPackage_thenResultListHasOnePack() {

        BakeryAvailableProduct product1 = productCreator("CF");

        List<Integer> resultList1 = generator.generatePackageComposition(product1, 3);
        Assert.assertEquals(resultList1.size(), 1);
        Assert.assertEquals(resultList1.get(0).intValue(), 3);

    }

    @Test
    public void given_CF_Product_whenCalledWithQuantityFittingExactlyOneBigPackage_thenResultListHasOnePack() {

        BakeryAvailableProduct product1 = productCreator("CF");

        List<Integer> resultList1 = generator.generatePackageComposition(product1, 9);
        Assert.assertEquals(resultList1.size(), 1);
        Assert.assertEquals(resultList1.get(0).intValue(), 9);

    }

    @Test
    public void given_CF_Product_whenCalledWithQuantityFittingExactlyTwoSamePackage_thenResultListHasTwoSamePack() {

        BakeryAvailableProduct product1 = productCreator("CF");

        List<Integer> resultList1 = generator.generatePackageComposition(product1, 10);
        Assert.assertEquals(resultList1.size(), 2);
        Assert.assertEquals(resultList1.get(0).intValue(), 5);
        Assert.assertEquals(resultList1.get(1).intValue(), 5);

    }

    @Test
    public void given_CF_Product_whenCalledWithQuantityFittingExactlyTwoDifferentPackage_thenResultListHasTwoDiffPack() {

        BakeryAvailableProduct product1 = productCreator("CF");

        List<Integer> resultList1 = generator.generatePackageComposition(product1, 8);
        Assert.assertEquals(resultList1.size(), 2);
        Assert.assertEquals(resultList1.get(0).intValue(), 5);
        Assert.assertEquals(resultList1.get(1).intValue(), 3);

    }

    @Test
    public void given_CF_Product_whenCalledWithQuantityFittingExactlyThreeSameMediumPackage_thenResultListHasThreeSameMediumPack() {

        BakeryAvailableProduct product1 = productCreator("CF");

        List<Integer> resultList1 = generator.generatePackageComposition(product1, 15);
        Assert.assertEquals(resultList1.size(), 3);
        Assert.assertEquals(resultList1.get(0).intValue(), 9);
        Assert.assertEquals(resultList1.get(1).intValue(), 3);
        Assert.assertEquals(resultList1.get(2).intValue(), 3);

    }

    @Test
    public void given_CF_Product_whenCalledWithQuantityFittingExactlyThreeSameBigPackage_thenResultListHasThreeSameBigPack() {

        BakeryAvailableProduct product1 = productCreator("CF");

        List<Integer> resultList1 = generator.generatePackageComposition(product1, 27);
        Assert.assertEquals(resultList1.size(), 3);
        Assert.assertEquals(resultList1.get(0).intValue(), 9);
        Assert.assertEquals(resultList1.get(1).intValue(), 9);
        Assert.assertEquals(resultList1.get(2).intValue(), 9);

    }

    @Test
    public void given_CF_Product_whenCalledWithQuantityFittingExactlyThreeDiffPackage_thenResultListHasThreeDiffPack() {

        BakeryAvailableProduct product1 = productCreator("CF");

        List<Integer> resultList1 = generator.generatePackageComposition(product1, 17);
        Assert.assertEquals(resultList1.size(), 3);
        Assert.assertEquals(resultList1.get(0).intValue(), 9);
        Assert.assertEquals(resultList1.get(1).intValue(), 5);
        Assert.assertEquals(resultList1.get(2).intValue(), 3);

    }

    @Test
    public void given_CF_Product_whenCalledWithQuantityNotFittingSmallestPackage_thenResultListIsEmpty() {

        BakeryAvailableProduct product1 = productCreator("CF");

        List<Integer> resultList1 = generator.generatePackageComposition(product1, 2);
        Assert.assertEquals(resultList1.size(), 0);

    }

    @Test
    public void given_CF_Product_whenCalledWithQuantityFittingOneSmallestPackageAndQuantityLeftCantFitAnyPackage_thenResultListIsEmpty() {

        BakeryAvailableProduct product1 = productCreator("CF");

        List<Integer> resultList1 = generator.generatePackageComposition(product1, 4);
        Assert.assertEquals(resultList1.size(), 0);

    }

    @Test
    public void given_CF_Product_whenCalledWithQuantityFittingTwoSmallestPackageAndQuantityLeftCantFitAnyPackage_thenResultListIsEmpty() {

        BakeryAvailableProduct product1 = productCreator("CF");

        List<Integer> resultList1 = generator.generatePackageComposition(product1, 7);
        Assert.assertEquals(resultList1.size(), 0);

    }

    @Test
    public void given_MB11_Product_whenCalledWithQuantityFittingExactlyOneSmallPackage_thenResultListHasOnePack() {

        BakeryAvailableProduct product1 = productCreator("MB11");

        List<Integer> resultList1 = generator.generatePackageComposition(product1, 2);
        Assert.assertEquals(resultList1.size(), 1);
        Assert.assertEquals(resultList1.get(0).intValue(), 2);

    }

    @Test
    public void given_MB11_Product_whenCalledWithQuantityFittingExactlyOneBigPackage_thenResultListHasOnePack() {

        BakeryAvailableProduct product1 = productCreator("MB11");

        List<Integer> resultList1 = generator.generatePackageComposition(product1, 8);
        Assert.assertEquals(resultList1.size(), 1);
        Assert.assertEquals(resultList1.get(0).intValue(), 8);

    }

    @Test
    public void given_MB11_Product_whenCalledWithQuantityFittingExactlyTwoSamePackage_thenResultListHasTwoSamePack() {

        BakeryAvailableProduct product1 = productCreator("MB11");

        List<Integer> resultList1 = generator.generatePackageComposition(product1, 10);
        Assert.assertEquals(resultList1.size(), 2);
        Assert.assertEquals(resultList1.get(0).intValue(), 5);
        Assert.assertEquals(resultList1.get(1).intValue(), 5);

    }

    @Test
    public void given_MB11_Product_whenCalledWithQuantityFittingExactlyTwoDifferentPackage_thenResultListHasTwoDiffPack() {

        BakeryAvailableProduct product1 = productCreator("MB11");

        List<Integer> resultList1 = generator.generatePackageComposition(product1, 7);

        Assert.assertEquals(resultList1.size(), 2);
        Assert.assertEquals(resultList1.get(0).intValue(), 5);
        Assert.assertEquals(resultList1.get(1).intValue(), 2);

    }

    @Test
    public void given_MB11_Product_whenCalledWithQuantityFittingExactlyThreeSameBigPackage_thenResultListHasThreeSameBigPack() {

        BakeryAvailableProduct product1 = productCreator("MB11");

        List<Integer> resultList1 = generator.generatePackageComposition(product1, 24);
        Assert.assertEquals(resultList1.size(), 3);
        Assert.assertEquals(resultList1.get(0).intValue(), 8);
        Assert.assertEquals(resultList1.get(1).intValue(), 8);
        Assert.assertEquals(resultList1.get(2).intValue(), 8);

    }

    @Test
    public void given_MB11_Product_whenCalledWithQuantityFittingExactlyThreeDiffPackage_thenResultListHasThreeDiffPack() {

        BakeryAvailableProduct product1 = productCreator("MB11");

        List<Integer> resultList1 = generator.generatePackageComposition(product1, 15);
        Assert.assertEquals(resultList1.size(), 3);
        Assert.assertEquals(resultList1.get(0).intValue(), 8);
        Assert.assertEquals(resultList1.get(1).intValue(), 5);
        Assert.assertEquals(resultList1.get(2).intValue(), 2);

    }

    @Test
    public void given_MB11_Product_whenCalledWithQuantityNotFittingSmallestPackage_thenResultListIsEmpty() {

        BakeryAvailableProduct product1 = productCreator("MB11");

        List<Integer> resultList1 = generator.generatePackageComposition(product1, 1);

        Assert.assertEquals(resultList1.size(), 0);

    }

    @Test
    public void given_MB11_Product_whenCalledWithQuantityFittingOneSmallestPackageAndQuantityLeftCantFitAnyPackage_thenResultListIsEmpty() {

        BakeryAvailableProduct product1 = productCreator("MB11");

        List<Integer> resultList1 = generator.generatePackageComposition(product1, 3);
        Assert.assertEquals(resultList1.size(), 0);

    }

    @Test
    public void given_MB11_ProductAndPacksNotInOrder_whenCalledWithQuantityFittingOnePackage_thenResultListHasOnePackage() {

        BakeryAvailableProduct product1 = productCreator("MB11");

        product1.getAvailablePacks().get(0).setProductsInPackage(8);
        product1.getAvailablePacks().get(2).setProductsInPackage(2);


        List<Integer> resultList1 = generator.generatePackageComposition(product1, 5);
        Assert.assertEquals(resultList1.size(), 1);
        Assert.assertEquals(resultList1.get(0).intValue(), 5);

    }

    @Test
    public void given_MB11_ProductAndPacksNotInOrder_whenCalledWithQuantityFittingTwoPackage_thenResultListHasTwoPackage() {

        BakeryAvailableProduct product1 = productCreator("MB11");

        product1.getAvailablePacks().get(0).setProductsInPackage(8);
        product1.getAvailablePacks().get(2).setProductsInPackage(2);

        List<Integer> resultList1 = generator.generatePackageComposition(product1, 16);
        Assert.assertEquals(resultList1.size(), 2);
        Assert.assertEquals(resultList1.get(0).intValue(), 8);
        Assert.assertEquals(resultList1.get(1).intValue(), 8);

    }

    @Test
    public void given_MB11_ProductAndPacksNotInOrder_whenCalledWithQuantityFittingThreePackage_thenResultListHasThreePackage() {

        BakeryAvailableProduct product1 = productCreator("MB11");

        product1.getAvailablePacks().get(0).setProductsInPackage(8);
        product1.getAvailablePacks().get(2).setProductsInPackage(2);

        List<Integer> resultList1 = generator.generatePackageComposition(product1, 15);
        Assert.assertEquals(resultList1.size(), 3);
        Assert.assertEquals(resultList1.get(0).intValue(), 8);
        Assert.assertEquals(resultList1.get(1).intValue(), 5);
        Assert.assertEquals(resultList1.get(2).intValue(), 2);

    }

    @Test
    public void given_CF_ProductAndPacksNotInOrder_whenCalledWithQuantityFittingThreePackage_thenResultListHasThreePackage() {

        BakeryAvailableProduct product = productCreator("CF");

        product.getAvailablePacks().get(0).setProductsInPackage(9);
        product.getAvailablePacks().get(2).setProductsInPackage(3);

        List<Integer> resultList = generator.generatePackageComposition(product, 15);
        Assert.assertEquals(resultList.size(), 3);
        Assert.assertEquals(resultList.get(0).intValue(), 9);
        Assert.assertEquals(resultList.get(1).intValue(), 3);
        Assert.assertEquals(resultList.get(2).intValue(), 3);

    }

    @Test
    public void given_MB11_Product_whenCalledWithQuantityFittingFourPackage_thenResultListHasFourPackage() {

        BakeryAvailableProduct product = productCreator("MB11");

        List<Integer> resultList = generator.generatePackageComposition(product, 14);
        Assert.assertEquals(resultList.size(), 4);
        Assert.assertEquals(resultList.get(0).intValue(), 8);
        Assert.assertEquals(resultList.get(1).intValue(), 2);
        Assert.assertEquals(resultList.get(2).intValue(), 2);
        Assert.assertEquals(resultList.get(3).intValue(), 2);

    }


    @Test
    public void given_VS5_Product_whenCalledWith58_thenResultListHasSmallestAmountOfPackages() {

        BakeryAvailableProduct product = productCreator("VS5");


        List<Integer> resultList = generator.generatePackageComposition(product, 58);
        Assert.assertEquals(12, resultList.size());
        Assert.assertEquals(5, resultList.get(0).intValue());
        Assert.assertEquals(3, resultList.get(11).intValue());

    }

    @Test
    public void given_VS5_Product_whenCalledWith29_thenResultListHasSmallestAmountOfPackages() {

        BakeryAvailableProduct product = productCreator("VS5");


        List<Integer> resultList = generator.generatePackageComposition(product, 29);
        Assert.assertEquals(7, resultList.size());
        Assert.assertEquals(5, resultList.get(0).intValue());
        Assert.assertEquals(3, resultList.get(6).intValue());
        Assert.assertEquals(3, resultList.get(5).intValue());
        Assert.assertEquals(3, resultList.get(4).intValue());

    }

    @Test
    public void given_VS5_Product_whenCalledWith52_thenResultListHasSmallestAmountOfPackages() {

        BakeryAvailableProduct product = productCreator("VS5");


        List<Integer> resultList = generator.generatePackageComposition(product, 52);
        Assert.assertEquals(12, resultList.size());
        Assert.assertEquals(5, resultList.get(0).intValue());
        Assert.assertEquals(3, resultList.get(11).intValue());
        Assert.assertEquals(3, resultList.get(10).intValue());
        Assert.assertEquals(3, resultList.get(9).intValue());
        Assert.assertEquals(3, resultList.get(8).intValue());

    }

    @Test
    public void given_CF_Product_whenCalledWith76_thenResultListHasSmallestAmountOfPackages() {

        BakeryAvailableProduct product = productCreator("CF");


        List<Integer> resultList = generator.generatePackageComposition(product, 76);


        Assert.assertEquals(10, resultList.size());
        Assert.assertEquals(9, resultList.get(0).intValue());
        Assert.assertEquals(3, resultList.get(9).intValue());

    }

    @Test
    public void given_CF_Product_whenCalledWith96_thenResultListHasSmallestAmountOfPackages() {

        BakeryAvailableProduct product = productCreator("CF");


        List<Integer> resultList = generator.generatePackageComposition(product, 96);


        Assert.assertEquals(12, resultList.size());
        Assert.assertEquals(9, resultList.get(0).intValue());
        Assert.assertEquals(3, resultList.get(11).intValue());
        Assert.assertEquals(3, resultList.get(10).intValue());
        Assert.assertEquals(9, resultList.get(9).intValue());

    }

    @Test
    public void given_CF_Product_whenCalledWith16_thenResultListHasSmallestAmountOfPackages() {

        BakeryAvailableProduct product = productCreator("CF");


        List<Integer> resultList = generator.generatePackageComposition(product, 16);


        Assert.assertEquals(4, resultList.size());
        Assert.assertEquals(5, resultList.get(0).intValue());
        Assert.assertEquals(5, resultList.get(1).intValue());
        Assert.assertEquals(3, resultList.get(2).intValue());
        Assert.assertEquals(3, resultList.get(3).intValue());

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