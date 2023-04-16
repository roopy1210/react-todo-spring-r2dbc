package com.roopy.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@ToString
@Table
public class Todo {

    @Id
    private Integer id;
    private String comment;
    private boolean completed = false;

}
