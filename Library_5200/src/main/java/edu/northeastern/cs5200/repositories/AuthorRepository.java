package edu.northeastern.cs5200.repositories;

import edu.northeastern.cs5200.models.Author;
import edu.northeastern.cs5200.models.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthorRepository extends CrudRepository<Author, Integer> {

}
