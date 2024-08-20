package com.mentorship.tickets.controller;

import com.mentorship.tickets.dto.CategoryDto;
import com.mentorship.tickets.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryRestController {

    private final CategoryService categoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<CategoryDto>> findAllCategories() {
        return ResponseEntity.ok(categoryService.findAllCategories());
    }

    @GetMapping("/page")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<CategoryDto>> findPageOfCategories(@RequestParam(defaultValue = "1") final int page,
                                            @RequestParam(defaultValue = "10") final int size) {
        return ResponseEntity.ok(categoryService.findPageOfCategories(page, size));
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CategoryDto> findCategoryById(@PathVariable final Integer id) {
        return ResponseEntity.ok(categoryService.findCategoryById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CategoryDto> saveCategory(@RequestBody @Valid CategoryDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categoryService.saveCategory(dto));
    }

    @PostMapping("/bulk")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<List<CategoryDto>> saveBulkOfCategories(@RequestBody List<@Valid CategoryDto> categoryDTOs) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categoryService.saveBulkOfCategories(categoryDTOs));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable final Integer id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity
                .noContent()
                .build();
    }

}
