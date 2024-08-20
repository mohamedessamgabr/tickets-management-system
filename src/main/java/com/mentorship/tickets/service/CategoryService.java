package com.mentorship.tickets.service;

import com.mentorship.tickets.dto.CategoryDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    CategoryDto saveCategory(CategoryDto dto);
    List<CategoryDto> findAllCategories();
    CategoryDto findCategoryById(Integer id);
    void deleteCategoryById(Integer id);
    List<CategoryDto> saveBulkOfCategories(List<CategoryDto> dTOs);
    Page<CategoryDto> findPageOfCategories(int page, int size);
}
