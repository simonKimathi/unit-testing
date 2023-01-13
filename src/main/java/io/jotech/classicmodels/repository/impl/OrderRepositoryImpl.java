package io.jotech.classicmodels.repository.impl;


import io.jotech.classicmodels.entity.Order;
import io.jotech.classicmodels.repository.OrderRepository;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

public class OrderRepositoryImpl extends JpaRepositoryImpl<Order, Integer> implements OrderRepository {
    @Inject
    private EntityManager entityManager;

    protected OrderRepositoryImpl() {
        super(Order.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
