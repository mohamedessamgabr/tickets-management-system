package com.mentorship.tickets.service;

import com.mentorship.tickets.mapper.BaseMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class BaseServiceImpl<E, D,
        M extends BaseMapper<E,D>,
        R extends JpaRepository<E, Integer>> implements BaseService<D>{

    protected final R repository;
    protected final M mapper;

    protected BaseServiceImpl(R repository, M mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public D save(D dto) {
        E entity = mapper.mapToEntity(dto);
        repository.save(entity);
        return mapper.mapToDto(entity);
    }

    @Override
    public List<D> findAll() {
        return repository
                .findAll()
                .stream()
                .map(mapper::mapToDto)
                .toList();
    }

    @Override
    public D findOneById(Integer id) {
        Optional<E> entityOptional = repository.findById(id);
        return entityOptional.map(mapper::mapToDto).orElse(null);
    }

    @Override
    public void deleteById(Integer id) {
        D dto = findOneById(id);
        if (dto != null) {
            repository.deleteById(id);
        }
    }

    @Override
    @Transactional
    public List<D> saveMultipleItems(List<D> dTOs) {
        List<E> entitiesList = dTOs
                .stream()
                .parallel()
                .map(mapper::mapToEntity)
                .toList();
        entitiesList
                .stream()
                .parallel()
                .forEach(repository::save);
        return entitiesList
                .stream()
                .parallel()
                .map(mapper::mapToDto)
                .toList();
    }
}
