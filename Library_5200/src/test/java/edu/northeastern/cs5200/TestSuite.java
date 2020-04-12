package edu.northeastern.cs5200;


import edu.northeastern.cs5200.daos.*;
import edu.northeastern.cs5200.models.*;
import org.apache.tomcat.jni.Library;
import org.junit.FixMethodOrder;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TestSuite {

    @Autowired
    LibraryImpl libraryDao;


    static Member andrew, jason, julia, alice;
    static Book sapiens;

    @Test
    @Order(1)
    public void aTest() {
        libraryDao.truncateDatabase();

        andrew = new Member();
        andrew.setFirstName("Andrew");
        andrew.setLastName("Seedholm");
        andrew.setUsername("aseedholm");
        andrew.setPassword("1234");
        andrew.setEmail("a.s@northeastern.edu");

        libraryDao.createMember(andrew);

        List<Member> allMembers = libraryDao.findAllMembers();
        assertEquals(1, allMembers.size());

        sapiens = new Book();
        sapiens.setGenre(Genre.HISTORY);
        sapiens.setTitle("Sapiens");
        sapiens.addCopy();

        libraryDao.createBook(sapiens);
        List<Book> allBooks = libraryDao.findAllBooks();
        assertEquals(1, allBooks.size());

    }

}
