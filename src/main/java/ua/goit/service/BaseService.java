package ua.goit.service;

import ua.goit.model.BaseEntity;
import ua.goit.repository.BaseRepository;
import ua.goit.repository.Factory;

import java.util.List;
import java.util.Optional;

public abstract class BaseService <ID, E extends BaseEntity<ID>> {

    private final BaseRepository<ID,E> baseRepository;

    public BaseService (Class<E> aClass) {
        this.baseRepository = Factory.of(aClass);
    }

    public E createEntity(E e) {
        return baseRepository.save(e);
    }

    public E readEntity(ID id) {
        return baseRepository.getOne(id);
    }

    public E updateEntity(E e) {
        return baseRepository.save(e);
    }

    public void deleteEntity(ID id) {
        baseRepository.deleteById(id);
    }

    public List<E> readAll() {
        return baseRepository.findAll();
    }

    public Optional<E> findById(ID id) {
        return baseRepository.findById(id);
    }

    public List<E> findByName(String name) {
        return baseRepository.findByName(name);
    }
}
