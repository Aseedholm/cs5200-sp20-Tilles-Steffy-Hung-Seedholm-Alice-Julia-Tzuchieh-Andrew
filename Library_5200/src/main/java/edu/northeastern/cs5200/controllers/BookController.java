package edu.northeastern.cs5200.controllers;

import edu.northeastern.cs5200.models.Book;
import edu.northeastern.cs5200.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/books")
    public List<Book> findAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

}
