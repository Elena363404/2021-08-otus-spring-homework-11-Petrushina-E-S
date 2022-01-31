package ru.otus.elena363404.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.elena363404.domain.Book;
import ru.otus.elena363404.domain.Comment;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

  private String id;
  private String comment;
  private String bookId;
  private String bookName;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getBookName() {
    return bookName;
  }

  public void setBookName(String bookName) {
    this.bookName = bookName;
  }

  public String getBookId() {
    return bookId;
  }

  public void setBookId(String bookId) {
    this.bookId = bookId;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public static CommentDto commentToCommentDto(Comment comment) {
    Book book = comment.getBook();

    CommentDto commentDto = new CommentDto(comment.getId(), comment.getComment(), book != null ? book.getId() : "0", book != null ? book.getName() : "");
    return commentDto;
  }

  public static Comment commentDtoToComment(CommentDto commentDto) {
    Comment comment = new Comment(commentDto.getId(), commentDto.getComment(), new Book(commentDto.getBookId(), commentDto.getBookName(), null, null));

    return comment;
  }

  public static Flux<CommentDto> commentDtoListToFlux(List<CommentDto> commentDtoList) {
    Flux<CommentDto> commentDtoFlux = Flux.fromIterable(commentDtoList);
    return commentDtoFlux;
  }

  public static Mono<CommentDto> commentDtoToCommentDtoMono(CommentDto commentDto) {
    Mono<CommentDto> commentDtoMono = Mono.just(commentDto);
    return commentDtoMono;
  }

  public static Mono<CommentDto> commentMonoToCommentDtoMono(Mono<Comment> commentMono) {
    Comment comment = commentMonoToComment(commentMono);
    CommentDto commentDto = commentToCommentDto(comment);
    Mono<CommentDto> commentDtoMono = commentDtoToCommentDtoMono(commentDto);
    return commentDtoMono;
  }

  public static Mono<Comment> commentToCommentMono(Comment comment) {
    Mono<Comment> commentMono = Mono.just(comment);
    return commentMono;
  }

  public static Comment commentMonoToComment(Mono<Comment> commentMono) {
    Comment comment = commentMono.block();
    return comment;
  }

  private List<Comment> commentFluxToList(Flux<Comment> commentFlux) {
    List<Comment> commentList = commentFlux.collectList().block();
    return commentList;
  }

  public static Flux<CommentDto> commentFluxToCommentDtoFlux(Flux<Comment> commentFlux) {
    List<Comment> commentList = commentFlux.collectList().block();
    List<CommentDto> commentDtoList = commentList.stream().map(CommentDto::commentToCommentDto).collect(Collectors.toList());
    return commentDtoListToFlux(commentDtoList);
  }
}
