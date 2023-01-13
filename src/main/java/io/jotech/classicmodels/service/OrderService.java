package io.jotech.classicmodels.service;


import java.util.List;

import io.jotech.classicmodels.entity.Order;

public interface OrderService {
    List<Order> getAllOrders(Integer start, Integer limit);
}
