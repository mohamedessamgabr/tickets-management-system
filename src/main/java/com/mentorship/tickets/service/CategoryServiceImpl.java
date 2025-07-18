package com.mentorship.tickets.service;

import com.mentorship.tickets.dto.CategoryDto;
import com.mentorship.tickets.entity.Category;
import com.mentorship.tickets.exception.CategoryNotFoundException;
import com.mentorship.tickets.mapper.CategoryMapper;
import com.mentorship.tickets.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
    private final Logger logger = LogManager.getLogger(CategoryServiceImpl.class);
    @Override
    public CategoryDto saveCategory(CategoryDto dto) {
        logger.info("saving a category");
        return categoryMapper.mapToDto(categoryRepository
                .save(categoryMapper.mapToEntity(dto)));
    }

    @Override
    public List<CategoryDto> findAllCategories() {
        logger.info("start getting all categories");
        return categoryRepository
                .findAll()
                .stream()
                .map(categoryMapper::mapToDto)
                .toList();
    }

    @Override
    @Cacheable(value = "category", key = "#id")
    public CategoryDto findCategoryById(Integer id) {
        logger.info("Start getting the category of id {} from database", id);
        return categoryMapper.mapToDto(
                categoryRepository.findById(id).orElseThrow(
                        () -> new CategoryNotFoundException("No Category is found with ID: "  + id)));
    }

    @Override
    @CacheEvict(value = "category", key = "#id")
    public void deleteCategoryById(Integer id) {
        logger.info("Start deleting the category of id {} from database", id);
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
