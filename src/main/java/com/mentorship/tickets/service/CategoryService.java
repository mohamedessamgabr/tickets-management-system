package com.mentorship.tickets.service;

import com.mentorship.tickets.dto.CategoryDto;
import com.mentorship.tickets.entity.Category;
import com.mentorship.tickets.mapper.CategoryMapper;
import com.mentorship.tickets.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends BaseServiceImpl<Category, CategoryDto,
        CategoryMapper, CategoryRepository> {
    public CategoryService(CategoryRepository repository, CategoryMapper mapper) {
        super(repository, mapper);
    }
}
