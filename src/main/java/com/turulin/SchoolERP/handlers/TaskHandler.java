package com.turulin.SchoolERP.handlers;

import com.turulin.SchoolERP.models.Task;
import com.turulin.SchoolERP.repositorys.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class TaskHandler {

    @Autowired
    TaskRepository taskRepository;

    public Mono<ServerResponse> getTasks(ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(APPLICATION_JSON)
                .body(taskRepository.findAll(), Task.class);
    }

    public Mono<ServerResponse> addTask(ServerRequest serverRequest) {
        Mono<Task> taskMono = serverRequest.bodyToMono(Task.class);
        return taskMono.flatMap(task -> {
            Mono<Task> savedTask = taskRepository.save(task);
            return savedTask.flatMap(task1 -> ServerResponse.created(URI.create(task1.getId().toString()))
                    .contentType(APPLICATION_JSON)
                    .bodyValue(task1));
        });
    }

    public Mono<ServerResponse> getTaskById(ServerRequest serverRequest) {
        long id = Long.parseLong(serverRequest.pathVariable("id"));
        return ServerResponse.ok().body(taskRepository.findById(id), Task.class);
    }

    public Mono<ServerResponse> updateTask(ServerRequest serverRequest) {
        Mono<Task> updatedTask = serverRequest.bodyToMono(Task.class);
        return updatedTask.flatMap(task -> ServerResponse.ok().body(taskRepository.save(task), Task.class));
    }

    public Mono<ServerResponse> deleteTaskById(ServerRequest serverRequest) {
        long id = Long.parseLong(serverRequest.pathVariable("id"));
        return ServerResponse.noContent().build(taskRepository.deleteById(id));
    }

}
