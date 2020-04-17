package edu.northeastern.cs5200.controllers;

import edu.northeastern.cs5200.daos.LibraryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import edu.northeastern.cs5200.models.*;
import edu.northeastern.cs5200.repositories.*;

@RestController
@CrossOrigin(origins = "*")
public class AuthorController {

  @Autowired
  LibraryDao libraryDao;

  @PostMapping("api/author")
  public Author createAuthor(@RequestBody Author author) {
    return libraryDao.createAuthor(author);
  }

  @GetMapping("/api/authors")
  public List<Author> findAllAuthors() {
    return (List<Author>) libraryDao.findAllAuthors();
  }




}
