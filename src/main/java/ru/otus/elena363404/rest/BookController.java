package ru.otus.elena363404.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.elena363404.domain.Book;
import ru.otus.elena363404.rest.dto.BookDto;
import ru.otus.elena363404.service.BookService;
import ru.otus.elena363404.service.CommentService;

import static ru.otus.elena363404.rest.dto.BookDto.*;

@RestController
@AllArgsConstructor
public class BookController {
  private final BookService bookService;
  private final CommentService commentService;

  @PutMapping("/api/book/{id}")
  public Mono<BookDto> editBook(@RequestBody BookDto bookDto) {
    Mono<BookDto> saved = (bookService.saveBook(bookDtoToBook(bookDto))).map(BookDto::bookToBookDto);
    return saved;
  }

  @PostMapping("/api/book")
  public Mono<BookDto> addBook(@RequestBody BookDto bookDto) {
    Mono<BookDto> saved = (bookService.saveBook(bookDtoToBook(bookDto))).map(BookDto::bookToBookDto);
    return saved;
  }

  @DeleteMapping("/api/book/{id}")
  public Flux<Void> deleteBook(@PathVariable("id") String id) {

    Flux<Void> monoVoidComment = (commentService.deleteCommentByBookId(id)).concatWith(bookService.deleteBook(id));

    return monoVoidComment;
  }

  @GetMapping("/api/book/{id}")
  public Mono<Book> getBookById(@PathVariable("id") String id) {
    return  bookService.getBookById(id);
  }

  @GetMapping("/api/book")
  public Flux<BookDto> getAllBooks() {
    Flux<BookDto> bookDtoFlux = (bookService.getAllBook()).map(BookDto::bookToBookDto);

    return bookDtoFlux;
  }



}
