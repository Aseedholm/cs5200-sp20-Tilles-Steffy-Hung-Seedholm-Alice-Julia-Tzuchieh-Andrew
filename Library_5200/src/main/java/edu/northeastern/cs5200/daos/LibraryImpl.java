package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.models.*;
import edu.northeastern.cs5200.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public void dropBooks(){
        bookCopyRepository.deleteAll();
        bookRepository.deleteAll();
        hardCopyBookRepository.deleteAll();
        audioBookRepository.deleteAll();
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
    public Book findBookById(int id) {
        return null;
    }

    @Override
    public Member findMemberById(int id) {

        if (memberRepository.findById(id).isEmpty()) {
            return null;
        }

        return memberRepository.findById(id).get();
    }

    @Override
    public Librarian findLibrarianById(int id) {
        Librarian librarian = librarianRepository.findById(id).get();
        if (librarian == null) {
            return null;
        }
        return librarian;
    }

    @Override
    public LibraryCard findLibraryCardByMemberId(int memberId) {

        // Make sure member exists
    	var foundMember = memberRepository.findById(memberId);
    	if (foundMember.isPresent()) {
    	    Member member = foundMember.get();

    	    // Make sure the library card exists
            var foundCard = libraryCardRepository.findById(member.getLibraryCard().getId());
            if (foundCard.isPresent()) {
                return foundCard.get();
            }
            return null;
        }

    	return null;

    }

    @Override
    public Set<HardCopyBook> findHardCopyBooksByBookId(int id) {
        return hardCopyBookRepository.findByBookId(id);
    }

    @Override
    public Set<AudioBook> findAudioBooksByBookId(int id) {
        return audioBookRepository.findByBookId(id);
    }

    @Override
    public Member findMemberByUsername(String username) {
        //TODO use jpql
        Iterable<Member> members = memberRepository.findAll();

        for (Member m : members) {
            if (m.getUsername() == null) {
                continue;
            }
            if (m.getUsername().equals(username)) {
                return m;
            }
        }

        return null;
    }

    @Override
    public Librarian findLibrarianByUsername(String username) {
        //TODO use jpql
        Iterable<Librarian> librarians = librarianRepository.findAll();

        for (Librarian l : librarians) {
            if (l.getUsername() == null) {
                continue;
            }
            if (l.getUsername().equals(username)) {
                return l;
            }
        }

        return null;
    }
    
    @Override
    public LibraryCard findLibraryCardByMemberUsername(String memberUsername) {
        //TODO use jpql
    	Iterable<Member> members = memberRepository.findAll();

        for (Member m : members) {
            if (m.getUsername().equals(memberUsername)) {
            	return findLibraryCardByMemberId(m.getId());
            }
        }

        return null;
    }

    @Override
    public Book findBookByTitle(String title) {
        return bookRepository.findBookByTitle(title);
    }

    @Override
    public Admin createAdmin(Admin admin) {
        adminRepository.save(admin);
        return admin;
    }

    @Override
    public AudioBook createAudioBook(AudioBook audioBook) {
        audioBookRepository.save(audioBook);
        return audioBook;
    }

    @Override
    public Author createAuthor(Author author) {
        authorRepository.save(author);
        return author;
    }

    @Override
    public Book createBook(Book book) {
        bookRepository.save(book);
        bookCopyRepository.saveAll(book.getBookCopies());
        return book;

    }

    @Override
    public HardCopyBook createHardCopyBook(HardCopyBook book) {
        hardCopyBookRepository.save(book);
        return book;
    }

    @Override
    public LegerEntry createLegerEntry(LegerEntry entry) {
        legerEntryRepository.save(entry);
        return entry;
    }

    @Override
    public Librarian createLibrarian(Librarian librarian) {
        librarianRepository.save(librarian);
        return librarian;
    }

    @Override
    public LibraryCard createLibraryCard(LibraryCard card) {
        libraryCardRepository.save(card);
        return card;
    }

    @Override
    public Member createMember(Member member) {

        // Validate sponsorship - if age is < 13, must have valid sponsor
        if (member.isUnderThirteen() ) {
            System.out.println("Attempting to create a member under thirteen.");

            // When creating a new member <13 years old, they must have a sponsor.
            if (member.getSponsoredBy() == null) {
                System.out.println("You must enter a sponsor ID.");
                return null;
            }

            // Find the sponsor in the database
            var sponsorFound = memberRepository.findById(member.getSponsoredBy());

            // Make sure the sponsor exists in the DB
            if (sponsorFound.isPresent()) {

                Member sponsor = sponsorFound.get();

                // Make sure the sponsor is over 13
                if (!sponsor.isUnderThirteen()) {
                    member.setSponsoredBy(sponsor.getId());

                    Calendar today = Calendar.getInstance();
                    today.add(Calendar.YEAR, 5);
            		LibraryCard card = new LibraryCard(member.getId(), member, new java.sql.Date(today.getTime().getTime()));
            		member.setLibraryCard(card);
                    
                    // If so, save new member who is under 13 to db
                    memberRepository.save(member);
                    return member;
                }
                else {
                    System.out.println("Sponsor is not old enough to sponsor another library member.");
                    // TO DO throw exception?
                    return null;
                }
            }
            else {
                System.out.println("Sponsor not found.");
                // TO DO throw exception?
                return null;
            }
        }
        else {
            Calendar today = Calendar.getInstance();
            today.add(Calendar.YEAR, 5);
    		LibraryCard card = new LibraryCard(member.getId(), member, new java.sql.Date(today.getTime().getTime()));
    		member.setLibraryCard(card);
    		
            memberRepository.save(member);
            return member;
        }
        
    }

    @Override
    public User createUser(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public HardCopyBook addHardCopy(Integer bookId) {
        var foundBookInDb = bookRepository.findById(bookId);

        // Make sure book ID is valid
        if (foundBookInDb.isPresent()) {
            HardCopyBook hardCopyBook = new HardCopyBook();
            hardCopyBook.setCurrentCondition(CurrentCondition.NEW);
            hardCopyBook.setAvailable(true);
            hardCopyBook.setBook(foundBookInDb.get());
            hardCopyBookRepository.save(hardCopyBook);
            return hardCopyBook;
        }

        return null;

    }

    @Override
    public AudioBook addAudiobook(Integer bookId) {
        var foundBookInDb = bookRepository.findById(bookId);

        // Make sure book ID is valid
        if (foundBookInDb.isPresent()) {
            AudioBook newBookCopy = new AudioBook();
            newBookCopy.setAvailable(true);
            newBookCopy.setBook(foundBookInDb.get());
            audioBookRepository.save(newBookCopy);
            return newBookCopy;
        }

        return null;
    }

    @Override
    public boolean deleteAdmin(Integer id) {

        if (adminRepository.findById(id).isPresent()) {
            adminRepository.delete(adminRepository.findById(id).get());
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteLibrarian(Integer id) {
        if (librarianRepository.findById(id).isPresent()) {
            librarianRepository.delete(librarianRepository.findById(id).get());
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteMember(Integer id) {

        var foundMember = memberRepository.findById(id);

        if (foundMember.isPresent()) {
            memberRepository.delete(foundMember.get());
            return true;
        }
        return false;
    }


    @Override
    public boolean hasValidLibraryCard(Member member) {

    	// Make sure the library card exists
        var foundCard = libraryCardRepository.findById(member.getLibraryCard().getId());
        if (foundCard.isPresent()) {
        	
        	// Check expiration date
    		Calendar cardDate = Calendar.getInstance();
    		cardDate.setTime(foundCard.get().getExpirationDate());
    		Calendar today = Calendar.getInstance();

        	return (cardDate.get(Calendar.YEAR) >= today.get(Calendar.YEAR)) &&
        			(cardDate.get(Calendar.DAY_OF_YEAR) >= today.get(Calendar.DAY_OF_YEAR));
        
        } else {
        	// No card
        	return false;
        }

    }



    @Override
    public LegerEntry checkOutBookHardCopy(Integer memberId, Integer bookId) {

        var foundMemberInDb = memberRepository.findById(memberId);
        var foundBookInDb = bookRepository.findById(bookId);

        // Make sure the member ID and book ID are valid
        if (foundMemberInDb.isPresent() && foundBookInDb.isPresent()) {
            Member member = foundMemberInDb.get();
            Book book = foundBookInDb.get();

            // Check that user's library card is active
            if (!hasValidLibraryCard(member)) {
                return null;
            }

            // Check that we have at least one copy of the book, and it is available
            Iterator<HardCopyBook> availableBooks = findAvailableHardCopies(book).iterator();
            if (!availableBooks.hasNext()) {
                return null;
            }

            HardCopyBook bookToBorrow = availableBooks.next();

            // Finally, check out the book by creating a leger entry
            Calendar calendar = Calendar.getInstance();
            java.sql.Date currentDate = new java.sql.Date(calendar.getTime().getTime());
            LegerEntry newEntry = new LegerEntry(member.getId(), book.getId(), currentDate, null);
            legerEntryRepository.save(newEntry);

            // Mark book copy as not available
            bookToBorrow.setAvailable(false);
            bookCopyRepository.save(bookToBorrow);

            return newEntry;
        }

        return null;


    }

    @Override
    public boolean checkOutAudiobook(Integer memberId, Integer bookId) {

        var foundMemberInDb = memberRepository.findById(memberId);
        var foundBookInDb = bookRepository.findById(bookId);

        // Make sure the member ID and book ID are valid
        if (foundMemberInDb.isPresent() && foundBookInDb.isPresent()) {
            Member member = foundMemberInDb.get();
            Book book = foundBookInDb.get();

            // Check that user's library card is active
            if (!hasValidLibraryCard(member)) {
                return false;
            }

            // Check that we have at least one copy of the book, and it is available
            Iterator<AudioBook> availableBooks = findAvailableAudiobooks(book).iterator();
            if (!availableBooks.hasNext()) {
                return false;
            }

            // Take whatever one floats up first that is available
            AudioBook bookToBorrow = availableBooks.next();

            // Finally, check out the book by creating a leger entry
            java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            LegerEntry newEntry = new LegerEntry(member.getId(), book.getId(), currentDate, null);
            legerEntryRepository.save(newEntry);

            // Mark book copy as not available
            bookToBorrow.setAvailable(false);
            bookCopyRepository.save(bookToBorrow);

            return true;
        }

        return false;

    }

    @Override
    public Set<HardCopyBook> findAvailableHardCopies(Book book) {
        return hardCopyBookRepository.findAvailableBooksById(book.getId());
    }

    @Override
    public Set<AudioBook> findAvailableAudiobooks(Book book) {
        return audioBookRepository.findAvailableBooksById(book.getId());
    }

    @Override
    public Set<Object[]> seeCheckedOutBooksAllTime(Integer memberId) {
        return bookCopyRepository.findCheckedOutBooksAllTime(memberId);
    }

    @Override
    public Set<Object[]> seeCheckedOutBooksCurrently(Integer memberId) {
        return bookCopyRepository.findCheckedOutBooksCurrently(memberId);
    }


}
