package edu.northeastern.cs5200.repositories;

import edu.northeastern.cs5200.models.Book;
import edu.northeastern.cs5200.models.HardCopyBook;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HardCopyBookRepository extends CrudRepository<HardCopyBook, Integer> {

    @Query("SELECT BookCopy FROM Book book, BookCopy book_copy WHERE book.title=:title and book_copy.isAvailable=true")
    HardCopyBook findAvailableBookByTitle(String title);


}
