package com.mentorship.tickets.controller;

import com.mentorship.tickets.dto.TaskDto;
import com.mentorship.tickets.entity.Task;
import com.mentorship.tickets.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskRestController extends BaseController<TaskDto>{
    @Autowired
    public TaskRestController(@Qualifier("taskService") BaseService<TaskDto> baseService) {
        super(baseService);
    }
}
