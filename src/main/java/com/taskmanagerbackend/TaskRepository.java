package com.taskmanagerbackend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * The interface Task repository.
 */
public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {

    /**
     * Search tasks by priority list.
     *
     * @param priority the priority
     * @return the list
     */
    @Query(value = "SELECT *FROM task_manager WHERE task_priority = :priority ", nativeQuery = true)
    List<TaskEntity> searchTasksByPriority(@Param("priority") int priority);

    /**
     * Exists by task name boolean.
     *
     * @param taskName the task name
     * @return the boolean
     */
    boolean existsByTaskName(String taskName);
}
