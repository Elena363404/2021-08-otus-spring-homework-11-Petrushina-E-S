package ru.otus.elena363404.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.elena363404.domain.Author;
import ru.otus.elena363404.domain.Book;
import ru.otus.elena363404.repository.AuthorRepository;
import ru.otus.elena363404.repository.BookRepository;


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

    Flux<Book> bookFlux = bookRepository.findByAuthor(authorRepository.findById(id))
      .flatMap(book -> {book.setAuthor(null); return bookRepository.save(book);});

    return bookFlux.ignoreElements().then(authorRepository.deleteById(id));
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
