package edu.northeastern.cs5200.controllers;

import edu.northeastern.cs5200.daos.LibraryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import edu.northeastern.cs5200.models.*;
import edu.northeastern.cs5200.repositories.*;

@RestController
@CrossOrigin(origins = "*")
public class AdminController {

  @Autowired
  LibraryDao libraryDao;

  @PostMapping("api/admins")
  public Admin createAdmin(@RequestBody Admin admin) {
    return libraryDao.createAdmin(admin);
  }
}
