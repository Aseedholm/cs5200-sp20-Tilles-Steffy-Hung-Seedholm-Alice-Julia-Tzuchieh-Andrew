package edu.northeastern.cs5200.repositories;

import edu.northeastern.cs5200.models.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;


@Repository
public interface MemberRepository extends CrudRepository<Member, Integer> {


}
