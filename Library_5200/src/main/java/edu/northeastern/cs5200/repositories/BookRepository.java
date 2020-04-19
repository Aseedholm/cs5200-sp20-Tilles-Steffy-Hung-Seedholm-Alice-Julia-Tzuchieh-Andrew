package edu.northeastern.cs5200.repositories;

import edu.northeastern.cs5200.models.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {


    @Query("SELECT Book FROM Book book where book.title=:title")
    Book findBookByTitle(String title);


}
