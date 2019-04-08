package com.bakery.service;

import com.bakery.model.Packing;

import java.math.BigDecimal;
import java.util.List;

public interface PriceCalculator {
    BigDecimal calculateTotalPrice(List<Packing> packings);

}
