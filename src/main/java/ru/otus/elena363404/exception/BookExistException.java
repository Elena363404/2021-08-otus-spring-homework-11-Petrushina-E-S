package ru.otus.elena363404.exception;

import org.springframework.http.HttpStatus;

public class BookExistException extends RuntimeException {

  private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  public BookExistException(HttpStatus httpStatus, String message) {
    super(message);
    this.httpStatus = httpStatus;
  }
}
