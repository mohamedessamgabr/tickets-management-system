package com.mentorship.tickets.service;

import com.mentorship.tickets.dto.TaskDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TaskService {
    TaskDto saveTask(TaskDto dto);
    List<TaskDto> findAllTasks();
    TaskDto findTaskById(Integer id);
    void deleteTaskById(Integer id);
    List<TaskDto> saveBulkOfTasks(List<TaskDto> dTOs);
    List<TaskDto> saveBulkOfTasksAsynchronously(List<TaskDto> dTOs);
    Page<TaskDto> findPageOfTasks(int page, int size);
    void saveBulkOfTasksNoReturn(List<TaskDto> dTOs);
    void saveBulkOfTasksAsynchronouslyNoReturn(List<TaskDto> dTOs);
}
