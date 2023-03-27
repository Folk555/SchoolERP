package com.turulin.SchoolERP.routers;

import com.turulin.SchoolERP.handlers.TaskHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class TaskRouters {
    @Bean
    public RouterFunction<ServerResponse> taskRouter(TaskHandler taskHandler) {
        return RouterFunctions.route().path("/task", builder1 -> builder1
                        .nest(accept(MediaType.APPLICATION_JSON), builder2 -> builder2
                                .GET("/{id}", taskHandler::getTaskById)
                                .GET(taskHandler::getTasks)
                                .POST(taskHandler::addTask)
                                .DELETE("/{id}", taskHandler::deleteTaskById)
                                .PATCH(taskHandler::updateTask)
                        ))
                .build();
    }
}
