package com.mentorship.tickets.mapper;

public interface BaseMapper <E, D>{
    D mapToDto(E e);
    E mapToEntity(D d);
}
