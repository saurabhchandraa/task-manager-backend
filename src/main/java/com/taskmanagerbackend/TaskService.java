package com.taskmanagerbackend;

import java.util.List;

/**
 * The interface Task service.
 */
public interface TaskService {
    /**
     * Search tasks by priority list.
     *
     * @param priority the priority
     * @return the list
     */
    List<TaskEntity> searchTasksByPriority(int priority);

    /**
     * Delete task.
     *
     * @param id the id
     */
    void deleteTask(int id);

    /**
     * Add task task entity.
     *
     * @param taskEntity the task entity
     * @return the task entity
     */
    TaskEntity addTask(TaskEntity taskEntity);

    /**
     * Gets all task.
     *
     * @return the all task
     */
    List<TaskEntity> getAllTask();
}
