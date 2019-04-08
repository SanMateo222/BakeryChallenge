package com.bakery.repository;

import com.bakery.model.BakeryAvailableProduct;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Component
@Service
public class BakeryProductRepository {

    private static final String BAKERY_PRODUCTS_DATABASE_JSON = "/BakeryProductsDatabase.json";
    private static List<BakeryAvailableProduct> availableProducts;

    static {

        try {
            Reader reader = new InputStreamReader(BakeryProductRepository.class.getResourceAsStream(BAKERY_PRODUCTS_DATABASE_JSON));

            ObjectMapper objectMapper = new ObjectMapper();
            availableProducts = objectMapper.readValue(reader, new TypeReference<List<BakeryAvailableProduct>>() {
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<BakeryAvailableProduct> findAll() {
        return new ArrayList<>(availableProducts);
    }

    public BakeryAvailableProduct findByProductCode(String code) {
        return availableProducts.stream()
                .filter(product -> product.getProductCode().equals(code))
                .findFirst()
                .orElse(null);
    }
}


