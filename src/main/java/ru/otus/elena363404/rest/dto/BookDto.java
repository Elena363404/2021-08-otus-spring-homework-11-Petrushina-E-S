package ru.otus.elena363404.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.elena363404.domain.Author;
import ru.otus.elena363404.domain.Book;
import ru.otus.elena363404.domain.Genre;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
  private String id;
  private String name;
  private String authorId;
  private String authorName;
  private String genreId;
  private String genreName;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAuthorId() {
    return authorId;
  }

  public void setAuthorId(String authorId) {
    this.authorId = authorId;
  }

  public String getAuthorName() {
    return authorName;
  }

  public void setAuthorName(String authorName) {
    this.authorName = authorName;
  }

  public String getGenreId() {
    return genreId;
  }

  public void setGenreId(String genreId) {
    this.genreId = genreId;
  }

  public String getGenreName() {
    return genreName;
  }

  public void setGenreName(String genreName) {
    this.genreName = genreName;
  }

  public static Book toDomainObject(BookDto dto) {
    return new Book(dto.getId(), dto.getName(), new Author(dto.getAuthorId(), dto.getAuthorName()), new Genre(dto.getGenreId(), dto.getGenreName()));
  }

  public static BookDto bookToBookDto(Book book) {
    Author author = book.getAuthor();
    Genre genre = book.getGenre();

    BookDto bookDto = new BookDto(book.getId(), book.getName(), author != null ? author.getId() : "0", author != null ? author.getName() : "", genre != null ? genre.getId() : "0", genre != null ? genre.getName() : "");

    return bookDto;
  }

  public static Book bookDtoToBook(BookDto bookDto) {
    String authorId = bookDto.getAuthorId();
    String genreId = bookDto.getGenreId();
    Book book = new Book(bookDto.getId(), bookDto.getName(), authorId == "0" ? null : new Author(authorId, bookDto.getAuthorName()), genreId == "0" ? null : new Genre(genreId, bookDto.getGenreName()));

    return book;
  }

  public static Flux<BookDto> bookDtoListToFlux(List<BookDto> bookDtoList) {
    Flux<BookDto> bookDtoFlux = Flux.fromIterable(bookDtoList);
    return bookDtoFlux;
  }

  public static Mono<BookDto> bookDtoToBookDtoMono(BookDto bookDto) {
    Mono<BookDto> bookDtoMono = Mono.just(bookDto);
    return bookDtoMono;
  }

  public static Mono<BookDto> bookMonoToBookDtoMono(Mono<Book> bookMono) {
    Book book = bookMonoToBook(bookMono);
    BookDto bookDto = bookToBookDto(book);
    Mono<BookDto> bookDtoMono = bookDtoToBookDtoMono(bookDto);
    return bookDtoMono;
  }

  public static Book bookMonoToBook(Mono<Book> bookMono) {
    Book book = bookMono.block();
    return book;
  }

  public static Flux<BookDto> bookFluxToBookDtoFlux(Flux<Book> bookFlux) {
    List<Book> bookList = bookFlux.collectList().block();
    List<BookDto> bookDtoList = bookList.stream().map(BookDto::bookToBookDto).collect(Collectors.toList());
    return bookDtoListToFlux(bookDtoList);
  }

}
