package com.bakery.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.util.List;

public class BakeryOrder extends BakeryProduct {

    private Integer productQuantity;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private BigDecimal totalPrice;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Packing> packings;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Packing> getPackings() {
        return packings;
    }

    public void setPackings(List<Packing> packings) {
        this.packings = packings;
    }

    private BakeryOrder() {
    }

    public static class Builder {
        private String productName;
        private String productCode;
        private Integer productQuantity;
        private BigDecimal totalPrice;
        private List<Packing> packings;
        private String message;


        public Builder() {
        }

        public Builder withProductName(String productName) {
            this.productName = productName;
            return this;
        }

        public Builder withProductCode(String productCode) {
            this.productCode = productCode;
            return this;
        }

        public Builder withProductQuantity(Integer productQuantity) {
            this.productQuantity = productQuantity;
            return this;
        }

        public Builder withTotalPrice(BigDecimal totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Builder withPackages(List<Packing> packings) {
            this.packings = packings;
            return this;
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public BakeryOrder build() {
            BakeryOrder bakeryOrder = new BakeryOrder();
            bakeryOrder.setProductName(productName);
            bakeryOrder.setProductCode(productCode);
            bakeryOrder.productQuantity = this.productQuantity;
            bakeryOrder.totalPrice = this.totalPrice;
            bakeryOrder.packings = this.packings;
            bakeryOrder.message = this.message;
            return bakeryOrder;
        }

    }
}
