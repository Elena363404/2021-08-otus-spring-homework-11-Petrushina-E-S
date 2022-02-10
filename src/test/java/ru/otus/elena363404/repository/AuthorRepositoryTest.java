package ru.otus.elena363404.repository;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.elena363404.changelogTest.MongoIdForTest;
import ru.otus.elena363404.domain.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
class AuthorRepositoryTest {

  private static final String EXISTING_AUTHOR_ID = MongoIdForTest.idAuthor3;
  private static final int EXPECTES_NUMBER_OF_AUTHORS = 5;
  private static final String AUTHOR_ID_FOR_DELETE = MongoIdForTest.idAuthor5;

  @Autowired
  private AuthorRepository authorRepository;

  @Autowired
  private ReactiveMongoRepository<Author, String> cr;

  @DisplayName("Add author in the DB")
  @Test
  void shouldInsertAuthor() {
    Author expectedAuthor = new Author("Lermontov");
    Mono<Author> authorMono = authorRepository.save(expectedAuthor);
    Author author = authorMono.block();
    assertThat(expectedAuthor.getId()).isNotNull();
    assertThat(expectedAuthor.getName()).isEqualTo(author.getName());
  }

  @DisplayName("Return author by ID")
  @Test
  void shouldReturnExpectedAuthorById() {
    Author optionalActualAuthor = authorRepository.findById(EXISTING_AUTHOR_ID).block();
    Author expectedAuthor = cr.findById(EXISTING_AUTHOR_ID).block();
    assertThat(optionalActualAuthor).isEqualTo(expectedAuthor);
  }

  @DisplayName("Delete author by ID")
  @Test
  void shouldCorrectDeleteAuthorById() {
    val authorBeforeDelete = cr.findById(AUTHOR_ID_FOR_DELETE);
    assertNotNull(authorBeforeDelete);
    authorRepository.deleteById(AUTHOR_ID_FOR_DELETE);
    Mono<Author> authorAfterDelete = cr.findById(AUTHOR_ID_FOR_DELETE);
    assertThat(authorAfterDelete.block() == null).isEqualTo(true);
  }

  @DisplayName("Return list of the authors")
  @Test
  void shouldReturnExpectedAuthorsList() {
    System.out.println("\n\n\n\n----------------------------------------------------------------------------------------------------------");
    Flux<Author> authorFlux = authorRepository.findAll();
    List<Author> authorList = authorFlux.collectList().block();
    assertThat(authorList).isNotNull().hasSize(EXPECTES_NUMBER_OF_AUTHORS)
      .allMatch(s -> !s.getName().equals(""));
    System.out.println("----------------------------------------------------------------------------------------------------------\n\n\n\n");
  }


}