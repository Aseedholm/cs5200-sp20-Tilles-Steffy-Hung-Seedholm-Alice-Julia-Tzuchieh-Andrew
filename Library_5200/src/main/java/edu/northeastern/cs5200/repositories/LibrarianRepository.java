package edu.northeastern.cs5200.repositories;

import edu.northeastern.cs5200.models.Librarian;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibrarianRepository extends CrudRepository<Librarian, Integer> {
}
