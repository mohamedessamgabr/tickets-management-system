package com.mentorship.tickets.service;

import com.mentorship.tickets.dto.TaskDto;
import com.mentorship.tickets.entity.Task;
import com.mentorship.tickets.exception.TaskNotFoundException;
import com.mentorship.tickets.mapper.TaskMapper;
import com.mentorship.tickets.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public TaskDto saveTask(TaskDto dto) {
        return taskMapper.mapToDto(
                taskRepository
                .save(taskMapper.mapToEntity(dto)));
    }

    @Override
    public List<TaskDto> findAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::mapToDto)
                .toList();
    }

    @Override
    public TaskDto findTaskById(Integer id) {
        return taskMapper
                .mapToDto(taskRepository
                        .findById(id).orElseThrow(
                                () -> new TaskNotFoundException("No task is found with ID: " + id)
                        ));
    }

    @Override
    public void deleteTaskById(Integer id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<TaskDto> saveBulkOfTasks(List<TaskDto> dTOs) {
        List<Task> tasks = dTOs
                .stream()
                .map(taskMapper::mapToEntity)
                .toList();
        return taskRepository.saveAll(tasks)
                .stream()
                .map(taskMapper::mapToDto)
                .toList();

    }

    @Override
    public List<TaskDto> saveBulkOfTasksAsynchronously(List<TaskDto> dTOs) {
        final int NUMBER_OF_THREADS = 10; // Number of threads in the pool
        final int BATCH_SIZE = 500;
        List<TaskDto> result = Collections.synchronizedList(new ArrayList<>());
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        for (int i = 0; i < dTOs.size(); i += BATCH_SIZE) {
            List<Task> batch = dTOs.subList(i, Math.min(i + BATCH_SIZE, dTOs.size()))
                    .stream()
                    .map(taskMapper::mapToEntity)
                    .toList();
            executorService.submit(() -> {
                result.addAll(taskRepository.saveAll(batch)
                        .stream()
                        .map(taskMapper::mapToDto)
                        .toList());
            });
        }
        executorService.shutdown();
        return result;
    }

    @Override
    public Page<TaskDto> findPageOfTasks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return taskRepository.findAll(pageable)
                .map(taskMapper::mapToDto);
    }

    @Override
    public void saveBulkOfTasksNoReturn(List<TaskDto> dTOs) {
        List<Task> tasks = dTOs
                .stream()
                .map(taskMapper::mapToEntity)
                .toList();
        taskRepository.saveAll(tasks);
    }

    @Override
    public void saveBulkOfTasksAsynchronouslyNoReturn(List<TaskDto> dTOs) {
        final int NUMBER_OF_THREADS = 10; // Number of threads in the pool
        final int BATCH_SIZE = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        for (int i = 0; i < dTOs.size(); i += BATCH_SIZE) {
            List<Task> batch = dTOs.subList(i, Math.min(i + BATCH_SIZE, dTOs.size()))
                    .stream()
                    .map(taskMapper::mapToEntity)
                    .toList();
            executorService.submit(() -> {
                taskRepository.saveAll(batch);
            });
        }
        executorService.shutdown();
    }
}
