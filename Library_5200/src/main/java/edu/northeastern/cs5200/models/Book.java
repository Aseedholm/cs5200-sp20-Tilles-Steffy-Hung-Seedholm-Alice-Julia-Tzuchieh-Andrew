package edu.northeastern.cs5200.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Author author;

    private String title;
    private Date yearPublished;

    private String genre;

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    private Set<BookCopy> bookCopies;

    @Column(unique=true)
    private String ISBN;

    public Book(){
        this.bookCopies = new HashSet<BookCopy>();
    }

    public Book(Integer id, String title, Author author, Date yearPublished,
                String genre, String ISBN, Set<BookCopy> bookCopies) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.genre = genre;
        this.ISBN = ISBN;
        this.bookCopies = bookCopies;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        System.out.println("Genre: " + genre);
        this.genre = genre.toString();
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<BookCopy> getBookCopies() {
        return bookCopies;
    }

    public void setBookCopies(Set<BookCopy> bookCopies) {
        this.bookCopies = bookCopies;
    }

    public void addHardCoverCopy(){
        HardCopyBook newCopy = new HardCopyBook();
        newCopy.setBook(this);
        newCopy.setAvailable(true);
        newCopy.setEdition(null);
        newCopy.setCurrentCondition(CurrentCondition.NEW);
        this.bookCopies.add(newCopy);
    }

    public void addAudiobook(){
        AudioBook newCopy = new AudioBook();
        newCopy.setBook(this);
        newCopy.setAvailable(true);
        this.bookCopies.add(newCopy);
    }


    @Override
    public String toString() {
        return "Book{" +
                "book_id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", yearPublished=" + yearPublished +
                ", genre=" + genre +
                ", ISBN='" + ISBN + '\'' +
                '}';
    }
}
