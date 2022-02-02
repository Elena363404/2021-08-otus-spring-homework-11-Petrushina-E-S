package ru.otus.elena363404.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.elena363404.domain.Book;
import ru.otus.elena363404.domain.Comment;
import ru.otus.elena363404.repository.BookRepository;
import ru.otus.elena363404.repository.CommentRepository;

import java.util.List;


@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;
  private final CommentRepository commentRepository;

  @Override
  public Mono<Book> saveBook(Book book) {
    return bookRepository.save(book);
  }

  @Override
  public Mono<Void> deleteBook(String id) {
    return bookRepository.deleteById(id);
  }

  @Override
  public Mono<Book> getBookById(String id) {
    Mono<Book> book = bookRepository.findById(id);
    return book;
  }

  @Override
  public Flux<Book> getAllBook() {
    return bookRepository.findAll();
  }
}
