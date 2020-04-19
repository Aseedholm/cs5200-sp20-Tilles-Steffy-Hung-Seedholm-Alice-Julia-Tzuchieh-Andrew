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
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

//	@Test
//	@Order(1)
//	public void aTest() {
//        libraryDao.truncateDatabase();
//
//        andrew = new Member();
//        andrew.setFirstName("Andrew");
//        andrew.setLastName("Seedholm");
//        andrew.setUsername("aseedholm");
//        andrew.setPassword("1234");
//        andrew.setEmail("a.s@northeastern.edu");
//
//        libraryDao.createMember(andrew);
//
//        List<Member> allMembers = libraryDao.findAllMembers();
//        assertEquals(1, allMembers.size());
//
//        sapiens = new Book();
//        sapiens.setGenre(Genre.HISTORY);
//        sapiens.setTitle("Sapiens");
//        sapiens.addCopy();
//
//        libraryDao.createBook(sapiens);
//        List<Book> allBooks = libraryDao.findAllBooks();
//        assertEquals(1, allBooks.size());
//
//	}

//	@Test
//	@Order(2)
//	public void bTest() {
//		Member member = new Member();
//		member.setFirstName("A");
//		member.setLastName("AA");
//		member.setUsername("AAA");
//		member.setPassword("1234");
//		member.setEmail("A@northeastern.edu");
//		Member member = new Member();
//		member.setFirstName("B");
//		member.setLastName("BB");
//		member.setUsername("BBB");
//		member.setPassword("1234");
//		member.setEmail("B@northeastern.edu");
//		Member member = new Member();
//		member.setFirstName("C");
//		member.setLastName("CC");
//		member.setUsername("CCC");
//		member.setPassword("1234");
//		member.setEmail("C@northeastern.edu");
//		libraryDao.createMember(member);
//		System.out.println("CardID = " + card.getId());
//	}

//	@Test
//	@Order(3)
//	public void testIsUnderThirteen() {
//
//		libraryDao.truncateDatabase();
//
//
//
//		Member olderKid = new Member();
//		olderKid.setDateOfBirth(new java.sql.Date(100, 6, 18));
//		olderKid.setFirstName("Older Kid");
//		olderKid.setUsername("OldKid1000");
//		libraryDao.createMember(olderKid);
//		assertFalse(olderKid.isUnderThirteen());
//
//
//		Member youngerKid = new Member();
//		youngerKid.setDateOfBirth(new java.sql.Date(119, 6, 18));
//		youngerKid.setFirstName("Younger Kid");
//		youngerKid.setUsername("little_guy");
//		youngerKid.setSponsoredBy(libraryDao.findMemberByUsername("OldKid1000").getId());
//		libraryDao.createMember(youngerKid);
//
//		assertTrue(youngerKid.isUnderThirteen());
//		assertFalse(olderKid.isUnderThirteen());
//
//		// TODO make it work/test with almost exactly  13 years old
//
//	}

}
