package ru.otus.elena363404.changelogTest;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;


import com.mongodb.client.MongoDatabase;
import ru.otus.elena363404.domain.Author;
import ru.otus.elena363404.domain.Book;
import ru.otus.elena363404.domain.Comment;
import ru.otus.elena363404.domain.Genre;
import ru.otus.elena363404.repository.AuthorRepository;
import ru.otus.elena363404.repository.BookRepository;
import ru.otus.elena363404.repository.CommentRepository;
import ru.otus.elena363404.repository.GenreRepository;

import java.util.ArrayList;
import java.util.List;

@ChangeLog(order = "001")
public class DatabaseChangelog {

    private List<Author> authorList;
    private List<Genre> genreList;
    private List<Book> bookList;

    @ChangeSet(order = "000", id = "dropDb", author = "elena363404", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "001", id = "initAuthor", author = "elena363404")
    public void initAuthor(AuthorRepository repository) {
        authorList = new ArrayList<>();
        authorList.add(new Author("Stephen King"));
        authorList.add(new Author("Alexander Pushkin"));
        authorList.add(new Author("Isaak Newton"));
        authorList.add(new Author("Vladimir Lenin"));
        authorList.add(new Author("Marina Tsvetaeva"));
        for (int i = 0; i < authorList.size(); i++) {
            repository.save(authorList.get(i)).block();
        }
    }

    @ChangeSet(order = "002", id = "initGenre", author = "elena363404")
    public void initGenre(GenreRepository repository) {
        genreList = new ArrayList<>();
        genreList.add(new Genre("Fantastic"));
        genreList.add(new Genre("Political"));
        genreList.add(new Genre("Novel"));
        genreList.add(new Genre("Horror"));
        genreList.add(new Genre("Horror-123"));
        for (int i = 0; i < genreList.size(); i++) {
            repository.save(genreList.get(i)).block();
        }
    }

    @ChangeSet(order = "003", id = "initBook", author = "elena363404")
    public void initBook(BookRepository repository) {
        bookList = new ArrayList<>();
        bookList.add(new Book("Doughter of Capitan", authorList.get(1), genreList.get(2)));
        bookList.add(new Book("Apocalypse", authorList.get(2), genreList.get(0)));
        bookList.add(new Book("Revolution-1", authorList.get(3), genreList.get(1)));
        bookList.add(new Book("Revolution-2", authorList.get(3), genreList.get(1)));
        bookList.add(new Book("It", authorList.get(0), genreList.get(3)));
        for (int i = 0; i < bookList.size(); i++) {
            repository.save(bookList.get(i)).block();
        }
    }

    @ChangeSet(order = "004", id = "initComment", author = "elena363404")
    public void initComment(CommentRepository repository) {
        repository.save(new Comment("Good book!", bookList.get(0))).block();
        repository.save(new Comment("Bad book!", bookList.get(0))).block();
        repository.save(new Comment("Norm book!", bookList.get(1))).block();
    }

}
