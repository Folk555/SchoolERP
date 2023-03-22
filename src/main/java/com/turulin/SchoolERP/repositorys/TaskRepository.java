package com.turulin.SchoolERP.repositorys;

import com.turulin.SchoolERP.models.Task;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends ReactiveMongoRepository<Task, Long> {
}
