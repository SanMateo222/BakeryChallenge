package com.bakery.model;

import java.math.BigDecimal;

public class Pack {

    public Pack() {
    }

    private BigDecimal packagePrice;
    private Integer productsInPackage;

    public Pack(BigDecimal packagePrice, Integer productsInPackage) {
        this.packagePrice = packagePrice;
        this.productsInPackage = productsInPackage;
    }

    public Integer getProductsInPackage() {
        return productsInPackage;
    }

    public void setProductsInPackage(Integer productsInPackage) {
        this.productsInPackage = productsInPackage;
    }

    public BigDecimal getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(BigDecimal packagePrice) {
        this.packagePrice = packagePrice;
    }
}
