package ru.otus.elena363404.repository;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.otus.elena363404.changelogTest.MongoIdForTest;
import ru.otus.elena363404.domain.Author;
import ru.otus.elena363404.domain.Book;
import ru.otus.elena363404.domain.Genre;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
class BookRepositoryTest {

  private static final String EXISTING_BOOK_ID = MongoIdForTest.idBook3;
  private static final String BOOK_ID_FOR_DELETE = MongoIdForTest.idBook2;

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private ReactiveMongoRepository<Book, String> cr;

  @DisplayName("Add book in the DB")
  @Test
  void shouldInsertBook() {
    Book expectedBook = new Book( MongoIdForTest.idBook5, "BookForTest", new Author(MongoIdForTest.idAuthor2, "Alexander Pushkin"), new Genre(MongoIdForTest.idGenre3, "Novel"));
    Mono<Book> newBookMono = bookRepository.save(expectedBook);
    Book newBook = newBookMono.block();
    Book actualBook = cr.findById(newBook.getId()).block();
    assertThat(actualBook.getName()).isEqualTo(newBook.getName());

    StepVerifier
      .create(newBookMono)
      .assertNext(book -> assertNotNull(book.getId()))
      .expectComplete()
      .verify();
  }

  @DisplayName("Return book by ID")
  @Test
  void shouldReturnExpectedBookById() {
    Book optionalActualBook = bookRepository.findById(EXISTING_BOOK_ID).block();
    Book expectedBook = cr.findById(EXISTING_BOOK_ID).block();
    assertThat(optionalActualBook).isEqualTo(expectedBook);
  }

  @DisplayName("Delete book by ID")
  @Test
  void shouldCorrectDeleteBookById() {
    val bookBeforeDelete = cr.findById(BOOK_ID_FOR_DELETE);
    assertNotNull(bookBeforeDelete);
    bookRepository.deleteById(BOOK_ID_FOR_DELETE);
    Mono<Book> bookAfterDelete = cr.findById(BOOK_ID_FOR_DELETE);
    assertThat(bookAfterDelete.block() == null).isEqualTo(true);
  }
}