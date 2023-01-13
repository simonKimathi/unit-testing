package io.jotech.classicmodels.service;


import java.util.List;

import io.jotech.classicmodels.entity.OrderDetail;

public interface OrderDetailService {
    List<OrderDetail> getAllOrderDetails(Integer start, Integer limit);
}
