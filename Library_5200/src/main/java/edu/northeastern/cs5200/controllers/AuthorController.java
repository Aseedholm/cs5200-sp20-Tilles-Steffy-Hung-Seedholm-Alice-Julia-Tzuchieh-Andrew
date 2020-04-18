package edu.northeastern.cs5200.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


import edu.northeastern.cs5200.models.*;
import edu.northeastern.cs5200.repositories.*;

@RestController
@CrossOrigin(origins = "*")
public class AuthorController {

  @Autowired
  AuthorRepository authorRepository;

  @GetMapping("/api/authors")
  public List<Author> findAllAuthors() {
    return (List<Author>) authorRepository.findAll();
  }



}
