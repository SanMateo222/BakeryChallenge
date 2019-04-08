package com.bakery.service;

import com.bakery.app.BakeryOrderRequest;
import com.bakery.model.BakeryOrder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderGeneratorImplTest {

    @InjectMocks
    OrderGeneratorImpl generator;

    @Mock
    PriceCalculator priceCalculatorMock;

    @Mock
    PackageGenerator packageGeneratorMock;

    @Mock
    MessageGenerator messageGenerator;

    private static final String VEGEMITE_SCROLL_NAME = "Vegemite Scroll";
    private static final String VEGEMITE_SCROLL_CODE = "VS5";
    private static final String BLUEBERRY_MUFFIN_NAME = "Blueberry Muffin";
    private static final String BLUEBERRY_MUFFIN_CODE = "MB11";
    private static final String CROISSANT_NAME = "Croissant";
    private static final String CROISSANT_CODE = "CF";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenProductCodeAndQuantity_whenOrderGeneratorIsCalled_thenResponseContainsCorrectCodeNameAndAmount() {

        Mockito.when(priceCalculatorMock.calculateTotalPrice(Mockito.any())).thenReturn(new BigDecimal(0));
        Mockito.when(packageGeneratorMock.calculatePackages(Mockito.any(), Mockito.any())).thenReturn(new ArrayList<>());
        Mockito.when(messageGenerator.generateMessage(Mockito.any(), Mockito.any())).thenReturn(null);
        Mockito.when(messageGenerator.generateMessage(Mockito.any(), Mockito.any(), Mockito.anyList())).thenReturn(null);

        BakeryOrderRequest bakeryOrderRequest = new BakeryOrderRequest(VEGEMITE_SCROLL_CODE, "5");

        List<BakeryOrderRequest> bakeryOrderRequestList = new ArrayList<>();
        bakeryOrderRequestList.add(bakeryOrderRequest);

        List<BakeryOrder> expectedBakeryOrderList = new ArrayList<>();

        BakeryOrder expectedBakeryOrder = new BakeryOrder.Builder()
                .withProductName(VEGEMITE_SCROLL_NAME)
                .withProductCode(VEGEMITE_SCROLL_CODE)
                .withProductQuantity(5)
                .build();

        expectedBakeryOrderList.add(expectedBakeryOrder);


        List<BakeryOrder> generatedBakeryOrderList = generator.generateOrderList(bakeryOrderRequestList);

        Assert.assertEquals(generatedBakeryOrderList.get(0).getProductName(), expectedBakeryOrderList.get(0).getProductName());
        Assert.assertEquals(generatedBakeryOrderList.get(0).getProductCode(), expectedBakeryOrderList.get(0).getProductCode());
        Assert.assertEquals(generatedBakeryOrderList.get(0).getProductQuantity(), expectedBakeryOrderList.get(0).getProductQuantity());

    }

    @Test
    public void givenProductCodeAndQuantityTooSmall_whenOrderGeneratorIsCalled_thenResponseContainsCorrectCodeNameAndAmount() {

        Mockito.when(priceCalculatorMock.calculateTotalPrice(Mockito.any())).thenReturn(new BigDecimal(0));
        Mockito.when(packageGeneratorMock.calculatePackages(Mockito.any(), Mockito.any())).thenReturn(new ArrayList<>());
        Mockito.when(messageGenerator.generateMessage(Mockito.any(), Mockito.any())).thenReturn(null);
        Mockito.when(messageGenerator.generateMessage(Mockito.any(), Mockito.any(), Mockito.anyList())).thenReturn(null);

        BakeryOrderRequest bakeryOrderRequest = new BakeryOrderRequest(VEGEMITE_SCROLL_CODE, "1");

        List<BakeryOrderRequest> bakeryOrderRequestList = new ArrayList<>();
        bakeryOrderRequestList.add(bakeryOrderRequest);

        List<BakeryOrder> expectedBakeryOrderList = new ArrayList<>();

        BakeryOrder expectedBakeryOrder = new BakeryOrder.Builder()
                .withProductName(VEGEMITE_SCROLL_NAME)
                .withProductCode(VEGEMITE_SCROLL_CODE)
                .withProductQuantity(1)
                .build();

        expectedBakeryOrderList.add(expectedBakeryOrder);


        List<BakeryOrder> generatedBakeryOrderList = generator.generateOrderList(bakeryOrderRequestList);

        Assert.assertEquals(generatedBakeryOrderList.get(0).getProductName(), expectedBakeryOrderList.get(0).getProductName());
        Assert.assertEquals(generatedBakeryOrderList.get(0).getProductCode(), expectedBakeryOrderList.get(0).getProductCode());
        Assert.assertEquals(generatedBakeryOrderList.get(0).getProductQuantity(), expectedBakeryOrderList.get(0).getProductQuantity());

    }

    @Test
    public void givenProductCodeIncorrectAndQuantity_whenOrderGeneratorIsCalled_thenResponseContainsRequestedCodeNameAndAmount() {

        Mockito.when(priceCalculatorMock.calculateTotalPrice(Mockito.any())).thenReturn(new BigDecimal(0));
        Mockito.when(packageGeneratorMock.calculatePackages(Mockito.any(), Mockito.any())).thenReturn(new ArrayList<>());
        Mockito.when(messageGenerator.generateMessage(Mockito.any(), Mockito.any())).thenReturn(null);
        Mockito.when(messageGenerator.generateMessage(Mockito.any(), Mockito.any(), Mockito.anyList())).thenReturn(null);

        BakeryOrderRequest bakeryOrderRequest = new BakeryOrderRequest("IncorrectCode", "5");

        List<BakeryOrderRequest> bakeryOrderRequestList = new ArrayList<>();
        bakeryOrderRequestList.add(bakeryOrderRequest);

        List<BakeryOrder> expectedBakeryOrderList = new ArrayList<>();

        BakeryOrder expectedBakeryOrder = new BakeryOrder.Builder()
                .withProductCode("IncorrectCode")
                .withProductQuantity(5)
                .build();

        expectedBakeryOrderList.add(expectedBakeryOrder);


        List<BakeryOrder> generatedBakeryOrderList = generator.generateOrderList(bakeryOrderRequestList);

        Assert.assertEquals(generatedBakeryOrderList.get(0).getProductName(), expectedBakeryOrderList.get(0).getProductName());
        Assert.assertEquals(generatedBakeryOrderList.get(0).getProductCode(), expectedBakeryOrderList.get(0).getProductCode());
        Assert.assertEquals(generatedBakeryOrderList.get(0).getProductQuantity(), expectedBakeryOrderList.get(0).getProductQuantity());

    }

    @Test
    public void givenProductCodeIsNotAnNumber_whenOrderGeneratorIsCalled_NumberFormatExceptionIsThrown() {
        Mockito.when(priceCalculatorMock.calculateTotalPrice(Mockito.any())).thenReturn(new BigDecimal(0));
        Mockito.when(packageGeneratorMock.calculatePackages(Mockito.any(), Mockito.any())).thenReturn(new ArrayList<>());
        Mockito.when(messageGenerator.generateMessage(Mockito.any(), Mockito.any())).thenReturn(null);
        Mockito.when(messageGenerator.generateMessage(Mockito.any(), Mockito.any(), Mockito.anyList())).thenReturn(null);

        BakeryOrderRequest bakeryOrderRequest = new BakeryOrderRequest(VEGEMITE_SCROLL_CODE, "a");

        List<BakeryOrderRequest> bakeryOrderRequestList = new ArrayList<>();
        bakeryOrderRequestList.add(bakeryOrderRequest);

        Assertions.assertThrows(NumberFormatException.class, () -> generator.generateOrderList(bakeryOrderRequestList));

    }

    @Test
    public void givenMultipleProductCodesAndQuantities_whenOrderGeneratorIsCalled_thenResponseContainsCorrectCodeNamesAndAmounts() {
        Mockito.when(priceCalculatorMock.calculateTotalPrice(Mockito.any())).thenReturn(new BigDecimal(0));
        Mockito.when(packageGeneratorMock.calculatePackages(Mockito.any(), Mockito.any())).thenReturn(new ArrayList<>());
        Mockito.when(messageGenerator.generateMessage(Mockito.any(), Mockito.any())).thenReturn(null);
        Mockito.when(messageGenerator.generateMessage(Mockito.any(), Mockito.any(), Mockito.anyList())).thenReturn(null);

        BakeryOrderRequest bakeryOrderRequest1 = new BakeryOrderRequest(VEGEMITE_SCROLL_CODE, "6");
        BakeryOrderRequest bakeryOrderRequest2 = new BakeryOrderRequest(BLUEBERRY_MUFFIN_CODE, "7");
        BakeryOrderRequest bakeryOrderRequest3 = new BakeryOrderRequest(CROISSANT_CODE, "8");

        List<BakeryOrderRequest> bakeryOrderRequestList = new ArrayList<>();
        bakeryOrderRequestList.add(bakeryOrderRequest1);
        bakeryOrderRequestList.add(bakeryOrderRequest2);
        bakeryOrderRequestList.add(bakeryOrderRequest3);

        List<BakeryOrder> expectedBakeryOrderList = new ArrayList<>();
        BakeryOrder expectedBakeryOrder1 = new BakeryOrder.Builder()
                .withProductName(VEGEMITE_SCROLL_NAME)
                .withProductCode(VEGEMITE_SCROLL_CODE)
                .withProductQuantity(6)
                .build();

        BakeryOrder expectedBakeryOrder2 = new BakeryOrder.Builder()
                .withProductName(BLUEBERRY_MUFFIN_NAME)
                .withProductCode(BLUEBERRY_MUFFIN_CODE)
                .withProductQuantity(7)
                .build();

        BakeryOrder expectedBakeryOrder3 = new BakeryOrder.Builder()
                .withProductName(CROISSANT_NAME)
                .withProductCode(CROISSANT_CODE)
                .withProductQuantity(8)
                .build();

        expectedBakeryOrderList.add(expectedBakeryOrder1);
        expectedBakeryOrderList.add(expectedBakeryOrder2);
        expectedBakeryOrderList.add(expectedBakeryOrder3);

        List<BakeryOrder> generatedBakeryOrderList = generator.generateOrderList(bakeryOrderRequestList);

        Assert.assertEquals(generatedBakeryOrderList.get(0).getProductName(), expectedBakeryOrderList.get(0).getProductName());
        Assert.assertEquals(generatedBakeryOrderList.get(0).getProductCode(), expectedBakeryOrderList.get(0).getProductCode());
        Assert.assertEquals(generatedBakeryOrderList.get(0).getProductQuantity(), expectedBakeryOrderList.get(0).getProductQuantity());

        Assert.assertEquals(generatedBakeryOrderList.get(1).getProductName(), expectedBakeryOrderList.get(1).getProductName());
        Assert.assertEquals(generatedBakeryOrderList.get(1).getProductCode(), expectedBakeryOrderList.get(1).getProductCode());
        Assert.assertEquals(generatedBakeryOrderList.get(1).getProductQuantity(), expectedBakeryOrderList.get(1).getProductQuantity());

        Assert.assertEquals(generatedBakeryOrderList.get(2).getProductName(), expectedBakeryOrderList.get(2).getProductName());
        Assert.assertEquals(generatedBakeryOrderList.get(2).getProductCode(), expectedBakeryOrderList.get(2).getProductCode());
        Assert.assertEquals(generatedBakeryOrderList.get(2).getProductQuantity(), expectedBakeryOrderList.get(2).getProductQuantity());
    }
}