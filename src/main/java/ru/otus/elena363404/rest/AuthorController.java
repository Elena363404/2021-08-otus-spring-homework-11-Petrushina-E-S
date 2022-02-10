package ru.otus.elena363404.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.elena363404.domain.Author;
import ru.otus.elena363404.exception.BookExistException;
import ru.otus.elena363404.service.AuthorService;
import ru.otus.elena363404.service.BookService;

@RestController
@AllArgsConstructor
public class AuthorController {

  private final AuthorService authorService;
  private final BookService bookService;

  @PutMapping("/api/author/{id}")
  public Mono<Author> editAuthor(@RequestBody Author author) {
    Mono<Author> saved = authorService.saveAuthor(author);
    return saved;
  }

  @DeleteMapping("/api/author/{id}")
  public Mono<Object> deleteAuthor(@PathVariable("id") String id)  {

    return bookService.existsByAuthorId(id).map(
      exists -> !exists ? authorService.deleteAuthor(id) : Mono.error(new BookExistException(HttpStatus.BAD_REQUEST, "Book with deleted Author exist. Delete Book first!"))
    );
  }

  @GetMapping("/api/author")
  public Flux<Author> getAllAuthors() {
    return authorService.getAllAuthor();
  }
}
