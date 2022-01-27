package com.taskmanagerbackend;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDto {

    private Integer id;

    private String taskName;

    private int taskOrder;

    private int taskPriority;
}
