package com.mentorship.tickets.service;

import com.mentorship.tickets.dto.TaskDto;
import com.mentorship.tickets.entity.Task;
import com.mentorship.tickets.mapper.TaskMapper;
import com.mentorship.tickets.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService extends BaseServiceImpl<Task, TaskDto, TaskMapper, TaskRepository>{

    @Autowired
    public TaskService(TaskRepository repository, TaskMapper mapper) {
        super(repository, mapper);
    }
}
