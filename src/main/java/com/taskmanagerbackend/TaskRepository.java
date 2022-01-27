package com.taskmanagerbackend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {

    @Query(value = "SELECT *FROM task_manager WHERE task_priority = :priority ", nativeQuery = true)
    List<TaskEntity> searchTasksByPriority(@Param("priority") int priority);
}
