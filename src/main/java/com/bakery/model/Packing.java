package com.bakery.model;

import java.math.BigDecimal;

public class Packing extends Pack {

    public Packing(BigDecimal packagePrice, Integer productsInPackage, Integer amountOfPackages) {
        super(packagePrice, productsInPackage);
        this.amountOfPackages = amountOfPackages;
    }

    public Packing(Pack pack, Integer amountOfPackages) {
        super(pack.getPackagePrice(), pack.getProductsInPackage());
        this.amountOfPackages = amountOfPackages;
    }

    private Integer amountOfPackages;

    public Integer getAmountOfPackages() {
        return amountOfPackages;
    }

    public void setAmountOfPackages(Integer amountOfPackages) {
        this.amountOfPackages = amountOfPackages;
    }
}
