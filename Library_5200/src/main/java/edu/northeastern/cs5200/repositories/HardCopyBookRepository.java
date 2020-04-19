package edu.northeastern.cs5200.repositories;

import edu.northeastern.cs5200.models.Book;
import edu.northeastern.cs5200.models.HardCopyBook;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface HardCopyBookRepository extends CrudRepository<HardCopyBook, Integer> {

    @Query("SELECT hardCopyBook FROM BookCopy hardCopyBook, Book book " +
            "WHERE hardCopyBook.book = book AND hardCopyBook.class = 'hard_copy' AND hardCopyBook.isAvailable=true")
    Set<HardCopyBook> findAvailableBooksById(Integer id);

}
