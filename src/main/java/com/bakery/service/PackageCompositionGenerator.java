package com.bakery.service;

import com.bakery.model.BakeryAvailableProduct;

import java.util.List;

public interface PackageCompositionGenerator {

    List<Integer> generatePackageComposition(BakeryAvailableProduct currentProduct, Integer quantity);

}
