package edu.northeastern.cs5200.dataloader;

import edu.northeastern.cs5200.daos.LibraryDao;
import edu.northeastern.cs5200.models.Admin;
import edu.northeastern.cs5200.models.Librarian;
import edu.northeastern.cs5200.models.LibraryMember;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SeedDatabase {

    LibraryDao libraryDao;

    public SeedDatabase(LibraryDao libraryDao){
        this.libraryDao = libraryDao;
    }


    private Long stringDateToLong(String stringDate) throws ParseException {
        try {
            DateFormat formatter;
            java.util.Date date;
            formatter = new SimpleDateFormat("dd-MMM-yy");
            date = formatter.parse(stringDate);
            return date.getTime();
        }
        catch (ParseException e){
            stringDate = "01-January-1900";
            DateFormat formatter;
            java.util.Date date;
            formatter = new SimpleDateFormat("dd-MMM-yy");
            date = formatter.parse(stringDate);
            return date.getTime();
        }
    }

    public void createUsers() throws ParseException {

        LibraryMember newMember = new LibraryMember();
        newMember.setDateOfBirth(new Timestamp(stringDateToLong("01-April-1995")));
        newMember.setFirstName("Navya");
        newMember.setLastName("Reddy");
        newMember.setEmail("reddy.na@husky.neu.edu");
        newMember.setUsername("navya12");
        newMember.setPassword("1234");
        libraryDao.createMember(newMember);

        LibraryMember newMember2 = new LibraryMember();
        newMember2.setDateOfBirth(new Timestamp(stringDateToLong("26-May-1985")));
        newMember2.setFirstName("Nesara");
        newMember2.setLastName("Mahav");
        newMember2.setEmail("madhav.n@husky.neu.edu");
        newMember2.setUsername("nesara_loves_to_code");
        newMember2.setPassword("1234");
        libraryDao.createMember(newMember2);

        LibraryMember childMember = new LibraryMember();
        childMember.setDateOfBirth(new Timestamp(stringDateToLong("26-May-2013")));
        childMember.setFirstName("Junior");
        childMember.setLastName("McGee");
        childMember.setEmail("cutiepie2013@hotmail.com");
        childMember.setUsername("ilikecookies123");
        childMember.setPassword("1234");
        childMember.setSponsor(newMember2);
        libraryDao.createMember(childMember);


        Admin jose = new Admin();
        jose.setDateOfBirth(new Timestamp(stringDateToLong("09-October-1980")));
        jose.setFirstName("Jose");
        jose.setLastName("Annunziato");
        jose.setEmail("j.annunziato@northeastern.edu");
        jose.setUsername("TheProfessor");
        jose.setPassword("1234");
        libraryDao.createAdmin(jose);

        Admin siddhesh = new Admin();
        siddhesh.setDateOfBirth(new Timestamp(stringDateToLong("09-October-1980")));
        siddhesh.setFirstName("Siddhesh");
        siddhesh.setLastName("Latkar");
        siddhesh.setEmail("latkar.s@husky.neu.edu");
        siddhesh.setUsername("857IlovetoTA");
        siddhesh.setPassword("1234");
        libraryDao.createAdmin(siddhesh);

        Librarian sameer = new Librarian();
        sameer.setDateOfBirth(new Timestamp(stringDateToLong("16-December-1990")));
        sameer.setFirstName("Sameer");
        sameer.setLastName("Desai");
        sameer.setEmail("desai.sam@husky.neu.edu");
        sameer.setUsername("424databaseLuvr");
        sameer.setPassword("1234");
        libraryDao.createLibrarian(sameer);

        Librarian sanju = new Librarian();
        sanju.setDateOfBirth(new Timestamp(stringDateToLong("22-February-1992")));
        sanju.setFirstName("Sanju");
        sanju.setLastName("Vasisht");
        sanju.setEmail("malavallisatheesh.s@husky.neu.edu");
        sanju.setUsername("mongoose123");
        sanju.setPassword("1234");
        libraryDao.createLibrarian(sanju);

    }

}
