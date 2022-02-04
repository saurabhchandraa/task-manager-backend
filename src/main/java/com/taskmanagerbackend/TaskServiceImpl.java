package com.taskmanagerbackend;

import com.taskmanagerbackend.exceptions.DuplicateRecordException;
import com.taskmanagerbackend.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Task service.
 */
@Service
public class TaskServiceImpl implements TaskService {

    /**
     * The Task repository.
     */
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<TaskEntity> searchTasksByPriority(int priority) {
        return taskRepository.searchTasksByPriority(priority);
    }

    @Override
    public void deleteTask(int id) {
        if(taskRepository.existsById(id))
        {
            taskRepository.deleteById(id);
        }
        else {
            throw new NotFoundException("Task ID- "+id+" Not Found");
        }

    }

    @Override
    public TaskEntity addTask(TaskEntity taskEntity) {
        if(taskRepository.existsByTaskName(taskEntity.getTaskName())) {
            throw new DuplicateRecordException("Task Name- "+taskEntity.getTaskName()+" Already Exists");
        }
        return taskRepository.save(taskEntity);
    }

    @Override
    public List<TaskEntity> getAllTask() {
        return taskRepository.findAll();
    }
}
