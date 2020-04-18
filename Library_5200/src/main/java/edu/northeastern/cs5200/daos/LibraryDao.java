package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.models.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public interface LibraryDao {


    void truncateDatabase();

    // Finder methods -> find all
    List<Admin> findAllAdmin();
    List<AudioBook> findAllAudioBooks();
    List<Author> findAllAuthors();
    List<Book> findAllBooks();
    List<BookCopy> findAllBookCopies();
    List<HardCopyBook> findAllHardCopyBooks();
    List<LegerEntry> findAllLegerEntries();
    List<Librarian> findAllLibrarians();
    List<LibraryCard> findAllLibraryCards();
    List<Member> findAllMembers();
    List<User> findAllUsers();

    // Finder methods -> find by ID
    Member findMemberById(int id);


    // Finder methods -> find by some other attribute
    Member findMemberByUsername(String username);

    // Create a single object  methods
    Admin createAdmin(Admin admin);
    AudioBook createAudioBook(AudioBook audioBook);
    Author createAuthor(Author author);
    Book createBook(Book book);
    HardCopyBook createHardCopyBook(HardCopyBook book);
    LegerEntry createLegerEntry(LegerEntry entry);
    Librarian createLibrarian(Librarian librarian);
    LibraryCard createLibraryCard(LibraryCard card);
    Member createMember(Member member);
    User createUser(User user);




    // More advanced methods
    boolean hasValidLibraryCard(Member member);
    boolean checkOutBook(Member member, Book book);
    BookCopy findAvailableCopy(Book book);

}


