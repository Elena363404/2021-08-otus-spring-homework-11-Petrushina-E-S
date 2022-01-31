package ru.otus.elena363404.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.elena363404.domain.Comment;
import ru.otus.elena363404.repository.CommentRepository;


@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

  private final CommentRepository commentRepository;

  @Override
  public Mono<Comment> saveComment(Comment comment) {
    return commentRepository.save(comment);
  }


  @Override
  public Mono<Void> deleteComment(String id) {
    return commentRepository.deleteById(id);
  }

  @Override
  public Mono<Comment> getCommentById(String id) {
    Mono<Comment> commentMono = commentRepository.findById(id);

    return commentMono;
  }

  @Override
  public Flux<Comment> getAllComment() {
    return commentRepository.findAll();
  }
}
