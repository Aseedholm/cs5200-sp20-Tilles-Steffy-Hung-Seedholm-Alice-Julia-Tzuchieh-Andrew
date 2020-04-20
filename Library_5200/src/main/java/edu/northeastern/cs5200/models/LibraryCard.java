package edu.northeastern.cs5200.models;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
public class LibraryCard {

    // TODO we could add something like "suspended" if they return stuff late a lot
    // Or make a trigger that activates that once 3x books are returned late

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @MapsId
    private Member user;


    private Timestamp expirationDate;

    public LibraryCard(Integer id, Member member, Timestamp expirationDate) {

        this.id = id;
        this.user = member;
        this.expirationDate = expirationDate;
    }


    public LibraryCard() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Member getUser() {
        return user;
    }

    public void setUser(Member user) {
        this.user = user;
    }

    public Timestamp getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Timestamp expirationDate) {
        this.expirationDate = expirationDate;
    }

}