package edu.northeastern.cs5200.models;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Member extends User {

    private Boolean isUnderThirteen;
    private Boolean isSponsored;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private LibraryCard libraryCard;

    @ManyToOne
    private Member sponsoredBy;

    @OneToMany(mappedBy="sponsoredBy")
    private Set<Member> recipientsOfSponsorship;

    public Member() {
        this.recipientsOfSponsorship = new HashSet<>();
    }

    public Member(Integer id, String firstName, String lastName, String username, String password, String email,
                  Date dateOfBirth, Boolean isUnderThirteen, Boolean isSponsored, Member sponsoredBy,
                  Set<Member> recipientsOfSponsorship) {
        super(id, firstName, lastName, username, password, email, dateOfBirth);
        this.isUnderThirteen = isUnderThirteen;
        this.isSponsored = isSponsored;
        this.sponsoredBy = sponsoredBy;
        this.recipientsOfSponsorship = recipientsOfSponsorship;
    }



    public Boolean getUnderThirteen() {
        return isUnderThirteen;
    }

    public void setUnderThirteen(Boolean underThirteen) {
        isUnderThirteen = underThirteen;
    }

    public Boolean getSponsored() {
        return isSponsored;
    }

    public void setSponsored(Boolean sponsored) {
        isSponsored = sponsored;
    }

    public LibraryCard getLibraryCard() {
        return libraryCard;
    }

    public void setLibraryCard(LibraryCard libraryCard) {
        this.libraryCard = libraryCard;
    }

    public Member getSponsoredBy() {
        return sponsoredBy;
    }

    public void setSponsoredBy(Member sponsoredBy) {
        this.sponsoredBy = sponsoredBy;
    }

    public Set<Member> getRecipientsOfSponsorship() {
        return recipientsOfSponsorship;
    }

    public void setRecipientsOfSponsorship(Set<Member> recipientsOfSponsorship) {
        this.recipientsOfSponsorship = recipientsOfSponsorship;
    }
}
