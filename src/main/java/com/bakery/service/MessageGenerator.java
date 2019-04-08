package com.bakery.service;

import com.bakery.model.BakeryAvailableProduct;
import com.bakery.model.Packing;

import java.util.List;

public interface MessageGenerator {


    String generateMessage(BakeryAvailableProduct currentProduct, Integer productQuantity);

    String generateMessage(BakeryAvailableProduct currentProduct, Integer productQuantity, List<Packing> packings);

}
