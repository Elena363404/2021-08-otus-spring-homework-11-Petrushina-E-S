package ru.otus.elena363404.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.elena363404.domain.Author;
import ru.otus.elena363404.domain.Book;

import java.util.List;

public interface BookService {

  Mono<Book> saveBook(Book book);

  Mono<Void> deleteBook(String id);

  Mono<Book> getBookById(String id);

  Mono<Boolean> existsByAuthorId(String authorId);

  Mono<Boolean> existsByGenreId(String genreId);

  Flux<Book> getAllBook();
}
