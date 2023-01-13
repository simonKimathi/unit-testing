package io.jotech.classicmodels.repository;


import io.jotech.classicmodels.entity.Payment;
import io.jotech.classicmodels.entity.PaymentId;

public interface PaymentRepository extends JpaRepository<Payment, PaymentId> {
}
