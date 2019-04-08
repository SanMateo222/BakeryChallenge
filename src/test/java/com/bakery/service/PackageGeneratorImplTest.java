package com.bakery.service;

import com.bakery.model.BakeryAvailableProduct;
import com.bakery.model.BakeryOrder;
import com.bakery.model.Pack;
import com.bakery.model.Packing;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PackageGeneratorImplTest {

    private static final String VEGEMITE_SCROLL_CODE = "VS5";
    private static final String CROISSANT_CODE = "CF";

    @InjectMocks
    PackageGeneratorImpl generator;
    @Mock
    PackageCompositionGenerator compositionMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenProductCodeAndQuantity_whenOrderGeneratorIsCalled_thenPackingDataIsNotEmpty() {
        List<Integer> packComposition = addPackComposition(3);
        Mockito.when(compositionMock.generatePackageComposition(Mockito.any(), Mockito.any())).thenReturn(packComposition);

        Assert.assertNotNull(generator.calculatePackages(productCreator(VEGEMITE_SCROLL_CODE), 3));
    }


    @Test
    public void givenOrderWithMatchingOrderedAmountAndPackage_whenOrderGeneratorIsCalled_thenOrderContainsOnePackage() {
        List<Integer> packComposition = addPackComposition(3);
        Mockito.when(compositionMock.generatePackageComposition(Mockito.any(), Mockito.any())).thenReturn(packComposition);

        List<Packing> result = generator.calculatePackages(productCreator(VEGEMITE_SCROLL_CODE), 3);

        List<Packing> expectedPackings = new ArrayList<>();
        expectedPackings.add(addPacking(3, 1));

        List<BakeryOrder> expectedBakeryOrderList = new ArrayList<>();
        expectedBakeryOrderList.add(new BakeryOrder.Builder()
                .withPackages(expectedPackings)
                .build());

        Assert.assertEquals(expectedBakeryOrderList.get(0).getPackings().get(0).getAmountOfPackages(),
                result.get(0).getAmountOfPackages());

    }

    private Packing addPacking(int productsInPackage, int amountOfPackages) {
        return new Packing(null, productsInPackage, amountOfPackages);
    }

    @Test
    public void givenOrderWithDoubleOrderedAmountThanPackage_whenOrderGeneratorIsCalled_thenOrderContainsTwoPackage() {
        List<Integer> packComposition = addPackComposition(3, 3);
        Mockito.when(compositionMock.generatePackageComposition(Mockito.any(), Mockito.any())).thenReturn(packComposition);

        List<Packing> result = generator.calculatePackages(productCreator(VEGEMITE_SCROLL_CODE), 6);

        List<Packing> expectedPackings = new ArrayList<>();
        expectedPackings.add(addPacking(3, 2));

        List<BakeryOrder> expectedBakeryOrderList = new ArrayList<>();
        expectedBakeryOrderList.add(new BakeryOrder.Builder()
                .withPackages(expectedPackings)
                .build());

        Assert.assertEquals(expectedBakeryOrderList.get(0).getPackings().get(0).getAmountOfPackages(),
                result.get(0).getAmountOfPackages());

    }

    @Test
    public void given_VS5_Product_whenOrderWithMultipleSamePackages_thenOrderContainsCorrectPackageAmounts() {
        List<Integer> packComposition = addPackComposition(3, 3, 3, 3);
        Mockito.when(compositionMock.generatePackageComposition(Mockito.any(), Mockito.any())).thenReturn(packComposition);

        List<Packing> result = generator.calculatePackages(productCreator(VEGEMITE_SCROLL_CODE), 12);

        List<Packing> expectedPackings = new ArrayList<>();
        expectedPackings.add(addPacking(3, 4));

        List<BakeryOrder> expectedBakeryOrderList = new ArrayList<>();
        expectedBakeryOrderList.add(new BakeryOrder.Builder()
                .withPackages(expectedPackings)
                .build());

        Assert.assertEquals(expectedBakeryOrderList.get(0).getPackings().get(0).getAmountOfPackages(),
                result.get(0).getAmountOfPackages());


    }

    @Test
    public void given_VS5_Product_whenOrderWithMultipleDifferentPackages_thenOrderContainsCorrectPackageAmounts() {
        List<Integer> packComposition = addPackComposition(5, 5, 3);
        Mockito.when(compositionMock.generatePackageComposition(Mockito.any(), Mockito.any())).thenReturn(packComposition);

        List<Packing> result = generator.calculatePackages(productCreator(VEGEMITE_SCROLL_CODE), 13);

        List<Packing> expectedPackings = new ArrayList<>();
        expectedPackings.add(addPacking(3, 1));
        expectedPackings.add(addPacking(5, 2));

        List<BakeryOrder> expectedBakeryOrderList = new ArrayList<>();
        expectedBakeryOrderList.add(new BakeryOrder.Builder()
                .withPackages(expectedPackings)
                .build());

        Assert.assertEquals(expectedBakeryOrderList.get(0).getPackings().get(0).getAmountOfPackages(),
                result.get(0).getAmountOfPackages());
        Assert.assertEquals(expectedBakeryOrderList.get(0).getPackings().get(1).getAmountOfPackages(),
                result.get(1).getAmountOfPackages());

    }

    @Test
    public void given_CF_Product_whenOrderWithMultipleSamePackages_thenOrderContainsCorrectPackageAmounts() {
        List<Integer> packComposition = addPackComposition(3, 3);
        Mockito.when(compositionMock.generatePackageComposition(Mockito.any(), Mockito.any())).thenReturn(packComposition);

        List<Packing> result = generator.calculatePackages(productCreator(CROISSANT_CODE), 6);

        List<Packing> expectedPackings = new ArrayList<>();
        expectedPackings.add(addPacking(3, 2));

        List<BakeryOrder> expectedBakeryOrderList = new ArrayList<>();
        expectedBakeryOrderList.add(new BakeryOrder.Builder()
                .withPackages(expectedPackings)
                .build());

        Assert.assertEquals(expectedBakeryOrderList.get(0).getPackings().get(0).getAmountOfPackages(),
                result.get(0).getAmountOfPackages());

    }

    @Test
    public void given_CF_Product_whenOrderWithTwoDifferentPackages_thenOrderContainsCorrectPackageAmounts() {
        List<Integer> packComposition = addPackComposition(5, 3);
        Mockito.when(compositionMock.generatePackageComposition(Mockito.any(), Mockito.any())).thenReturn(packComposition);

        List<Packing> result = generator.calculatePackages(productCreator(CROISSANT_CODE), 8);

        List<Packing> expectedPackings = new ArrayList<>();
        expectedPackings.add(addPacking(3, 1));
        expectedPackings.add(addPacking(5, 1));

        List<BakeryOrder> expectedBakeryOrderList = new ArrayList<>();
        expectedBakeryOrderList.add(new BakeryOrder.Builder()
                .withPackages(expectedPackings)
                .build());

        Assert.assertEquals(expectedBakeryOrderList.get(0).getPackings().get(0).getAmountOfPackages(),
                result.get(0).getAmountOfPackages());
        Assert.assertEquals(expectedBakeryOrderList.get(0).getPackings().get(1).getAmountOfPackages(),
                result.get(1).getAmountOfPackages());

    }


    @Test
    public void given_CF_Product_whenOrderWithMultipleDifferentPackages_thenOrderContainsCorrectPackageAmounts() {

        List<Integer> packComposition = addPackComposition(9, 9, 5, 3);
        Mockito.when(compositionMock.generatePackageComposition(Mockito.any(), Mockito.any())).thenReturn(packComposition);


        List<Packing> result = generator.calculatePackages(productCreator(CROISSANT_CODE), 26);

        List<Packing> expectedPackings = new ArrayList<>();
        expectedPackings.add(addPacking(3, 1));
        expectedPackings.add(addPacking(5, 1));
        expectedPackings.add(addPacking(9, 2));

        List<BakeryOrder> expectedBakeryOrderList = new ArrayList<>();
        expectedBakeryOrderList.add(new BakeryOrder.Builder()
                .withPackages(expectedPackings)
                .build());

        Assert.assertEquals(expectedBakeryOrderList.get(0).getPackings().get(0).getAmountOfPackages(),
                result.get(0).getAmountOfPackages());
        Assert.assertEquals(expectedBakeryOrderList.get(0).getPackings().get(1).getAmountOfPackages(),
                result.get(1).getAmountOfPackages());
        Assert.assertEquals(expectedBakeryOrderList.get(0).getPackings().get(2).getAmountOfPackages(),
                result.get(2).getAmountOfPackages());
    }

    private List<Integer> addPackComposition(Integer... args) {
        return new ArrayList<>(Arrays.asList(args));
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