package com.bakery.model;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class BakeryAvailableProduct extends BakeryProduct {

    private List<Pack> availablePacks;

    public List<Pack> getAvailablePacks() {
        return availablePacks;
    }


    public void setAvailablePacks(List<Pack> availablePacks) {
        this.availablePacks = availablePacks;
    }

    public Integer findSmallestPack() {
        return this.getAvailablePacks().stream()
                .min(Comparator.comparing(Pack::getProductsInPackage))
                .orElseThrow(NoSuchElementException::new)
                .getProductsInPackage();
    }

    public Integer calculateProductsInPackage(Integer index) {
        return index < 0 ? null : this.getAvailablePacks().get(index).getProductsInPackage();
    }

    public Integer getAvailablePackIndexByProductAmount(Integer currentPackProductAmount) {
        for (int packIndex = 0; packIndex < this.getAvailablePacks().size(); packIndex++) {
            if (currentPackProductAmount.equals(this.getAvailablePacks().get(packIndex).getProductsInPackage())) {
                return packIndex;
            }
        }
        return -1;
    }
}
