package ru.otus.elena363404.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.elena363404.domain.Genre;

public interface GenreRepository extends ReactiveMongoRepository<Genre, String> {

  Flux<Genre> findAll();

  Mono<Genre> findById(String id);

  Mono<Genre> save(Genre genre);
}
