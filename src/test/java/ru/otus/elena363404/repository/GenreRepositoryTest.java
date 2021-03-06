package ru.otus.elena363404.repository;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.elena363404.changelogTest.MongoIdForTest;
import ru.otus.elena363404.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataMongoTest
class GenreRepositoryTest {

  private static final String EXISTING_GENRE_ID = MongoIdForTest.idGenre3;
  private static final String GENRE_ID_FOR_DELETE = MongoIdForTest.idGenre5;
  private static final int EXPECTES_NUMBER_OF_GENRES = 5;

  @Autowired
  private GenreRepository genreRepository;

  @Autowired
  private ReactiveMongoRepository<Genre, String> cr;

  @DisplayName("Add genre in the DB")
  @Test
  void shouldInsertGenre() {
    Genre expectedGenre = new Genre( "Thriller");
    Genre genre = genreRepository.save(expectedGenre).block();
    assertThat(expectedGenre.getId()).isNotNull();
    assertThat(expectedGenre.getName()).isEqualTo(genre.getName());
  }

  @DisplayName("Return genre by ID")
  @Test
  void shouldReturnExpectedGenreById() {
    Genre optionalActualGenre = genreRepository.findById(EXISTING_GENRE_ID).block();
    Genre expectedGenre = cr.findById(EXISTING_GENRE_ID).block();
    assertThat(optionalActualGenre).isEqualTo(expectedGenre);
  }

  @DisplayName("Delete genre by ID")
  @Test
  void shouldCorrectDeleteGenreById() {
    val genreBeforeDelete = cr.findById(GENRE_ID_FOR_DELETE);
    assertNotNull(genreBeforeDelete);
    genreRepository.deleteById(GENRE_ID_FOR_DELETE);
    Genre genreAfterDelete = cr.findById(GENRE_ID_FOR_DELETE).block();
    assertThat(genreAfterDelete == null).isEqualTo(true);
  }

  @DisplayName("Return list of the genres")
  @Test
  void shouldReturnExpectedGenresList() {
    System.out.println("\n\n\n\n----------------------------------------------------------------------------------------------------------");
    List<Genre> genres = genreRepository.findAll().collectList().block();
    assertThat(genres).isNotNull().hasSize(EXPECTES_NUMBER_OF_GENRES)
      .allMatch(s -> !s.getName().equals(""));
    System.out.println("----------------------------------------------------------------------------------------------------------\n\n\n\n");
  }

}