package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.models.*;
import edu.northeastern.cs5200.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
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
    public Member findMemberById(int id) {

        if (!memberRepository.findById(id).isPresent()) {
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
    	Optional<Member> member = memberRepository.findById(memberId);
        Optional<LibraryCard> card = libraryCardRepository.findById(member.get().getLibraryCard().getId());
        if (card == null) {
            return null;
        }
        return (LibraryCard)card.get();
    }
    
    @Override
    public Member findMemberByUsername(String username) {

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

    	Iterable<Member> members = memberRepository.findAll();

        for (Member m : members) {
            if (m.getUsername().equals(memberUsername)) {
            	return findLibraryCardByMemberId(m.getId());
            }
        }

        return null;
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
            Member sponsor = memberRepository.findById(member.getSponsoredBy()).get();

            // Make sure the sponsor exists in the DB
            if (sponsor != null) {

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
        if (memberRepository.findById(id).isPresent()) {
            memberRepository.delete(memberRepository.findById(id).get());
            return true;
        }
        return false;
    }


    @Override
    public boolean hasValidLibraryCard(Member member) {
        Calendar calendar = Calendar.getInstance();
        java.sql.Date currentDate = new java.sql.Date(calendar.getTime().getTime());

        if (memberRepository.findById(member.getId()).isPresent()) {
            Member foundMember = memberRepository.findById(member.getId()).get();
            Date expirationDate = foundMember.getLibraryCard().getExpirationDate();

            return (currentDate.compareTo(expirationDate) < 0);
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

        // Mark as not available
        bookToBorrow.setAvailable(false);
        bookCopyRepository.save(bookToBorrow);

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
            if (c.getBook().getId() == book.getId() && c.getAvailable()) {


                return c;

            }
        }

        return null;

    }




}
