package ru.otus.elena363404.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.elena363404.domain.Author;
import ru.otus.elena363404.domain.Book;
import ru.otus.elena363404.repository.AuthorRepository;
import ru.otus.elena363404.repository.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

  private final AuthorRepository authorRepository;
  private final BookRepository bookRepository;

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
    Mono<Author> authorMono = authorRepository.findById(id);

    return authorMono;
  }

  @Override
  public Flux<Author> getAllAuthor() {
    Flux<Author> authorList = authorRepository.findAll();
    return authorList;
  }
}
