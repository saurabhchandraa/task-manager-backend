package com.taskmanagerbackend;

import java.util.List;

public interface TaskService {
    List<TaskEntity> searchTasksByPriority(int priority);

    void deleteTask(int id);

    TaskEntity addTask(TaskEntity taskEntity);
}
