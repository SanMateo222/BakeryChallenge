package com.bakery.app;

public class BakeryOrderRequest {

    private String productCode;
    private String productQuantity;

    public BakeryOrderRequest(String productCode, String productAmount) {
        this.productCode = productCode;
        this.productQuantity = productAmount;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }


    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }
}
