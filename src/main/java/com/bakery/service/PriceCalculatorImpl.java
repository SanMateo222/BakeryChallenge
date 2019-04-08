package com.bakery.service;

import com.bakery.model.Packing;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PriceCalculatorImpl implements PriceCalculator {

    public BigDecimal calculateTotalPrice(List<Packing> packings) {
        BigDecimal totalPrice = new BigDecimal(0);

        for (Packing pack : packings) {
            BigDecimal productPrice = pack.getPackagePrice();
            BigDecimal productQuantity = new BigDecimal(pack.getAmountOfPackages());

            totalPrice = totalPrice.add(productPrice.multiply(productQuantity));

        }
        return totalPrice.intValue() == 0 ? null : totalPrice;
    }
}
