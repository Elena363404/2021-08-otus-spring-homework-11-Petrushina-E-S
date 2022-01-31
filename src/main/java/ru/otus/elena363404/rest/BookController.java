package ru.otus.elena363404.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.elena363404.domain.Book;
import ru.otus.elena363404.rest.dto.BookDto;
import ru.otus.elena363404.service.BookService;

import static ru.otus.elena363404.rest.dto.BookDto.*;

@RestController
@AllArgsConstructor
public class BookController {
  private final BookService bookService;

  @PutMapping("/api/book/{id}")
  public Mono<BookDto> editBook(@RequestBody BookDto bookDto) {
    Book book = bookDtoToBook(bookDto);
    Mono<Book> saved = bookService.saveBook(book);
    return bookMonoToBookDtoMono(saved);
  }

  @PostMapping("/api/book")
  public Mono<BookDto> addBook(@RequestBody BookDto bookDto) {
    Book book = bookDtoToBook(bookDto);
    Mono<Book> saved = bookService.saveBook(book);
    return bookMonoToBookDtoMono(saved);
  }

  @DeleteMapping("/api/book/{id}")
  public Mono<Void> deleteBook(@PathVariable("id") String id) {
    return  bookService.deleteBook(id);
  }

  @GetMapping("/api/book/{id}")
  public Mono<Book> getBookById(@PathVariable("id") String id) {
    return  bookService.getBookById(id);
  }

  @GetMapping("/api/book")
  public Flux<BookDto> getAllBooks() {
    Flux<Book> bookFlux = bookService.getAllBook();
    Flux<BookDto> bookDtoFlux = bookFluxToBookDtoFlux(bookFlux);

    return bookDtoFlux;
  }



}
