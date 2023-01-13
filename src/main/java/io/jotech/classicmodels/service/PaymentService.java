package io.jotech.classicmodels.service;


import java.util.List;

import io.jotech.classicmodels.entity.Payment;

public interface PaymentService {
    List<Payment> getAllPayments(Integer start, Integer limit);
}
