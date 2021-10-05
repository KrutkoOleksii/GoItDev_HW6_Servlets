package ua.goit.service;

import ua.goit.model.BaseEntity;
import ua.goit.repository.Factory;

import java.util.List;
import java.util.Optional;

public abstract class BaseService <ID, E extends BaseEntity<ID>> {

    public E createEntity(Class<E> aClass, E e) {
        return Factory.of(aClass).save(e);
    }

    public E readEntity(Class<E> aClass, ID id) {
        return Factory.of(aClass).getOne(id);
    }

    public E updateEntity(Class<E> aClass, E e) {
        return Factory.of(aClass).save(e);
    }

    public void deleteEntity(Class<E> aClass, ID id) {
        Factory.of(aClass).deleteById(id);
    }

    public List<E> readAll(Class<E> aClass) {
        return Factory.of(aClass).findAll();
    }

    public Optional<E> findById(Class<E> aClass, ID id) {
        return Factory.of(aClass).findById(id);
    }

    public Optional<E> findByName(Class<E> aClass, String name) {
        return Factory.of(aClass).findByName(name);
    }
}
