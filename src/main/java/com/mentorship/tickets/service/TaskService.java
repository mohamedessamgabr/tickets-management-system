package com.mentorship.tickets.service;

import com.mentorship.tickets.dto.TaskDto;
import com.mentorship.tickets.entity.Task;
import com.mentorship.tickets.exception.TaskNotFoundException;
import com.mentorship.tickets.mapper.TaskMapper;
import com.mentorship.tickets.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService extends BaseServiceImpl<Task, TaskDto, TaskMapper, TaskRepository>{

    public TaskService(TaskRepository repository, TaskMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public TaskDto findOneById(Integer id) {
        TaskDto taskDto = super.findOneById(id);
        if (taskDto == null) {
            throw new TaskNotFoundException("No task found with id: " + id);
        }
        return taskDto;
    }
}
