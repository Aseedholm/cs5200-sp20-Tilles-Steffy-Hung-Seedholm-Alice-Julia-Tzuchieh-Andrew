package edu.northeastern.cs5200.models;

import javax.persistence.*;
import java.sql.Date;

@Entity(name="books")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    @ManyToOne
    private Author author;

    private String title;
    private Date yearPublished;
    private Genre genre;

    @Column(unique=true)
    private String ISBN;

    public Book(){

    }

    public Book(Integer bookId, String title, Author author, Date yearPublished,
                Genre genre, String ISBN) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.genre = genre;
        this.ISBN = ISBN;
    }

    public Integer getBook_id() {
        return bookId;
    }

    public void setBook_id(Integer book_id) {
        this.bookId = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Date getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(Date yearPublished) {
        this.yearPublished = yearPublished;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    @Override
    public String toString() {
        return "Book{" +
                "book_id=" + bookId +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", yearPublished=" + yearPublished +
                ", genre=" + genre +
                ", ISBN='" + ISBN + '\'' +
                '}';
    }
}
