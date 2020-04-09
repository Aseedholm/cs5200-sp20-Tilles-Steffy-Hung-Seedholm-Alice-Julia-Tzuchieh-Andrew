package edu.northeastern.cs5200.models;


import java.sql.Date;
import java.util.Set;

public class Author {

    private Integer author_id;
    private String firstName;
    private String lastName;
    private String penName;
    private Date dateOfBirth;
    private Date dateOfDeath;
    private Set<Book> booksWritten;


    public Author() {

    }


    public Author(Integer id, String firstName, String lastName, String penName,
                  Date dateOfBirth, Date dateOfDeath, Set<Book> booksWritten) {
        this.author_id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.penName = penName;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
        this.booksWritten = booksWritten;
    }


    public Integer getId() {
        return author_id;
    }

    public void setId(Integer id) {
        this.author_id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPenName() {
        return penName;
    }

    public void setPenName(String penName) {
        this.penName = penName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(Date dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public Set<Book> getBooksWritten() {
        return booksWritten;
    }

    public void setBooksWritten(Set<Book> booksWritten) {
        this.booksWritten = booksWritten;
    }
}
