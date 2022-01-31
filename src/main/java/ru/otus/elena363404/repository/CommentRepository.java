package ru.otus.elena363404.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.elena363404.domain.Book;
import ru.otus.elena363404.domain.Comment;

public interface CommentRepository extends ReactiveMongoRepository<Comment, String> {

  Flux<Comment> findByBook(Mono<Book> book);

  Mono<Comment> save(Comment comment);
}
