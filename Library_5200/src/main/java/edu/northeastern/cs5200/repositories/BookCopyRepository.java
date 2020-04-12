package edu.northeastern.cs5200.repositories;

import edu.northeastern.cs5200.models.BookCopy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCopyRepository extends CrudRepository<BookCopy, Integer> {
}
