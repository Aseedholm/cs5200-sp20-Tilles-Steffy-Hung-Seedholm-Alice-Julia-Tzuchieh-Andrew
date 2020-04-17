package edu.northeastern.cs5200.controllers;

import edu.northeastern.cs5200.daos.LibraryDao;
import edu.northeastern.cs5200.models.*;
import edu.northeastern.cs5200.repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class BookController {

    @Autowired
    LibraryDao libraryDao;


    @PostMapping("api/book")
    public Book createBook(@RequestBody Book book) {
        return libraryDao.createBook(book);
    }

    @GetMapping("api/books")
    public List<Book> findAllBooks() {
        return (List<Book>) libraryDao.findAllBooks();
    }



}
