package com.mentorship.tickets.service;

import com.mentorship.tickets.exception.EntityNotFoundException;
import com.mentorship.tickets.mapper.BaseMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;

public abstract class BaseServiceImpl<E, D,
        M extends BaseMapper<E,D>,
        R extends JpaRepository<E, Integer>> implements BaseService<D>{

    protected final R repository;
    protected final M mapper;

    protected BaseServiceImpl(R repository, M mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
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
        return entityOptional.map(mapper::mapToDto).orElseThrow(
                () -> new EntityNotFoundException("No entity is found with ID: " + id)
        );
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        D dto = findOneById(id);
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public List<D> saveMultipleItems(List<D> dTOs) {
        List<E> entitiesList = dTOs
                .stream()
                .map(mapper::mapToEntity)
                .toList();
        entitiesList
                .forEach(repository::save);
        return entitiesList
                .stream()
                .map(mapper::mapToDto)
                .toList();
    }

    @Override
    //@Transactional
    public void saveMultipleItemsAsynchronously(List<D> dTOs) {
        final int NUMBER_OF_THREADS = 10; // Number of threads in the pool
        final int BATCH_SIZE = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        for (int i = 0; i < dTOs.size(); i += BATCH_SIZE) {
            List<D> batch = dTOs.subList(i, Math.min(i + BATCH_SIZE, dTOs.size()));
            executorService.submit(() -> {
                for (D b : batch) {
                    this.save(b);
                }
            });
        }
        executorService.shutdown();
    }

    @Override
    public Page<D> findpage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable)
                .map(mapper::mapToDto);
    }
}
