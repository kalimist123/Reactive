package com.packtpub.reactive.ticketmanagement;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
public class UserHandler {
    private final UserRepository userRepository;
    public UserHandler(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public Mono<ServerResponse> getAllUsers(ServerRequest request){
        Flux<User> users = this.userRepository.getAllUsers();
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(users,
                User.class);
    }

    public Mono<ServerResponse> getUser(ServerRequest request){
        int userId = Integer.valueOf(request.pathVariable("id"));
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Mono<User> userMono = this.userRepository.getUser(userId);
        return userMono
                .flatMap(user ->
                        ServerResponse.ok().contentType(APPLICATION_JSON).body(fromObject(user)))
                .switchIfEmpty(notFound);
    }
}
