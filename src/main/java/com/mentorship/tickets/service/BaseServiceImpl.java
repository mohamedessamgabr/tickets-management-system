package com.mentorship.tickets.service;

import com.mentorship.tickets.mapper.BaseMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;

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
    @Transactional
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
    @Transactional
    public List<D> saveMultipleItemsAsynchronously(List<D> dTOs) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<D> result = new ArrayList<>();
        List<Future<D>> futures = new ArrayList<>();
        for(D dto: dTOs) {
            Callable<D> task = () -> this.save(dto);
            Future<D> future = executorService.submit(task);
            futures.add(future);
        }
        executorService.shutdown();
        for (Future<D> future : futures) {
            try{
                result.add(future.get());
            } catch (Exception e) {
                throw new RuntimeException("Persisting error");
            }
        }
        return result;
    }

    @Override
    public Page<D> findpage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable)
                .map(mapper::mapToDto);
    }
}
