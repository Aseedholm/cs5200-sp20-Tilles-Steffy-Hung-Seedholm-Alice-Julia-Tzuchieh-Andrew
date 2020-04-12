package edu.northeastern.cs5200.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.sql.Date;

@Entity
public class LegerEntry {


    @EmbeddedId
    private LegerId id;

    private Date dateBorrowed;
    private Date dateReturned;

    public LegerEntry(){

    }


    public LegerEntry(LegerId id, Date dateBorrowed, Date dateReturned) {
        this.id = id;
        this.dateBorrowed = dateBorrowed;
        this.dateReturned = dateReturned;
    }

    public LegerEntry(Integer memberId, Integer bookCopyId, Date dateBorrowed, Date dateReturned) {
        LegerId newId = new LegerId(memberId, bookCopyId);
        this.id = newId;
        this.dateBorrowed = dateBorrowed;
        this.dateReturned = dateReturned;
    }

    public LegerId getId() {
        return id;
    }

    public void setId(LegerId id) {
        this.id = id;
    }

    public Date getDateBorrowed() {
        return dateBorrowed;
    }

    public void setDateBorrowed(Date dateBorrowed) {
        this.dateBorrowed = dateBorrowed;
    }

    public Date getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(Date dateReturned) {
        this.dateReturned = dateReturned;
    }
}
