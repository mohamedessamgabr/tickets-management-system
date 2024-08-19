package com.mentorship.tickets.mapper;

import com.mentorship.tickets.dto.CategoryDto;
import com.mentorship.tickets.entity.Category;
import com.mentorship.tickets.exception.CategoryAlreadyExistsException;
import com.mentorship.tickets.repository.CategoryRepository;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper implements BaseMapper<Category, CategoryDto>{

    @Autowired
    private CategoryRepository categoryRepository;

    @Mapping(target = "id", source = "category.id")
    @Mapping(target = "name", source = "category.name")
    @Override
    public abstract CategoryDto mapToDto(Category category);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "categoryDto.name")
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updated", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Override
    public abstract Category mapToEntity(CategoryDto categoryDto);

    @BeforeMapping
    public void validateCategoryBeforeMappingToEntity(@MappingTarget Category category, CategoryDto categoryDto) {
        if (categoryRepository.findByName(categoryDto.getName()).isPresent()) {
            throw new CategoryAlreadyExistsException("Category with name " + categoryDto.getName() + " already exists");
        }
    }
}
