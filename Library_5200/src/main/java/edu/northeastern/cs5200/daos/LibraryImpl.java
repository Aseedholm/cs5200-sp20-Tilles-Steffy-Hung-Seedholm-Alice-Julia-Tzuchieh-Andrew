package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.models.*;
import edu.northeastern.cs5200.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

@Repository
public class LibraryImpl implements LibraryDao {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    AudioBookRepository audioBookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookCopyRepository bookCopyRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    HardCopyBookRepository hardCopyBookRepository;

    @Autowired
    LegerEntryRepository legerEntryRepository;

    @Autowired
    LibrarianRepository librarianRepository;

    @Autowired
    LibraryCardRepository libraryCardRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    UserRepository userRepository;


    @Override
    public void truncateDatabase() {
        adminRepository.deleteAll();
        audioBookRepository.deleteAll();
        authorRepository.deleteAll();
        bookCopyRepository.deleteAll();
        bookRepository.deleteAll();
        hardCopyBookRepository.deleteAll();
        legerEntryRepository.deleteAll();
        librarianRepository.deleteAll();
        libraryCardRepository.deleteAll();
        memberRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Override
    public List<Admin> findAllAdmin() {
        return (List<Admin>) adminRepository.findAll();
    }

    @Override
    public List<AudioBook> findAllAudioBooks() {
        return (List<AudioBook>)audioBookRepository.findAll();
    }

    @Override
    public List<Author> findAllAuthors() {
        return (List<Author>)authorRepository.findAll();
    }

    @Override
    public List<Book> findAllBooks() {
        return (List<Book>)bookRepository.findAll();
    }

    @Override
    public List<BookCopy> findAllBookCopies() {
        return (List<BookCopy>)bookCopyRepository.findAll();
    }

    @Override
    public List<HardCopyBook> findAllHardCopyBooks() {
        return (List<HardCopyBook>)hardCopyBookRepository.findAll();
    }

    @Override
    public List<LegerEntry> findAllLegerEntries() {
        return (List<LegerEntry>)legerEntryRepository.findAll();
    }

    @Override
    public List<Librarian> findAllLibrarians() {
        return (List<Librarian>)librarianRepository.findAll();
    }

    @Override
    public List<LibraryCard> findAllLibraryCards() {
        return (List<LibraryCard>)libraryCardRepository.findAll();
    }

    @Override
    public List<Member> findAllMembers() {
        return (List<Member>)memberRepository.findAll();
    }

    @Override
    public List<User> findAllUsers() {
        return (List<User>)userRepository.findAll();
    }

    @Override
    public void createAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    @Override
    public void createAudioBook(AudioBook audioBook) {
        audioBookRepository.save(audioBook);
    }

    @Override
    public void createAuthor(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void createBook(Book book) {
        bookRepository.save(book);
        bookCopyRepository.saveAll(book.getBookCopies());

    }

    @Override
    public void createHardCopyBook(HardCopyBook book) {
        hardCopyBookRepository.save(book);
    }

    @Override
    public void createLegerEntry(LegerEntry entry) {
        legerEntryRepository.save(entry);
    }

    @Override
    public void createLibrarian(Librarian librarian) {
        librarianRepository.save(librarian);
    }

    @Override
    public void createLibraryCard(LibraryCard card) {
        libraryCardRepository.save(card);
    }

    @Override
    public void createMember(Member member) {
        memberRepository.save(member);
    }

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }


    @Override
    public boolean hasValidLibraryCard(Member member) {
        Calendar calendar = Calendar.getInstance();
        java.sql.Date currentDate = new java.sql.Date(calendar.getTime().getTime());

        Member foundMember = memberRepository.findById(member.getId()).get();
        Date expirationDate = foundMember.getLibraryCard().getExpirationDate();

        if (currentDate.compareTo(expirationDate) < 0) {
            return true;
        }

        return false;

    }

    @Override
    public boolean checkOutBook(Member member, Book book) {

        // Check that user is not suspended
        // Check that user's library card is active
        if (!hasValidLibraryCard(member)) {
            return false;
        }
        // Check that there exist at least one copy of the book

        BookCopy bookToBorrow = findAvailableCopy(book);
        if(bookToBorrow == null) {
            return false;
        }

        // Finally, check out the book by creating a leger entry
        Calendar calendar = Calendar.getInstance();
        java.sql.Date currentDate = new java.sql.Date(calendar.getTime().getTime());
        LegerEntry newEntry = new LegerEntry(member.getId(), book.getId(), currentDate, null);
        legerEntryRepository.save(newEntry);
        return true;

    }

    @Override
    public BookCopy findAvailableCopy(Book book) {
        //TODO way to optimize this? Right now it loads all to memory, then hunts for a matching book id.
        // Should probably override with SQL

        Set<BookCopy> copies = (Set<BookCopy>)bookCopyRepository.findAll();

        // Iterate through all copies
        for (BookCopy c : copies) {

            // If this is a copy of the correct book and it is available, return it
            if (c.getBook().getId() == book.getId() && isBookCopyAvailable(c)) {

                return c;

            }
        }

        return null;

    }


    public boolean isBookCopyAvailable(BookCopy bookCopy) {

        //TODO should we keep this stored as a variable or do a lookup each time?
        // As a variable, there is risk of inconsistancy
        // As a lookup, it takes longer

        Set<LegerEntry> allLegerEntries = (Set<LegerEntry>)legerEntryRepository.findAll();
        for (LegerEntry entry : allLegerEntries) {
            if (entry.getId().getBookCopyId() == bookCopy.getId()) {
                return false;
            }
        }

        return true;

    }


}
