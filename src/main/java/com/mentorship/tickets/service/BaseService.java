package com.mentorship.tickets.service;

import java.util.List;

public interface BaseService<D> {
    D save(D dto);
    List<D> findAll();
    D findOneById(Integer id);
    void deleteById(Integer id);
    List<D> saveMultipleItems(List<D> dTOs);
}
