package ru.otus.elena363404.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.elena363404.domain.Comment;

import java.util.List;

public interface CommentService {

  Mono<Comment> saveComment(Comment comment);

  Mono<Void> deleteComment(String id);

  Mono<Comment> getCommentById(String id);

  Flux<Comment> getAllComment();
}
