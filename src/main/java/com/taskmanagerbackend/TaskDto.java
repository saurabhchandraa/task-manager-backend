package com.taskmanagerbackend;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Task dto.
 */
@Getter
@Setter
public class TaskDto {

    /**
     * The Id.
     */
    private Integer id;

    /**
     * The Task name.
     */
    private String taskName;

    /**
     * The Task order.
     */
    private int taskOrder;

    /**
     * The Task priority.
     */
    private int taskPriority;
}
