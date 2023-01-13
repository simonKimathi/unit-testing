package io.jotech.classicmodels.service.impl;


import java.util.List;

import io.jotech.classicmodels.entity.OrderDetail;
import io.jotech.classicmodels.repository.OrderDetailRepository;
import io.jotech.classicmodels.service.OrderDetailService;
import jakarta.inject.Inject;

public class OrderDetailServiceImpl implements OrderDetailService {


    @Inject
    private OrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderDetail> getAllOrderDetails(Integer start, Integer limit) {
        return orderDetailRepository.listAll(start, limit);
    }
}
