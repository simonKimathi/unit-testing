package io.jotech.classicmodels.repository.impl;


import io.jotech.classicmodels.entity.OrderDetail;
import io.jotech.classicmodels.entity.OrderDetailID;
import io.jotech.classicmodels.repository.OrderDetailRepository;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

public class OrderDetailRepositoryImpl extends JpaRepositoryImpl<OrderDetail, OrderDetailID> implements OrderDetailRepository {


    @Inject
    private EntityManager entityManager;

    protected OrderDetailRepositoryImpl() {
        super(OrderDetail.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
