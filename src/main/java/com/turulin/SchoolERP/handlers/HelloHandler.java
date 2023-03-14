package com.turulin.SchoolERP.handlers;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class HelloHandler {
    public Mono<ServerResponse> sayHello(ServerRequest serverRequest) {
        return ServerResponse.ok().bodyValue(new String("hello user"));
    }
}
