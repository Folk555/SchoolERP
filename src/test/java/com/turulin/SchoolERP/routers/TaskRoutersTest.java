package com.turulin.SchoolERP.routers;

import com.turulin.SchoolERP.models.Task;
import com.turulin.SchoolERP.repositorys.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskRoutersTest {

    @Autowired
    WebTestClient webClient;

    @MockBean
    private TaskRepository taskRepository;

    @Test
    void givenTaskId_whenGetTaskById_thenCorrectTask() {
        Task task = new Task((long) 1, "author", "room", "description");

        given(taskRepository.findById((long) 1))
                .willReturn(Mono.just(task));

        webClient.get()
                .uri("/task/1")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Task.class)
                .isEqualTo(task);
    }

    @Test
    void givenTasks_whenGetAllTasks_thenListTasks() {
        var listTask = List.of(new Task((long) 1, "author", "room", "description"),
                new Task((long) 2, "author2", "room2", "description2"));

        given(taskRepository.findAll())
                .willReturn(Flux.fromIterable(listTask));

        webClient.get()
                .uri("/task")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(Task.class)
                .isEqualTo(listTask);
    }

    @Test
    void givenTask_whenPostTask_thenTaskAdded() {
        Task task = new Task();
        task.setAuthor("author");
        task.setRoom("room");
        task.setDescription("description");
        Task savedTask = new Task((long) 1, "author", "room", "description");

        given(taskRepository.save(task))
                .willReturn(Mono.just(savedTask));

        webClient.post()
                .uri("/task")
                .bodyValue(task)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(Task.class)
                .isEqualTo(savedTask);

        then(taskRepository.save(task));
    }

    @Test
    void givenSavedTask_whenDeleteTask_thenTaskDeleted() {
        Task task = new Task((long) 1, "author", "room", "description");

        given(taskRepository.deleteById(task.getId()))
                .willReturn(Mono.empty());

        webClient.delete()
                .uri("/task/" + task.getId())
                .exchange()
                .expectStatus()
                .isNoContent()
                .expectBody().isEmpty();

        then(taskRepository.deleteById(task.getId()));
    }

    @Test
    void givenSavedTask_whenUpdateTask_thenTaskUpdated() {
        Task task = new Task((long) 1, "author", "room", "description");

        given(taskRepository.save(task))
                .willReturn(Mono.just(task));

        webClient.patch()
                .uri("/task")
                .bodyValue(task)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Task.class)
                .isEqualTo(task);

        then(taskRepository.save(task));
    }

}