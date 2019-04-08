package com.bakery.service;

import com.bakery.app.BakeryOrderRequest;
import com.bakery.model.BakeryOrder;
import com.bakery.model.Pack;

import java.util.List;

public interface OrderGenerator {
    List<BakeryOrder> generateOrderList(List<BakeryOrderRequest> bakeryOrderRequestList);
    }
