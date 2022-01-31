package ru.otus.elena363404.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.elena363404.domain.Genre;
import ru.otus.elena363404.service.GenreService;


@RestController
@AllArgsConstructor
public class GenreController {
  private final GenreService genreService;

  @PutMapping("/api/genre/{id}")
  public Mono<Genre> editGenre(@RequestBody Genre genre) {
    Mono<Genre> saved = genreService.saveGenre(genre);
    return saved;
  }

  @DeleteMapping("/api/genre/{id}")
  public Mono<Void> deleteGenre(@PathVariable("id") String id) {
    return  genreService.deleteGenre(id);
  }

  @GetMapping("/api/genre")
  public Flux<Genre> getAllGenre() {
    return genreService.getAllGenre();
  }
}
