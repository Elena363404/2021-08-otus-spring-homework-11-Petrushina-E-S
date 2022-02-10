package ru.otus.elena363404.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.elena363404.domain.Author;
import ru.otus.elena363404.repository.AuthorRepository;


@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

  private final AuthorRepository authorRepository;

  @Override
  public Mono<Author> saveAuthor(Author author) {
    return authorRepository.save(author);
  }

  @Override
  public Mono<Void> deleteAuthor(String id) {
    return authorRepository.deleteById(id);
  }

  @Override
  public Mono<Author> getAuthorById(String id) {
    return authorRepository.findById(id);
  }

  @Override
  public Flux<Author> getAllAuthor() {
    return authorRepository.findAll();
  }
}
