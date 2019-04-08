package com.bakery.service;

import com.bakery.model.BakeryAvailableProduct;
import com.bakery.model.CompositionData;
import com.bakery.model.Pack;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class PackageCompositionGeneratorImpl implements PackageCompositionGenerator {

    private static BakeryAvailableProduct currentProduct;
    private static final int ZERO = 0;
    private static final int ONE = 1;

    public List<Integer> generatePackageComposition(BakeryAvailableProduct availableProduct, Integer quantity) {
        List<Integer> resultList = new ArrayList<>();
        currentProduct = availableProduct;
        Integer index = currentProduct.getAvailablePacks().size() - ONE;
        CompositionData data = new CompositionData(index, quantity, resultList);

        if (!currentProduct.getAvailablePacks().isEmpty()) {
            currentProduct.getAvailablePacks().sort(Comparator.comparing(Pack::getProductsInPackage));
            compositionAlgorithm(data);
        }

        return resultList;
    }

    private void compositionAlgorithm(CompositionData data) {

        if (indexAboveZero(data) && quantityAboveZero(data)) {

            Integer currentPackProductsQuantity = currentProduct.calculateProductsInPackage(data.getIndex());

            if (currentPackProductsQuantity != null) {

                quantityMatchesCurrentPack(data, currentPackProductsQuantity);
                quantityBiggerThanCurrentPack(data, currentPackProductsQuantity);
                quantitySmallerThanCurrentPack(data);

                continueWithSmallerPack(data);
            }
        }
    }

    private void quantityMatchesCurrentPack(CompositionData data, Integer currentPackProductsQuantity) {
        if (currentPackProductsQuantity.equals(data.getQuantity())) {
            data.addCurrentPack(data, currentPackProductsQuantity);
        }
    }

    private void quantityBiggerThanCurrentPack(CompositionData data, Integer currentPackProductsQuantity) {
        if (data.getQuantity() > currentPackProductsQuantity) {
            if (!isLastPack(data.getIndex()) && !nextRemainingQuantityCanFitSmallerPackage(data, currentPackProductsQuantity)) {
                continueWithSmallerPack(data);
            } else {
                data.addCurrentPack(data, currentPackProductsQuantity);
            }
            if (data.getQuantity() >= currentPackProductsQuantity) {
                compositionAlgorithm(data);
            }
        }
    }

    private void quantitySmallerThanCurrentPack(CompositionData data) {
        if (isLastPack(data.getIndex()) && quantityAboveZero(data) && !emptyResultList(data)) {
            if (firstResultIsSmallestPack(data) && currentProduct.findSmallestPack() > data.getQuantity()) {
                finishWithEmptyResultList(data);
            } else {
                startWithSmallerPack(data);
            }
        }
    }

    private void continueWithSmallerPack(CompositionData data) {
        if (!data.getQuantity().equals(ZERO)) {
            data.setIndex(data.getIndex() - ONE);
            compositionAlgorithm(data);
        }
    }

    private boolean emptyResultList(CompositionData data) {
        return data.getCompositionList().isEmpty();
    }

    private boolean quantityAboveZero(CompositionData data) {
        return data.getQuantity() > ZERO;
    }

    private void finishWithEmptyResultList(CompositionData data) {
        data.getCompositionList().clear();
        data.setQuantity(ZERO);
    }

    private void startWithSmallerPack(CompositionData data) {

        data.setIndex(getSmallerAvailablePackIndex(data.getCompositionList().get(ZERO)));

        removeLastPackOfResultList(data);

        compositionAlgorithm(data);
    }

    private void removeLastPackOfResultList(CompositionData data) {
        int lastIndexOfBiggerPack = data.getCompositionList().lastIndexOf(getBiggerExistingPackProductsAmount(data));
        while (data.getCompositionList().size() > lastIndexOfBiggerPack) {

            data.addQuantity(data.getCompositionList().get(data.getCompositionList().size() - ONE));

            data.getCompositionList().remove(data.getCompositionList().size() - ONE);
        }
    }

    private Integer getBiggerExistingPackProductsAmount(CompositionData data) {

        int valueToAdd = ONE;
        while (valueToAdd < data.getCompositionList().size()) {
            int productsInBiggerPackage = currentProduct.getAvailablePacks().get(data.getIndex() + valueToAdd).getProductsInPackage();

            if (data.getCompositionList().contains(productsInBiggerPackage)) {
                return productsInBiggerPackage;
            }
            valueToAdd++;
        }
        return null;
    }


    private boolean nextRemainingQuantityCanFitSmallerPackage(CompositionData data, Integer currentPackProductsQuantity) {
        Integer productsInSmallerPackage = currentProduct.calculateProductsInPackage(getSmallerAvailablePackIndex(currentPackProductsQuantity));

        return productsInSmallerPackage != null && (data.getQuantity() - currentPackProductsQuantity) >= productsInSmallerPackage;
    }

    private boolean indexAboveZero(CompositionData data) {
        return data.getIndex() >= ZERO;
    }

    private boolean firstResultIsSmallestPack(CompositionData data) {
        return currentProduct.findSmallestPack().equals(data.getCompositionList().get(ZERO));
    }

    private boolean isLastPack(Integer index) {
        return index - ONE < ZERO;
    }

    private Integer getSmallerAvailablePackIndex(Integer currentPackProductAmount) {
        Integer index = currentProduct.getAvailablePackIndexByProductAmount(currentPackProductAmount);
        return index == -ONE ? -ONE : index - ONE;
    }
}
