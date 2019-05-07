package com.packtpub.reactive.ticketmanagement;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository {
    Flux<User> getAllUsers();
    Mono<User> getUser(Integer id);
    Mono<Void> saveUser(Mono<User> userMono);

}

