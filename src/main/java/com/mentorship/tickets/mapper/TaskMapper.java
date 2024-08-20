package com.mentorship.tickets.mapper;

import com.mentorship.tickets.dto.TaskDto;
import com.mentorship.tickets.entity.Category;
import com.mentorship.tickets.entity.Task;
import com.mentorship.tickets.exception.CategoryNotFoundException;
import com.mentorship.tickets.exception.EntityNotFoundException;
import com.mentorship.tickets.repository.CategoryRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class TaskMapper implements BaseMapper<Task, TaskDto> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Mapping(target = "id", source = "task.id")
    @Mapping(target = "name", source = "task.name")
    @Mapping(target = "description", source = "task.description")
    @Mapping(target = "dateFrom", source = "task.dateFrom", dateFormat = "dd-MM-yyyy")
    @Mapping(target = "dateTo", source = "task.dateTo", dateFormat = "dd-MM-yyyy")
    @Mapping(target = "category", source = "task.category.name")
    @Override
    public abstract TaskDto mapToDto(Task task);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "taskDto.name")
    @Mapping(target = "description", source = "taskDto.description")
    @Mapping(target = "dateFrom", source = "taskDto.dateFrom", dateFormat = "dd-MM-yyyy")
    @Mapping(target = "dateTo", source = "taskDto.dateTo", dateFormat = "dd-MM-yyyy")
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updated", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Override
    public abstract Task mapToEntity(TaskDto taskDto);

    @BeforeMapping
    public void setTheCategoryBeforeMappingToDto(@MappingTarget Task task, TaskDto dto) {
        Category category = categoryRepository.findByName(dto.getCategory()).orElseThrow(
                () -> new EntityNotFoundException("No Category with the name: " + dto.getCategory())
        );
        task.setCategory(category);
    }
}
