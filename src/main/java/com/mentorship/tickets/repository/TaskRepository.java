package com.mentorship.tickets.repository;

import com.mentorship.tickets.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
