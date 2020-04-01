package figi.spring.restfulwebservices.services;

import java.util.List;
import java.util.Optional;

public interface CrudService<T, ID> {

    List<T> findAll();

    T save(T object);

    Optional<T> findById(ID id);

    void delete(T object);

    boolean deleteById(ID id);
}
