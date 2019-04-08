package com.bakery.model;

import com.fasterxml.jackson.annotation.JsonInclude;

public class BakeryProduct {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String productName;

    private String productCode;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

}
