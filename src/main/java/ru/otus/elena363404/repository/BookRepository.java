package ru.otus.elena363404.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.elena363404.domain.Author;
import ru.otus.elena363404.domain.Book;
import ru.otus.elena363404.domain.Genre;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {

  Mono<Book> findById(String id);

  Mono<Book> save(Book book);

  Flux<Book> findAll();

  Flux<Book> findByAuthor(Mono<Author> author);

  Flux<Book> findByGenre(Mono<Genre> genre);

}
