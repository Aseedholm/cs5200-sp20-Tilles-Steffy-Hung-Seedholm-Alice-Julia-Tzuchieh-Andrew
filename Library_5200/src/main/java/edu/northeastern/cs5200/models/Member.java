package edu.northeastern.cs5200.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Member extends User {


    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private LibraryCard libraryCard;

    @Column(name="sponsored_by", insertable=false, updatable=false)
    private Integer sponsoredBy;

    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
    @JoinColumn(name="sponsored_by")
    private Set<Member> recipientsOfSponsorship;

    public Member() {
        this.recipientsOfSponsorship = new HashSet<>();
    }

    public Member(Integer id, String firstName, String lastName, String username, String password, String email,
                  Date dateOfBirth, Integer sponsoredBy, Set<Member> recipientsOfSponsorship) {
        super(id, firstName, lastName, username, password, email, dateOfBirth);
        this.sponsoredBy = sponsoredBy;
        this.recipientsOfSponsorship = recipientsOfSponsorship;
    }



    public LibraryCard getLibraryCard() {
        return libraryCard;
    }

    public void setLibraryCard(LibraryCard libraryCard) {
        this.libraryCard = libraryCard;
    }

    public Integer getSponsoredBy() {
        return sponsoredBy;
    }

    public void setSponsor(Member sponsor) {
        this.sponsoredBy = sponsor.getId();
    }

    public void setSponsoredBy(Integer sponsoredBy) {
        this.sponsoredBy = sponsoredBy;
    }

    public Set<Member> getRecipientsOfSponsorship() {
        return recipientsOfSponsorship;
    }

    public void setRecipientsOfSponsorship(Set<Member> recipientsOfSponsorship) {
        this.recipientsOfSponsorship = recipientsOfSponsorship;
    }

    public void addSponsorRecipient(Member recipient) {
        this.recipientsOfSponsorship.add(recipient);
    }

    /**
     * Figures out if the member is under 13 or not.
     * @return True if they're under thirteen, false otherwise.
     */
    public boolean isUnderThirteen() {

        Calendar today = Calendar.getInstance();
        Calendar minDOB = today;
        minDOB.add(Calendar.YEAR, -13);

        // Just making null DOB be an adult, for convenience.
        // TODO maybe adjust
        if (this.getDateOfBirth() == null) {
            return false;
        }


        // If it is  less than zero, than this date is before the minimum DOB
        if (this.getDateOfBirth().compareTo((minDOB.getTime())) < 0) {

            // Therefore, they are at least 13 years old
            return false;
        }

        // If they  were born after the minimum DOB, they are indeed 13 years old
        return true;

    }


    @Override
    public String toString() {
        return "Member{" +
                "id=" + getId() +
                ", username=" + getUsername() +
                ", libraryCard=" + libraryCard +
                ", sponsoredBy=" + sponsoredBy +
                ", recipientsOfSponsorship=" + recipientsOfSponsorship +
                '}';
    }




}