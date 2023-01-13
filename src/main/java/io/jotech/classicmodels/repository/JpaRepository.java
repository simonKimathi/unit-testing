package io.jotech.classicmodels.repository;

import java.util.List;
import java.util.stream.Stream;

public interface JpaRepository<T,ID>  extends Repository<T, ID>{
     Long getCountOfEntity();

    T insert(T entity);
    T read(ID id);
    T update(T entity);
    boolean delete(T entity);
    List<T> listAll(Integer start,Integer limit);
    Stream<T> streamAll();

    boolean deleteById(ID id);
}
