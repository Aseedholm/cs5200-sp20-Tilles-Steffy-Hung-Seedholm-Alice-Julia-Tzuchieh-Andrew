package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.models.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface LibraryDao {

    void truncateDatabase();
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

    void createAdmin(Admin admin);
    void createAudioBook(AudioBook audioBook);
    void createAuthor(Author author);
    void createBook(Book book);
    void createHardCopyBook(HardCopyBook book);
    void createLegerEntry(LegerEntry entry);
    void createLibrarian(Librarian librarian);
    void createLibraryCard(LibraryCard card);
    void createMember(Member member);
    void createUser(User user);

    boolean hasValidLibraryCard(Member member);
    boolean checkOutBook(Member member, Book book);
    BookCopy findAvailableCopy(Book book);

}


