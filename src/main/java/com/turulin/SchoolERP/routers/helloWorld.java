package com.turulin.SchoolERP.routers;

import com.turulin.SchoolERP.handlers.TaskHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class helloWorld {
    @Bean
    public RouterFunction<ServerResponse> helloAnswer(TaskHandler helloHandler) {
        return RouterFunctions
                .route(GET("/tasks"), helloHandler::getTasks);
    }
}
