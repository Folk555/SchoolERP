package com.turulin.SchoolERP.services;

import com.turulin.SchoolERP.models.Task;
import com.turulin.SchoolERP.repositorys.TaskRepository;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class TaskService {
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private long id=0;
    @Autowired
    private TaskRepository taskRepository;

    public Flux<Task> addTask(Task task) {
        //task.setId(id++);
        return taskRepository.save(task).flux();
    }

    public Flux<Task> getAllTasks() {
        return taskRepository.findAll();
    }
}
