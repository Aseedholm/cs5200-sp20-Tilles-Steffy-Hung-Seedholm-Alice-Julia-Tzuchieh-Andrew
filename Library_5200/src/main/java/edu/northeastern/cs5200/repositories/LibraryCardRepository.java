package edu.northeastern.cs5200.repositories;

import edu.northeastern.cs5200.models.LibraryCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryCardRepository extends CrudRepository<LibraryCard, Integer> {
}
