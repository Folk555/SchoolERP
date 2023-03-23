package com.turulin.SchoolERP.repositorys;

import com.turulin.SchoolERP.models.Task;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends R2dbcRepository<Task, Long> {
}
