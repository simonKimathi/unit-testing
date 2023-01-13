package io.jotech.classicmodels.repository.impl;


import io.jotech.classicmodels.entity.Employee;
import io.jotech.classicmodels.repository.EmployeeRepository;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

public class EmployeeRepositoryImpl extends JpaRepositoryImpl<Employee, Integer> implements EmployeeRepository {

    @Inject
    private EntityManager entityManager;

    protected EmployeeRepositoryImpl() {
        super(Employee.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
