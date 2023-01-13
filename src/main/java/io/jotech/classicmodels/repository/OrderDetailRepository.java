package io.jotech.classicmodels.repository;


import io.jotech.classicmodels.entity.OrderDetail;
import io.jotech.classicmodels.entity.OrderDetailID;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailID> {
}
