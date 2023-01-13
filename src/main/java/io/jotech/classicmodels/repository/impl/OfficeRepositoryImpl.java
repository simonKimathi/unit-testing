package io.jotech.classicmodels.repository.impl;


import io.jotech.classicmodels.entity.Office;
import io.jotech.classicmodels.repository.OfficeRepository;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

public class OfficeRepositoryImpl extends JpaRepositoryImpl<Office, String> implements OfficeRepository {


    @Inject
    private EntityManager entityManager;

    protected OfficeRepositoryImpl() {
        super(Office.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

}
