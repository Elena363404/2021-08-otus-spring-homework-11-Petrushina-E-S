package ru.otus.elena363404.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.elena363404.domain.Author;

public interface AuthorService {

  Mono<Author> saveAuthor(Author author);

  Mono<Void> deleteAuthor(String id);

  Mono<Author> getAuthorById(String id);

  Flux<Author> getAllAuthor();
}
