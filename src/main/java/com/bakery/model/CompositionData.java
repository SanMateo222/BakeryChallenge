package com.bakery.model;

import java.util.List;

public class CompositionData {

    private Integer index;
    private Integer quantity;
    private List<Integer> compositionList;

    public CompositionData(Integer index, Integer quantity, List<Integer> resultList) {
        this.index = index;
        this.quantity = quantity;
        this.compositionList = resultList;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<Integer> getCompositionList() {
        return compositionList;
    }

    public void setCompositionList(List<Integer> compositionList) {
        this.compositionList = compositionList;
    }

    public void addCurrentPack(CompositionData data, Integer currentPackProductsQuantity) {
        this.getCompositionList().add(currentPackProductsQuantity);
        this.setQuantity(data.getQuantity() - currentPackProductsQuantity);
    }

    public void addQuantity(Integer valueToAdd) {
        this.quantity = quantity + valueToAdd;
    }

}
