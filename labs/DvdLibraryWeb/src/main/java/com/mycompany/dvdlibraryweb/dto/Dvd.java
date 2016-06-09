/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dvdlibraryweb.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author apprentice
 */
public class Dvd {

    int id;
    int dvdAge;
    int dvdNoteAmount;
    
    @NotEmpty(message="You must enter a title!")
    String title;
    
    @NotEmpty(message="You must enter a rating!")
    String rating;
    
    @NotEmpty(message="You must enter a director!")
    String director;
    
    @NotEmpty(message="You must enter a studio!")
    String studio;
    
    String date;
    
    String notes;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy", timezone="EST")
    Date dvdDate = new Date();
    
    List <Notes> noteList = new ArrayList();

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

    public Date getDvdDate() {
        return dvdDate;
    }

    public void setDvdDate(Date dvdDate) {
        setDvdAge(dvdDate);
        this.dvdDate = dvdDate;
    }

    public int getDvdAge() {
        return dvdAge;
    }

    public void setDvdAge(Date dvdDate) {

        Calendar cal = Calendar.getInstance();
        int presentYear = cal.get(Calendar.YEAR);
        cal.setTime(dvdDate);
        this.dvdAge = presentYear - cal.get(Calendar.YEAR);

    }

    public List<Notes> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<Notes> noteList) {
        this.noteList = noteList;
        setDvdNoteAmount(noteList);
    }

    public int getDvdNoteAmount() {
        return dvdNoteAmount;
    }

    public void setDvdNoteAmount(List <Notes> noteList) {
        this.dvdNoteAmount = noteList.size();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    

    
}
