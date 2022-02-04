package com.taskmanagerbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * The type Task controller.
 */
@RestController
@CrossOrigin("http://localhost:3000/")
public class TaskController {

    /**
     * The Task service.
     */
    @Autowired
    private TaskService taskService;

    /**
     * The Mapper.
     */
    @Autowired
    private TasksMapper mapper;

    /**
     * Find all tasks response entity.
     *
     * @return the response entity
     */
    @GetMapping(value="/allTasks", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<List<TaskDto>> findAllTasks()
    {
        List<TaskEntity>  entityList = taskService.getAllTask();
        List<TaskDto> dtoList = entityList.stream().map(mapper::toTaskDto).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }

    /**
     * Search tasks by priority response entity.
     *
     * @param priority the priority
     * @return the response entity
     */
    @GetMapping(value="/tasks/priority/{priority}", produces = APPLICATION_JSON_VALUE)
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

    /**
     * Add task response entity.
     *
     * @param request the request
     * @param taskDto the task dto
     * @return the response entity
     */
    @PostMapping(value="/tasks", consumes= {MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> addTask(HttpServletRequest request, @RequestBody TaskDto taskDto)
    {

        String location = "";
        String responseBody = "";
        TaskEntity taskEntity = mapper.toTaskEntity(taskDto);
        TaskEntity entityResponse = taskService.addTask(taskEntity);

        location = request.getRequestURI() + "/"+entityResponse.getId();
        responseBody = String.format("{\"id\": %d}" ,entityResponse.getId());

        return ResponseEntity.status(HttpStatus.CREATED).header(location).body(responseBody);
    }

    /**
     * Delete task.
     *
     * @param id the id
     */
    @DeleteMapping(value="/tasks/{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteTask(@PathVariable int id)
    {
        taskService.deleteTask(id);
    }
}
