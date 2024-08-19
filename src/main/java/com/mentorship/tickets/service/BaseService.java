package com.mentorship.tickets.service;

import org.springframework.data.domain.Page;

import java.util.List;

public interface BaseService<D> {
    D save(D dto);
    List<D> findAll();
    D findOneById(Integer id);
    void deleteById(Integer id);
    List<D> saveMultipleItems(List<D> dTOs);
    List<D> saveMultipleItemsAsynchronously(List<D> dTOs);
    Page<D> findpage(int page, int size);

}
