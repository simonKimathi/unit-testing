package io.jotech.classicmodels.service.impl;


import java.util.List;

import io.jotech.classicmodels.entity.Payment;
import io.jotech.classicmodels.repository.PaymentRepository;
import io.jotech.classicmodels.service.PaymentService;
import jakarta.inject.Inject;

public class PaymentServiceImpl implements PaymentService {
    @Inject
    private PaymentRepository paymentRepository;
    @Override
    public List<Payment> getAllPayments(Integer start, Integer limit) {
        return paymentRepository.listAll(start, limit);
    }
}
