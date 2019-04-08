package com.bakery.service;

import com.bakery.model.BakeryAvailableProduct;
import com.bakery.model.Packing;

import java.util.List;

public interface PackageGenerator {
    List<Packing> calculatePackages(BakeryAvailableProduct currentProduct, Integer quantity);
}
