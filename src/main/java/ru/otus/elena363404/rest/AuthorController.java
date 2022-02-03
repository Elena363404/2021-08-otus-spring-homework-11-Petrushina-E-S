package ru.otus.elena363404.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.elena363404.domain.Author;
import ru.otus.elena363404.service.AuthorService;

@RestController
@AllArgsConstructor
public class AuthorController {

  private final AuthorService authorService;

  @PutMapping("/api/author/{id}")
  public Mono<Author> editAuthor(@RequestBody Author author) {
    Mono<Author> saved = authorService.saveAuthor(author);
    return saved;
  }

  @DeleteMapping("/api/author/{id}")
  public Mono<Void> deleteAuthor(@PathVariable("id") String id)  {

    return authorService.deleteAuthor(id);
  }

  @GetMapping("/api/author")
  public Flux<Author> getAllAuthors() {
    return authorService.getAllAuthor();
  }
}
