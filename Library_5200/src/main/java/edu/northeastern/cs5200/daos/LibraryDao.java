package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.models.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public interface LibraryDao {


    void truncateDatabase();
    void dropBooks();


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
    Book findBookById(String id);
    Member findMemberById(int id);
    Librarian findLibrarianById(int id);
    LibraryCard findLibraryCardByMemberId(int memberId);


    // Finder methods -> find by some other attribute
    // >> Find single object
    Member findMemberByUsername(String username);
    Librarian findLibrarianByUsername(String username);
    LibraryCard findLibraryCardByMemberUsername(String memberUsername);
    Book findBookByTitle(String title);

    // >> Find a set of objects
    Set<HardCopyBook> findHardCopyBooksByBookId(String id);
    Set<AudioBook> findAudioBooksByBookId(String id);

    // Create a single object methods
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

    // Specialized single-create methods
    HardCopyBook addHardCopy(String bookId);
    AudioBook addAudiobook(String bookId);


    // Delete by ID methods
    boolean deleteAdmin(Integer id);
    boolean deleteLibrarian(Integer id);
    boolean deleteMember(Integer id);


    // More advanced methods
    boolean hasValidLibraryCard(Member member);

    // To check out books
    LegerEntry checkOutBookHardCopy(Integer memberId, String bookId);
    boolean checkOutAudiobook(Integer memberId, String bookId);
    Set<HardCopyBook> findAvailableHardCopies(Book book);
    Set<AudioBook> findAvailableAudiobooks(Book book);
    Set<Object[]> seeCheckedOutBooksAllTime(Integer memberId);
    Set<Object[]> seeCheckedOutBooksCurrently(Integer memberId);
}


