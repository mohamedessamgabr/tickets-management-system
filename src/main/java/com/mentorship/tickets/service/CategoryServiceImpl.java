package com.mentorship.tickets.service;

import com.mentorship.tickets.dto.CategoryDto;
import com.mentorship.tickets.entity.Category;
import com.mentorship.tickets.exception.CategoryNotFoundException;
import com.mentorship.tickets.mapper.CategoryMapper;
import com.mentorship.tickets.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDto saveCategory(CategoryDto dto) {
        return categoryMapper.mapToDto(categoryRepository
                .save(categoryMapper.mapToEntity(dto)));
    }

    @Override
    public List<CategoryDto> findAllCategories() {
        return categoryRepository
                .findAll()
                .stream()
                .map(categoryMapper::mapToDto)
                .toList();
    }

    @Override
    public CategoryDto findCategoryById(Integer id) {
        return categoryMapper.mapToDto(
                categoryRepository.findById(id).orElseThrow(
                        () -> new CategoryNotFoundException("No Category is found with ID: "  + id)));
    }

    @Override
    public void deleteCategoryById(Integer id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<CategoryDto> saveBulkOfCategories(List<CategoryDto> dTOs) {
        List<Category> categories = dTOs
                .stream()
                .map(categoryMapper::mapToEntity)
                .toList();
        return categoryRepository
                .saveAll(categories)
                .stream()
                .map(categoryMapper::mapToDto)
                .toList();
    }
    

    @Override
    public Page<CategoryDto> findPageOfCategories(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return categoryRepository.findAll(pageable)
                .map(categoryMapper::mapToDto);
    }
}
