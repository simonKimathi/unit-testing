package io.jotech.classicmodels.repository.impl;

import java.util.List;
import java.util.stream.Stream;

import static jakarta.transaction.Transactional.TxType.REQUIRED;
import static jakarta.transaction.Transactional.TxType.SUPPORTS;

import io.jotech.classicmodels.repository.JpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;


@Transactional(SUPPORTS)
public abstract class JpaRepositoryImpl<T, ID> implements JpaRepository<T, ID> {
    private final Class<T> entityClass;

    protected JpaRepositoryImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    @Override
    public Long getCountOfEntity() {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<T> root = criteriaQuery.from(entityClass);
        Expression<Long> countExpression = builder.count(root);
        criteriaQuery.select(countExpression);
        TypedQuery<Long> typedStudentQuery = getEntityManager().createQuery(criteriaQuery);
        return typedStudentQuery.getSingleResult();
    }

    @Transactional(REQUIRED)
    @Override
    public T insert(T entity) {
        getEntityManager().persist(entity);
        return entity;
    }

    @Override
    public T read(ID id) {
        return getEntityManager().find(entityClass, id);
    }

    @Transactional(REQUIRED)
    @Override
    public T update(T entity) {
        return getEntityManager().merge(entity);
    }

    @Transactional(REQUIRED)
    public boolean delete(T entity) {
        getEntityManager().merge(entity);
        getEntityManager().remove(entity);
        return true;
    }

    @Override
    public List<T> listAll(Integer start,Integer limit) {
        CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
        cq.select(cq.from(entityClass));

        TypedQuery<T> query = getEntityManager().createQuery(cq);
        query.setFirstResult(start);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    @Override
    public Stream<T> streamAll() {
        var criteriaQuery = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
        criteriaQuery.select(criteriaQuery.from(entityClass));
        return getEntityManager().createQuery(criteriaQuery).getResultStream();
    }

    @Transactional(REQUIRED)
    @Override
    public boolean deleteById(ID id) {
        return this.delete(
                this.read(id)
        );
    }
}
