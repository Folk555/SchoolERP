package com.turulin.SchoolERP.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("tasks")
public class Task {
    @Id
    private Long id;
    private String description;
}
