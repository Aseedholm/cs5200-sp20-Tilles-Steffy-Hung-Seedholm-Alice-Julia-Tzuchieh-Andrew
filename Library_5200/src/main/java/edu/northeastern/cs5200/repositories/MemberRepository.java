package edu.northeastern.cs5200.repositories;

import edu.northeastern.cs5200.models.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends CrudRepository<Member, Integer> {
}
