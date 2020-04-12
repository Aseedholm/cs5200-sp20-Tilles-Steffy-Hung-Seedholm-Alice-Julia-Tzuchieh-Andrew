package edu.northeastern.cs5200.models;

import javax.persistence.Entity;
import java.sql.Date;

@Entity(name="hard_copy_books")
public class HardCopyBook extends Book {

    private Integer numPages;

    public HardCopyBook() {
        super();
    }

    public HardCopyBook(Integer book_id, String title, Author author, Date yearPublished,
                        Genre genre, String ISBN, Integer numPages) {
        super(book_id, title, author, yearPublished, genre, ISBN);
        this.numPages = numPages;

    }

    public Integer getNumPages() {
        return numPages;
    }

    public void setNumPages(Integer numPages) {
        this.numPages = numPages;
    }


}
