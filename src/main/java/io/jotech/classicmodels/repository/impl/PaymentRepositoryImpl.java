package io.jotech.classicmodels.repository.impl;


import io.jotech.classicmodels.entity.Payment;
import io.jotech.classicmodels.entity.PaymentId;
import io.jotech.classicmodels.repository.PaymentRepository;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

public class PaymentRepositoryImpl extends JpaRepositoryImpl<Payment, PaymentId> implements PaymentRepository {

    @Inject
    private EntityManager entityManager;
    protected PaymentRepositoryImpl() {
        super(Payment.class);
    }

    @Override
    protected EntityManager getEntityManager() {
   return entityManager; }
}
