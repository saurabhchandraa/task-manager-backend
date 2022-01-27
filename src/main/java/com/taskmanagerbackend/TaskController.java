package com.taskmanagerbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin("http://localhost:3000/")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TasksMapper mapper;

    @GetMapping(value="/tasks/{priority}", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<TaskDto>> searchTasksByPriority(@PathVariable int priority)
    {
        List<TaskEntity>  entityList = taskService.searchTasksByPriority(priority);

        List<TaskDto> dtoList = entityList.stream().map(mapper::toTaskDto).collect(Collectors.toList());
//        List<TaskDto> dtoList = new ArrayList<>();
//        for(TaskEntity entity: entityList) {
//            TaskDto dto = mapper.toTaskDto(entity);
//            dtoList.add(dto);
//        }

        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }

    @PostMapping(value="/tasks", consumes= {MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> addTask(HttpServletRequest request, @RequestBody TaskDto taskDto)
    {

        String location = "";
        String responseBody = "";
        TaskEntity taskEntity = mapper.toTaskEntity(taskDto);
        TaskEntity entityResponse = taskService.addTask(taskEntity);

        location = request.getRequestURI() + "/"+entityResponse.getId();
        responseBody = String.format("{\"id\":%d}" ,taskEntity.getId());

        return ResponseEntity.status(HttpStatus.CREATED).header(location).body(responseBody);
    }

    @DeleteMapping(value="/tasks/{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteTask(@PathVariable int id)
    {
        taskService.deleteTask(id);
    }
}
