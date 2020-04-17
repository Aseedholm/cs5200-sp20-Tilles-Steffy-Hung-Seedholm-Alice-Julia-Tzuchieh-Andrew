package edu.northeastern.cs5200.controllers;

import edu.northeastern.cs5200.daos.LibraryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import edu.northeastern.cs5200.models.*;
import edu.northeastern.cs5200.repositories.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class AdminController {

  @Autowired
  LibraryDao libraryDao;

  @PostMapping("api/admins")
  public Admin createAdmin(@RequestBody Admin admin) {
    return libraryDao.createAdmin(admin);
  }

  @GetMapping("/api/admins")
  public List<Admin> findAllAdmin() {
    return (List<Admin>) libraryDao.findAllAdmin();
  }

}
