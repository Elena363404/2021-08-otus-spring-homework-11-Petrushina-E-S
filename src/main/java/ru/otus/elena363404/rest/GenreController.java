package ru.otus.elena363404.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.elena363404.domain.Genre;
import ru.otus.elena363404.exception.BookExistException;
import ru.otus.elena363404.service.BookService;
import ru.otus.elena363404.service.GenreService;


@RestController
@AllArgsConstructor
public class GenreController {
  private final GenreService genreService;
  private final BookService bookService;

  @PutMapping("/api/genre/{id}")
  public Mono<Genre> editGenre(@RequestBody Genre genre) {
    Mono<Genre> saved = genreService.saveGenre(genre);
    return saved;
  }

  @DeleteMapping("/api/genre/{id}")
  public Mono<Object> deleteGenre(@PathVariable("id") String id) {

    return bookService.existsByGenreId(id).map(
      exists -> !exists ? genreService.deleteGenre(id) : Mono.error(new BookExistException(HttpStatus.BAD_REQUEST, "Book with deleted Genre exist. Delete Book first!"))
    );
  }

  @GetMapping("/api/genre")
  public Flux<Genre> getAllGenre() {
    return genreService.getAllGenre();
  }
}
