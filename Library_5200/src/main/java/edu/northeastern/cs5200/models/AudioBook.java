package edu.northeastern.cs5200.models;

import javax.persistence.Entity;
import java.sql.Date;
import java.util.Set;

@Entity(name="audiobooks")
public class AudioBook extends Book {

    private String narratedBy;
    private Integer numMinutes;
    private Double fileSizeMb;
    private String fileType;

    //TODO: figure this one out - enum maybe? is it worth it
    // private Set<String> languagesAvailable;


    public AudioBook(Integer book_id, String title, Author author, Date yearPublished,
                     Genre genre, String ISBN, String narratedBy, Integer numMinutes, Double fileSizeMb,
                     String fileType){
        super(book_id, title, author, yearPublished, genre, ISBN);
            this.narratedBy = narratedBy;
            this.numMinutes = numMinutes;
            this.fileSizeMb = fileSizeMb;
            this.fileType = fileType;
    }

    public String getNarratedBy() {
        return narratedBy;
    }

    public void setNarratedBy(String narratedBy) {
        this.narratedBy = narratedBy;
    }

    public Integer getNumMinutes() {
        return numMinutes;
    }

    public void setNumMinutes(Integer numMinutes) {
        this.numMinutes = numMinutes;
    }

    public Double getFileSizeMb() {
        return fileSizeMb;
    }

    public void setFileSizeMb(Double fileSizeMb) {
        this.fileSizeMb = fileSizeMb;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
