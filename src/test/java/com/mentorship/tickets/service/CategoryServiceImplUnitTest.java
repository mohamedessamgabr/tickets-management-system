package com.mentorship.tickets.service;

import com.mentorship.tickets.dto.CategoryDto;
import com.mentorship.tickets.entity.Category;
import com.mentorship.tickets.exception.CategoryAlreadyExistsException;
import com.mentorship.tickets.exception.CategoryNotFoundException;
import com.mentorship.tickets.mapper.CategoryMapper;
import com.mentorship.tickets.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplUnitTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private Category category;
    private CategoryDto categoryDto;

    @BeforeEach
    public void setUp() {
        categoryDto = CategoryDto.builder().id(1).name("Category").build();
        category = new Category();
        category.setId(1);
        category.setName("Category");
    }

    @Test
    void saveCategorySuccess() {
        when(categoryMapper.mapToEntity(categoryDto)).thenReturn(category);
        when(categoryRepository.save(category)).thenReturn(category);
        when(categoryMapper.mapToDto(category)).thenReturn(categoryDto);

        CategoryDto categoryToBeSaved = categoryService.saveCategory(categoryDto);
        assertNotNull(categoryToBeSaved);
        assertEquals(categoryToBeSaved, categoryDto);
    }

    @Test
    void saveCategorySuccessThrowsCategoryAlreadyExistsException() {
        when(categoryMapper.mapToEntity(categoryDto)).thenThrow(CategoryAlreadyExistsException.class);
        assertThrows(CategoryAlreadyExistsException.class, () ->
                categoryService.saveCategory(categoryDto));
    }

    @Test
    void findAllCategoriesSuccess() {
        when(categoryMapper.mapToDto(category)).thenReturn(categoryDto);
        when(categoryRepository.findAll()).thenReturn(List.of(category));

        List<CategoryDto> categoryDtoList = categoryService.findAllCategories();
        assertEquals(1, categoryDtoList.size());
        assertEquals(categoryDtoList.get(0), categoryDto);
    }

    @Test
    void findCategoryByIdSuccess() {
        when(categoryMapper.mapToDto(category)).thenReturn(categoryDto);
        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));

        CategoryDto categoryDto1 = categoryService.findCategoryById(1);
        assertNotNull(categoryDto1);
        assertEquals(categoryDto1, categoryDto);
    }


    @Test
    void findCategoryByIdSThrowsCategoryNotFoundException() {
        when(categoryRepository.findById(2)).thenThrow(CategoryNotFoundException.class);
        assertThrows(CategoryNotFoundException.class, () ->
                categoryService.findCategoryById(2), "No Category is found with ID: 2");
    }
}