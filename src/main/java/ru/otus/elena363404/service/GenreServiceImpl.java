package ru.otus.elena363404.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.elena363404.domain.Book;
import ru.otus.elena363404.domain.Genre;
import ru.otus.elena363404.repository.BookRepository;
import ru.otus.elena363404.repository.GenreRepository;

@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService {

  private final GenreRepository genreRepository;
  private final BookRepository bookRepository;

  @Override
  public Mono<Genre> saveGenre(Genre genre) {
    return genreRepository.save(genre);
  }

  @Override
  public Mono<Void> deleteGenre(String id) {
    return genreRepository.deleteById(id);
  }

  @Override
  public Mono<Genre> getGenreById(String id) {
    Mono<Genre> genre = genreRepository.findById(id);

    return genre;
  }

  @Override
  public Flux<Genre> getAllGenre() {
    return genreRepository.findAll();
  }
}
