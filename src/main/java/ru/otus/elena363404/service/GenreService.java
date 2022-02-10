package ru.otus.elena363404.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.elena363404.domain.Genre;

public interface GenreService {
  Mono<Genre> saveGenre(Genre genre);

  Mono<Void> deleteGenre(String id);

  Mono<Genre> getGenreById(String id);

  Flux<Genre> getAllGenre();
}
