package com.taskmanagerbackend;

import com.taskmanagerbackend.exceptions.DuplicateRecordException;
import com.taskmanagerbackend.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
class TaskServiceImplTest {

    @Autowired
    private TaskServiceImpl taskService;

    @MockBean
    private TaskRepository taskRepository;

    private TaskEntity taskEntity;

    @BeforeEach
    public void setup() {
        taskEntity = new TaskEntity();
        taskEntity.setTaskName("Learn a new Language");
        taskEntity.setId(1);
        taskEntity.setTaskPriority(1);
        taskEntity.setTaskOrder(1);
    }

    @Test
    void searchTasksByPriority() {
        List<TaskEntity> entityList = new ArrayList<>();
        entityList.add(taskEntity);

        Mockito.when(taskRepository.searchTasksByPriority(Mockito.anyInt())).thenReturn(entityList);
        List<TaskEntity> responseList = taskService.searchTasksByPriority(1);
        assertEquals(1, responseList.size());
        Mockito.verify(taskRepository).searchTasksByPriority(Mockito.anyInt());
    }

    @Test
    void deleteTask() {
        Mockito.when(taskRepository.existsById(Mockito.any())).thenReturn(true);
        Mockito.doNothing().when(taskRepository).deleteById(Mockito.anyInt());
        taskService.deleteTask(1);
        Mockito.verify(taskRepository).deleteById(Mockito.anyInt());
    }

    @Test
    void deleteTask_Exception() {
        Mockito.when(taskRepository.existsById(Mockito.any())).thenReturn(false);
        assertThrows(NotFoundException.class, () ->
                taskService.deleteTask(1));
        Mockito.verify(taskRepository, Mockito.never()).deleteById(Mockito.anyInt());
    }

    @Test
    void addTask() {
        Mockito.when(taskRepository.existsByTaskName(Mockito.anyString())).thenReturn(false);
        Mockito.when(taskRepository.save(Mockito.any())).thenReturn(taskEntity);
        assertEquals(taskEntity.getTaskName(), taskService.addTask(taskEntity).getTaskName());
        Mockito.verify(taskRepository).save(Mockito.any());
    }

    @Test
    void addTask_Exception() {
        Mockito.when(taskRepository.existsByTaskName(Mockito.anyString())).thenReturn(true);
        assertThrows(DuplicateRecordException.class, () ->
                taskService.addTask(taskEntity));
        Mockito.verify(taskRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void getAllTask() {
        List<TaskEntity> entityList = new ArrayList<>();
        entityList.add(taskEntity);

        Mockito.when(taskRepository.findAll()).thenReturn(entityList);
        List<TaskEntity> responseList = taskService.getAllTask();
        assertEquals(1, responseList.size());
        Mockito.verify(taskRepository).findAll();
    }
}