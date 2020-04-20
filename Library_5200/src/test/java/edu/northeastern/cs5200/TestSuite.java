package edu.northeastern.cs5200;

import edu.northeastern.cs5200.daos.*;
import edu.northeastern.cs5200.dataloader.GoogleBooksAPI;
import edu.northeastern.cs5200.models.*;
import org.apache.tomcat.jni.Library;
import org.json.simple.parser.ParseException;
import org.junit.FixMethodOrder;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.Condition;

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
//		Admin admin = new Admin();
//		admin.setFirstName("AR");
//		admin.setLastName("AAR");
//		admin.setUsername("AAAR");
//		admin.setPassword("1234");
//		admin.setEmail("AR@northeastern.edu");
//		libraryDao.createAdmin(admin);
//		Member member = new Member();
//		member.setFirstName("B");
//		member.setLastName("BB");
//		member.setUsername("BBB");
//		member.setPassword("1234");
//		member.setEmail("B@northeastern.edu");
//		libraryDao.createMember(member);
//		Librarian librarian = new Librarian();
//		librarian.setFirstName("L");
//		librarian.setLastName("LL");
//		librarian.setUsername("LLL");
//		librarian.setPassword("1234");
//		librarian.setEmail("L@northeastern.edu");
//		libraryDao.createLibrarian(librarian);
//		Author author = new Author();
//		author.setFirstName("F");
//		author.setLastName("L");
//		author.setDateOfBirth(new java.sql.Timestamp(100, 4, 20, 9, 9, 9, 9));
//		author.setDateOfDeath(new java.sql.Timestamp(200, 4, 20, 9, 9, 9, 9));
//		libraryDao.createAuthor(author);
//		System.out.println("CardID = " + card.getId());
//	}

//	@Test
//	@Order(3)
//	public void testIsUnderThirteen() {
//
//		libraryDao.truncateDatabase();
//
//		Member olderKid = new Member();
//		olderKid.setDateOfBirth(new java.sql.Timestamp(100, 6, 18, 9, 9, 9, 9));
//		olderKid.setFirstName("Older Kid");
//		olderKid.setUsername("OldKid1000");
//		libraryDao.createMember(olderKid);
//		assertFalse(olderKid.isUnderThirteen());
//
//		Member youngerKid = new Member();
//		youngerKid.setDateOfBirth(new java.sql.Timestamp(119, 6, 18, 9, 9, 9, 9));
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

//	@Test
//	public void populateDB(){
//
//		libraryDao.dropBooks();
//
//		Book sapiens = new Book();
//		sapiens.setTitle("Sapiens");
//		sapiens.setGenre(Genre.HISTORY);
//		sapiens.setISBN("abc123");
//
//		HardCopyBook sapiens1 = new HardCopyBook();
//		sapiens1.setBook(sapiens);
//		sapiens1.setAvailable(true);
//		sapiens1.setNumPages(400);
//		sapiens1.setCurrentCondition(CurrentCondition.NEW);
//
//		libraryDao.createBook(sapiens);
//		libraryDao.createHardCopyBook(sapiens1);
//
//
//		Book signal = new Book();
//		signal.setTitle("The Signal and the Noise");
//		signal.setGenre(Genre.SCIENCE);
//		signal.setISBN("zaz321");
//		signal.addAudiobook();
//		signal.addAudiobook();
//		signal.addHardCoverCopy();
//		libraryDao.createBook(signal);
//
//	}

//	@Test
//	public void testCheckOutBook() {
//		Member steve = libraryDao.findMemberByUsername("johnsmith");
//		Book desiredBook1 = libraryDao.findBookByTitle("Sapiens");
//		Book desiredBook2 = libraryDao.findBookByTitle("The Signal and the Noise");
//		libraryDao.checkOutBookHardCopy(steve.getId(), desiredBook1.getId());
//		libraryDao.checkOutAudiobook(steve.getId(), desiredBook2.getId());
//		assertEquals(2,libraryDao.findAllLegerEntries().size());
//	}


//	@Test
//	public void testCheckOutBook() {
//
//
//		Member steve = libraryDao.findMemberByUsername("johnsmith");
//		Book desiredBook = libraryDao.findBookByTitle("The Signal and the Noise");
//		//libraryDao.checkOutBookHardCopy(steve, desiredBook);
//		libraryDao.checkOutAudiobook(steve.getId(), desiredBook.getId());
//		assertEquals(2,libraryDao.findAllLegerEntries().size());
//
//
//
//	}

	@Test
	public void testLoadBooks() throws IOException, ParseException {
		GoogleBooksAPI api = new GoogleBooksAPI(libraryDao);
		api.loadFromAPI("https://www.googleapis.com/books/v1/volumes?q=mozart" +
				"&key=AIzaSyDzAEzIpOLfuwaEQcXsB-5vSN7b7lzJiMc");

	}


}
