package com.taskmanagerbackend;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "task_manager")
public class TaskEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "task_order")
    private int taskOrder;

    @Column(name = "task_priority")
    private int taskPriority;
}
