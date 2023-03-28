package com.turulin.SchoolERP.repositorys;

import com.turulin.SchoolERP.models.Task;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends ReactiveCrudRepository<Task, Long> {

}
