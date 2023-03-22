package com.turulin.SchoolERP.handlers;

import com.turulin.SchoolERP.models.Task;
import com.turulin.SchoolERP.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class TaskHandler {
    @Autowired
    TaskService taskService;

    public Mono<ServerResponse> getTasks(ServerRequest serverRequest) {
        return ServerResponse.status(222).contentType(APPLICATION_JSON)
                .body(taskService.getAllTasks(), Task.class);
    }

    public Mono<ServerResponse> addTask(ServerRequest serverRequest) {
        Mono<Task> taskMono = serverRequest.bodyToMono(Task.class);
        return  taskMono.flatMap(task -> ServerResponse.status(777).contentType(APPLICATION_JSON)
                .body(taskService.addTask(task), Task.class));
    }


//    public Mono<ServerResponse> getTaskById(ServerRequest serverRequest) {
//        long id = Long.parseLong(serverRequest.pathVariable("id"));
//        return ServerResponse.ok().bodyValue(database.taskList.get((int)id));
//    }
//
//    public Mono<ServerResponse> updateTask(ServerRequest serverRequest) {
//        long id = Long.parseLong(serverRequest.pathVariable("id"));
//        Task newTask = serverRequest.bodyToMono(Task.class).block();
//
//        Task task = database.taskList.get((int)id);
//        task.setDescription(newTask.getDescription());
//        return ServerResponse.ok().bodyValue(database.taskList.get((int)id));
//    }
//
//    public Mono<ServerResponse> deleteTaskById(ServerRequest serverRequest) {
//        long id = Long.parseLong(serverRequest.pathVariable("id"));
//
//        database.taskList.remove((int)id);
//        return ServerResponse.noContent().build();
//    }

}
