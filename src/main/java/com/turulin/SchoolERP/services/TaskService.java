package com.turulin.SchoolERP.services;

import com.turulin.SchoolERP.models.Task;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class TaskService {
    public static List<Task> taskList = new ArrayList<>();
    public static Publisher<Void> addTask(Mono<Task> task) {
        taskList.add(task.block());
        return null;
    }

    public static List<Task> getAllTasks() {
        return taskList;
    }
}
