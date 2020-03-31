package figi.spring.restfulwebservices.services;

import java.util.List;

public interface CrudService<T, ID> {

    List<T> findAll();

    T save(T object);

    T findById(ID id);

    void delete(T object);

    boolean deleteById(ID id);
}
