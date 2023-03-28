package com.turulin.SchoolERP.services;

import com.turulin.SchoolERP.exeptions.UsernameNotFoundException;
import com.turulin.SchoolERP.repositorys.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SecurityUserDetailsService implements ReactiveUserDetailsService {
    @Autowired
    UserDetailsRepository userRepository;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userRepository.findByUsernameWithQuery(username).switchIfEmpty(
                Mono.error(new UsernameNotFoundException(username))
        ).cast(UserDetails.class);
    }

}
