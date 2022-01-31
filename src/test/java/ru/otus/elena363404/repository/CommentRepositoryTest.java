package ru.otus.elena363404.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.elena363404.changelogTest.MongoIdForTest;
import ru.otus.elena363404.domain.Book;
import ru.otus.elena363404.domain.Comment;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class CommentRepositoryTest {

  private static final String EXISTING_COMMENT_ID = MongoIdForTest.idComment2;
  private static final String EXISTING_BOOK_ID = MongoIdForTest.idBook1;
  private static final String COMMENT_ID_FOR_DELETE = MongoIdForTest.idComment3;
  private static final String COMMENT_ID_FOR_UPDATE = MongoIdForTest.idComment3;
  private static final int EXPECTED_NUMBER_OF_COMMENTS = 4;

  @Autowired
  private ReactiveMongoRepository<Comment, String> cr;

  @Autowired
  private ReactiveMongoRepository<Book, String> crb;

  @Autowired
  private CommentRepository commentRepository;

  @DisplayName("Return comment by ID")
  @Test
  void shouldReturnExpectedCommentById() {
    Comment optionalActualComment = commentRepository.findById(EXISTING_COMMENT_ID).block();
    Comment expextedComment = cr.findById(EXISTING_COMMENT_ID).block();
    assertThat(optionalActualComment).isEqualTo(expextedComment);
  }

  @DisplayName("Update comment by ID")
  @Test
  void shouldUpdateExpectedCommentById() {
    Comment newComment = new Comment(COMMENT_ID_FOR_UPDATE, "Comment after update!", crb.findById("2").block());
    newComment = commentRepository.save(newComment).block();
    Comment updatedComment = cr.findById(COMMENT_ID_FOR_UPDATE).block();

    assertThat(newComment).isEqualTo(updatedComment);
  }

  @DisplayName("Return list of the comments")
  @Test
  void shouldReturnExpectedCommentsList() {
    System.out.println("\n\n\n\n----------------------------------------------------------------------------------------------------------");
    List<Comment> comments = commentRepository.findAll().collectList().block();
    assertThat(comments).isNotNull().hasSize(EXPECTED_NUMBER_OF_COMMENTS)
      .allMatch(s -> !s.getComment().equals(""));
    System.out.println("----------------------------------------------------------------------------------------------------------\n\n\n\n");
  }

  private List<Comment> getCommentListByBookId(String bookId) {
    Book book = crb.findById(bookId).block();
    List<Comment> commentList = new ArrayList<>();
    commentList.add(new Comment( MongoIdForTest.idComment1, "Good book!", book));
    commentList.add(new Comment( MongoIdForTest.idComment2, "Bad book!", book));

    return commentList;
  }
}