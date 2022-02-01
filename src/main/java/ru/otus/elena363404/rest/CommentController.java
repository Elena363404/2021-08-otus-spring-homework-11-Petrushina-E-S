package ru.otus.elena363404.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.elena363404.domain.Comment;
import ru.otus.elena363404.rest.dto.CommentDto;
import ru.otus.elena363404.service.CommentService;

import static ru.otus.elena363404.rest.dto.CommentDto.*;

@RestController
@AllArgsConstructor
public class CommentController {

  private final CommentService commentService;

  @PutMapping("/api/comment/{id}")
  public Mono<CommentDto> editComment(@RequestBody CommentDto commentDto) {
    Mono<CommentDto> commentDtoMono = (commentService.saveComment(commentDtoToComment(commentDto))).map(CommentDto::commentToCommentDto);

    return commentDtoMono;
  }

  @DeleteMapping("/api/comment/{id}")
  public Mono<Void> deleteComment(@PathVariable("id") String id) {
    return  commentService.deleteComment(id);
  }

  @GetMapping("/api/comment")
  public Flux<CommentDto> getAllComments() {
    Flux<CommentDto> commentDtoFlux = (commentService.getAllComment()).map(CommentDto::commentToCommentDto);
    return commentDtoFlux;
  }

}
