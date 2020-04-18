package edu.northeastern.cs5200.controllers;

import edu.northeastern.cs5200.daos.LibraryDao;
import edu.northeastern.cs5200.models.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.northeastern.cs5200.models.Librarian;
import edu.northeastern.cs5200.repositories.LibrarianRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class LibrarianController {


  @Autowired
  LibraryDao libraryDao;

  @PostMapping("api/librarians")
  public Librarian createLibrarian(@RequestBody Librarian librarian) {
    return libraryDao.createLibrarian(librarian);
  }

  @GetMapping("/api/librarians")
  public List<Librarian> findAllLibrarians() {
    return (List<Librarian>) libraryDao.findAllLibrarians();
  }

}
