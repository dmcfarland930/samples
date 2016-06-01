/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dvdlibraryweb.dto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class DVD {

    int id;
    int dvdAge;
    int dvdNoteAmount;
    String title;
    String rating;
    String director;
    String studio;
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
    

}
