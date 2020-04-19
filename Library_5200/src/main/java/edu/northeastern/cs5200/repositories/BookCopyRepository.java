package edu.northeastern.cs5200.repositories;

import edu.northeastern.cs5200.models.Book;
import edu.northeastern.cs5200.models.BookCopy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCopyRepository extends CrudRepository<BookCopy, Integer> {

    @Query("SELECT BookCopy FROM Book book, BookCopy book_copy WHERE book.title=:title and book_copy.isAvailable=true")
    BookCopy findAvailableBookByTitle(String title);

}
