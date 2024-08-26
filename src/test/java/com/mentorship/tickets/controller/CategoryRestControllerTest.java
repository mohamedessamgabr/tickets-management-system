package com.mentorship.tickets.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mentorship.tickets.dto.CategoryDto;
import com.mentorship.tickets.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(CategoryRestController.class)
class CategoryRestControllerTest {

    @MockBean
    private CategoryService categoryService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void findAllCategoriesReturnsDataTest() throws Exception {
        List<CategoryDto> categoryDtoList = List.of(
                CategoryDto.builder().id(1).name("Category1").build(),
                CategoryDto.builder().id(2).name("Category2").build(),
                CategoryDto.builder().id(3).name("Category3").build()
        );

        when(categoryService.findAllCategories()).thenReturn(categoryDtoList);
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/v1/categories"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(objectMapper.writeValueAsString(categoryDtoList)));
    }

    @Test
    void findAllCategoriesReturnsNothingTest() throws Exception {
        List<CategoryDto> categoryDtoList = List.of();

        when(categoryService.findAllCategories()).thenReturn(categoryDtoList);
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/v1/categories"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void findPageOfCategories() {
    }

    @Test
    void findCategoryById() {
    }

    @Test
    void saveCategory() {
    }

    @Test
    void saveBulkOfCategories() {
    }

    @Test
    void deleteCategoryById() {
    }
}