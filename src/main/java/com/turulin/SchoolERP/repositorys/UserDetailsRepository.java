package com.turulin.SchoolERP.repositorys;

import com.turulin.SchoolERP.models.security.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserDetailsRepository extends ReactiveCrudRepository<User, String> {
    @Query("""
            SELECT u.*, roles.role_name AS roles
            FROM users u
                JOIN authorities a USING (username)
                JOIN roles ON a.role_id = roles.id
            WHERE LOWER(u.username)=LOWER(:username)""")
    Mono<UserDetails> findByUsernameWithQuery(@Param("username") String username);
}
