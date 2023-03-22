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
public class Routers {
    @Bean
    public RouterFunction<ServerResponse> taskRouter(TaskHandler taskHandler) {
        return RouterFunctions.route().path("/task", builder1 -> builder1
                        .nest(accept(MediaType.APPLICATION_JSON), builder2 -> builder2
                                        .GET(taskHandler::getTasks)
                                        //.GET("/{id:[%d]+}", taskHandler::getTasks)
                                        .POST(taskHandler::addTask)
                                //.DELETE("/{id:[%d]+}", taskHandler::deleteTaskById)
                                //.PATCH("/{id:[%d]+}", taskHandler::updateTask)
                        ))
                .build();
    }
}
