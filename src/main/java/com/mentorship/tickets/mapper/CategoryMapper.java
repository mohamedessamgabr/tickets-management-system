package com.mentorship.tickets.mapper;

import com.mentorship.tickets.dto.CategoryDto;
import com.mentorship.tickets.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends BaseMapper<Category, CategoryDto>{

    @Mapping(target = "id", source = "category.id")
    @Mapping(target = "name", source = "category.name")
    @Override
    CategoryDto mapToDto(Category category);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "categoryDto.name")
    @Override
    Category mapToEntity(CategoryDto categoryDto);
}
