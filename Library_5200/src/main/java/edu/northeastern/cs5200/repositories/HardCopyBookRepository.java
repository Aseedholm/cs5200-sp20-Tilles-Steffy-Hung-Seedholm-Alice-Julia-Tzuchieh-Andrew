package edu.northeastern.cs5200.repositories;

import edu.northeastern.cs5200.models.HardCopyBook;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HardCopyBookRepository extends CrudRepository<HardCopyBook, Integer> {
}
