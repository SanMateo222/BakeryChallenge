package com.bakery.service;

import com.bakery.model.BakeryAvailableProduct;
import com.bakery.model.Packing;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageGeneratorImpl implements MessageGenerator {

    public static final String NON_EXISTING_PRODUCT = "Requested product code does not exist in database.";
    public static final String NO_PRODUCTS_ORDERED = "Requested quantity is zero, buy more!:)";
    public static final String ORDER_BELOW_SMALLEST_PACKAGE = "Requested amount of product cannot be purchased as it is smaller than our current smallest package. Please select higher amount of product.";
    public static final String ORDER_CANNOT_FIT_INTO_PACKAGES = "Requested amount of product cannot be purchased with our current packed offerings. Please select different amount of products.";


    public String generateMessage(BakeryAvailableProduct currentProduct, Integer productQuantity) {
        if (currentProduct == null) {
            return NON_EXISTING_PRODUCT;
        }
        if (productQuantity == 0) {
            return NO_PRODUCTS_ORDERED;
        }
        return null;
    }

    public String generateMessage(BakeryAvailableProduct currentProduct, Integer productQuantity, List<Packing> packings) {
        if (packings.isEmpty()) {
            if (currentProduct.findSmallestPack() > productQuantity) {
                return ORDER_BELOW_SMALLEST_PACKAGE;
            } else {
                return ORDER_CANNOT_FIT_INTO_PACKAGES;
            }
        }
        return null;
    }
}
