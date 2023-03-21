package com.turulin.SchoolERP.handlers;

import com.turulin.SchoolERP.models.Task;
import com.turulin.SchoolERP.repositorys.database;
import com.turulin.SchoolERP.services.TaskService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class TaskHandler {
    public Mono<ServerResponse> getTasks(ServerRequest serverRequest) {
        return ServerResponse.ok().bodyValue(TaskService.taskList);
    }

    public Mono<ServerResponse> getTaskById(ServerRequest serverRequest) {
        long id = Long.parseLong(serverRequest.pathVariable("id"));
        return ServerResponse.ok().bodyValue(database.taskList.get((int)id));
    }

    public Mono<ServerResponse> addTask(ServerRequest serverRequest) {
        Mono<Task> taskMono = serverRequest.bodyToMono(Task.class);
        return ServerResponse.accepted().build(TaskService.addTask(taskMono));
    }

    public Mono<ServerResponse> updateTask(ServerRequest serverRequest) {
        long id = Long.parseLong(serverRequest.pathVariable("id"));
        Task newTask = serverRequest.bodyToMono(Task.class).block();

        Task task = database.taskList.get((int)id);
        task.setDescription(newTask.getDescription());
        return ServerResponse.ok().bodyValue(database.taskList.get((int)id));
    }

    public Mono<ServerResponse> deleteTaskById(ServerRequest serverRequest) {
        long id = Long.parseLong(serverRequest.pathVariable("id"));

        database.taskList.remove((int)id);
        return ServerResponse.noContent().build();
    }

}
