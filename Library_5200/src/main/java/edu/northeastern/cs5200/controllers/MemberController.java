package edu.northeastern.cs5200.controllers;

import edu.northeastern.cs5200.daos.LibraryDao;
import edu.northeastern.cs5200.models.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.northeastern.cs5200.models.Member;
import edu.northeastern.cs5200.repositories.MemberRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class MemberController {

  @Autowired
  LibraryDao libraryDao;

  @PostMapping("api/member")
  public Member createMember(@RequestBody Member member) {
    return libraryDao.createMember(member);
  }

  @GetMapping("/api/members")
  public List<Member> findAllMembers() {
    return (List<Member>) libraryDao.findAllMembers();
  }

  @GetMapping("/api/member/id/{id}")
  public Member getById(@PathVariable("id") int id) {
    return libraryDao.findMemberById(id);
  }

  @GetMapping("/api/member/username/{username}")
  public Member getByUsername(@PathVariable("username") String username) {
    System.out.println("looking for: " + username);
    return libraryDao.findMemberByUsername(username);
  }


}
