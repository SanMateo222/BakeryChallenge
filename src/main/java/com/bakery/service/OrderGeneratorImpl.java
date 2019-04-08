package com.bakery.service;

import com.bakery.app.BakeryOrderRequest;
import com.bakery.model.BakeryAvailableProduct;
import com.bakery.model.BakeryOrder;
import com.bakery.model.Packing;
import com.bakery.repository.BakeryProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderGeneratorImpl implements OrderGenerator {

    private static final BakeryProductRepository bakeryProductRepository = new BakeryProductRepository();

    @Autowired
    private PriceCalculator priceCalculator;

    @Autowired
    private PackageGenerator packageGenerator;

    @Autowired
    private MessageGenerator messageGenerator;

    @Override
    public List<BakeryOrder> generateOrderList(List<BakeryOrderRequest> bakeryOrderRequestList) {

        List<BakeryOrder> generatedBakeryOrderList = new ArrayList<>();

        bakeryOrderRequestList.forEach(bakeryOrderRequest -> generatedBakeryOrderList.add(generateSingleOrder(bakeryOrderRequest)));

        return generatedBakeryOrderList;
    }


    private BakeryOrder generateSingleOrder(BakeryOrderRequest bakeryOrderRequest) {
        BakeryAvailableProduct currentProduct = bakeryProductRepository.findByProductCode(bakeryOrderRequest.getProductCode());

        Integer productQuantity = getOrderQuantity(bakeryOrderRequest);
        String productCode = bakeryOrderRequest.getProductCode();

        if (correctRequest(currentProduct, productQuantity)) {
            return generateCorrectOrder(currentProduct, productQuantity, productCode);
        } else {
            return generateIncorrectOrder(currentProduct, productQuantity, productCode);
        }
    }


    private BakeryOrder generateCorrectOrder(BakeryAvailableProduct currentProduct, Integer productQuantity, String productCode) {

        List<Packing> packings = packageGenerator.calculatePackages(currentProduct, productQuantity);

        return new BakeryOrder.Builder()
                .withProductName(currentProduct.getProductName())
                .withProductCode(productCode)
                .withProductQuantity(productQuantity)
                .withTotalPrice(priceCalculator.calculateTotalPrice(packings))
                .withPackages(packings)
                .withMessage(messageGenerator.generateMessage(currentProduct, productQuantity, packings))
                .build();
    }

    private BakeryOrder generateIncorrectOrder(BakeryAvailableProduct currentProduct, Integer productQuantity, String productCode) {
        return new BakeryOrder.Builder()
                .withProductCode(productCode)
                .withProductQuantity(productQuantity)
                .withMessage(messageGenerator.generateMessage(currentProduct, productQuantity))
                .build();
    }

    private boolean correctRequest(BakeryAvailableProduct currentProduct, Integer productQuantity) {
        return currentProduct != null && productQuantity > 0;
    }

    private Integer getOrderQuantity(BakeryOrderRequest bakeryOrderRequest) {
        try {
            int quantity = Integer.parseInt(bakeryOrderRequest.getProductQuantity());
            return (quantity <= 0) ? 0 : quantity;

        } catch (NumberFormatException ex) {
            throw new NumberFormatException("The quantity entered, " + bakeryOrderRequest.getProductQuantity() + " is invalid.");
        }
    }
}
