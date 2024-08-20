package com.mentorship.tickets.controller;

import com.mentorship.tickets.dto.TaskDto;
import com.mentorship.tickets.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskRestController {

    private final TaskService taskService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<TaskDto>> findAllTasks() {
        return ResponseEntity.ok(taskService.findAllTasks());
    }

    @GetMapping("/page")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<TaskDto>> findPageOfTasks(@RequestParam(defaultValue = "1") final int page,
                                                                  @RequestParam(defaultValue = "10") final int size) {
        return ResponseEntity.ok(taskService.findPageOfTasks(page, size));
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TaskDto> findTaskById(@PathVariable final Integer id) {
        return ResponseEntity.ok(taskService.findTaskById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TaskDto> saveTask(@RequestBody @Valid TaskDto taskDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(taskService.saveTask(taskDto));
    }

    @PostMapping("/bulk")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<List<TaskDto>> saveBulkOfTasks(@RequestBody List<@Valid TaskDto> taskDTOs) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(taskService.saveBulkOfTasks(taskDTOs));
    }

    @PostMapping("/bulk-async")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<List<TaskDto>> saveBulkOfTasksAsync(@RequestBody List<@Valid TaskDto> taskDTOs) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(taskService.saveBulkOfTasksAsynchronously(taskDTOs));
    }

    @PostMapping("/bulk-no-return")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> saveBulkOfTasksNoReturn(@RequestBody List<@Valid TaskDto> taskDTOs) {
        taskService.saveBulkOfTasksNoReturn(taskDTOs);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/bulk-async-no-return")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> saveBulkOfTasksAsyncNoReturn(@RequestBody List<@Valid TaskDto> taskDTOs) {
        taskService.saveBulkOfTasksAsynchronouslyNoReturn(taskDTOs);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable final Integer id) {
        taskService.deleteTaskById(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
