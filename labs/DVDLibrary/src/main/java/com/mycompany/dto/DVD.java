/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dto;

import java.util.Date;

/**
 *
 * @author apprentice
 */
public class DVD {

    int id;
    String title;
    String rating;
    String director;
    String studio;
    String userNote;
    Date dvdDate = new Date();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String student) {
        this.studio = student;
    }

    public String getUserNote() {
        return userNote;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }

    public Date getDvdDate() {
        return dvdDate;
    }

    public void setDvdDate(Date dvdDate) {
        this.dvdDate = dvdDate;
    }
    
    
}
