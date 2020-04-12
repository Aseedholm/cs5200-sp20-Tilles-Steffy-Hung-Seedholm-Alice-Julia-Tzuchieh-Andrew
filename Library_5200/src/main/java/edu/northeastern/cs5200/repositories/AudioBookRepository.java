package edu.northeastern.cs5200.repositories;

import edu.northeastern.cs5200.models.AudioBook;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AudioBookRepository extends CrudRepository<AudioBook, Integer> {
}
