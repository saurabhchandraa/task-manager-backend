package com.taskmanagerbackend;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * The type Task entity.
 */
@Entity
@Getter
@Setter
@Table(name = "task_manager")
public class TaskEntity {

    /**
     * The Id.
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    /**
     * The Task name.
     */
    @Column(name = "task_name")
    private String taskName;

    /**
     * The Task order.
     */
    @Column(name = "task_order")
    private int taskOrder;

    /**
     * The Task priority.
     */
    @Column(name = "task_priority")
    private int taskPriority;
}
