/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dvdlibraryweb.dto;

import com.mycompany.dvdlibraryweb.dao.DvdDao;

/**
 *
 * @author apprentice
 */
public class Notes {

    String title;
    String note;
    int id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
