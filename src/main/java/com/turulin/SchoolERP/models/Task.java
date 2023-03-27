package com.turulin.SchoolERP.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("tasks")
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    private Long id;
    private String author;
    private String room;
    private String description;
}
