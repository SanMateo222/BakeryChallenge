package com.bakery.service;

import com.bakery.model.BakeryAvailableProduct;
import com.bakery.model.Pack;
import com.bakery.model.Packing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PackageGeneratorImpl implements PackageGenerator {

    @Autowired
    private PackageCompositionGenerator packageCompositionGenerator;

    public List<Packing> calculatePackages(BakeryAvailableProduct currentProduct, Integer quantity) {

        List<Packing> packings = new ArrayList<>();

        List<Integer> resultList = packageCompositionGenerator.generatePackageComposition(currentProduct, quantity);

        Map<Integer, Long> counts = resultList.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));

        currentProduct.getAvailablePacks().forEach(pack -> addPacking(packings, counts, pack));

        return packings;
    }

    private void addPacking(List<Packing> packings, Map<Integer, Long> counts, Pack pack) {
        if (counts.get(pack.getProductsInPackage()) != null) {
            Packing packing = new Packing(pack, counts.get(pack.getProductsInPackage()).intValue());
            packings.add(packing);
        }
    }

}
