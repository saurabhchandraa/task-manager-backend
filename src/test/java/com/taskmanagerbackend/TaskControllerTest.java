package com.taskmanagerbackend;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

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
    void findAllTasks() throws Exception {
        List<TaskEntity> entityList = new ArrayList<>();
        entityList.add(taskEntity);
        Mockito.when(taskService.getAllTask()).thenReturn(entityList);
        mockMvc.perform(MockMvcRequestBuilders.get("/allTasks")).andExpect(status().isOk());
    }

    @Test
    void searchTasksByPriority()  throws Exception {
        List<TaskEntity> entityList = new ArrayList<>();
        entityList.add(taskEntity);
        Mockito.when(taskService.searchTasksByPriority(Mockito.anyInt())).thenReturn(entityList);
        mockMvc.perform(MockMvcRequestBuilders.get("/tasks/priority/1")).andExpect(status().isOk());
    }

    @Test
    void addTask() throws Exception {
        TaskDto taskDto = new TaskDto();
        taskDto.setTaskName("Learn a new Language");
        taskDto.setId(1);
        taskDto.setTaskPriority(1);
        taskDto.setTaskOrder(1);
        String jsonStr = new ObjectMapper().writeValueAsString(taskDto);
        Mockito.when(taskService.addTask(Mockito.any())).thenReturn(taskEntity);
        mockMvc.perform(MockMvcRequestBuilders.post("/tasks").contentType(MediaType.APPLICATION_JSON)
                .content(jsonStr)).andExpect(status().isCreated());
    }

    @Test
    void deleteTask() throws Exception {
        Mockito.doNothing().when(taskService).deleteTask(Mockito.anyInt());
        mockMvc.perform(MockMvcRequestBuilders.delete("/tasks/1")).andExpect(status().isNoContent());
    }
}

