/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dvdlibraryweb.dao;

import com.mycompany.dvdlibraryweb.dto.Dvd;
import com.mycompany.dvdlibraryweb.dto.Notes;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public class NoteDao {

    List<Notes> noteList = new ArrayList();
    private int nextId = 1000;
    
    public NoteDao() {
        noteList = decode();

    }

    public Notes create(Notes note) {

        note.setNoteId(nextId);
        noteList.add(note);
        nextId++;
        encode();

        return note;

    }

    public Notes get(int id) {

        return noteList
                .stream()
                .filter(a -> a.getNoteId() == id)
                .collect(Collectors.toList()).get(0);

    }

    public void update(Notes note) {

        List<Notes> modifiedNotesList = decode();
        noteList = modifiedNotesList
                .stream()
                .filter(a -> a.getTitle().equals(note.getTitle()))
                .collect(Collectors.toList());

        noteList.add(note);

        encode();

    }

    public void delete(Notes note) {

        List<Notes> modifiedNotesList = decode();
        noteList = modifiedNotesList
                .stream()
                .filter(a -> a.getNoteId() != note.getNoteId())
                .collect(Collectors.toList());

        encode();

    }

    public void encode() {

        final String TOKEN = "::";

        try {
            PrintWriter out = new PrintWriter(new FileWriter("notes.txt"));

            noteList
                    .stream()
                    .forEach((Notes myNotes) -> {
                        out.print(myNotes.getNoteId());
                        out.print(TOKEN);
                        out.print(myNotes.getDvdId());
                        out.print(TOKEN);
                        out.print(myNotes.getTitle());
                        out.print(TOKEN);
                        out.print(myNotes.getNote());
                        out.println();
                    }
                    );
            out.flush();
            out.close();

        } catch (IOException ex) {

            Logger.getLogger(NoteDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List decode() {

        int lastId = 0;
        Scanner sc = null;
        List<Notes> notes = new ArrayList();

        try {
            sc = new Scanner(new BufferedReader(new FileReader("notes.txt")));

            while (sc.hasNext()) {

                String currentLine = sc.nextLine();
                String[] stringParts = currentLine.split("::");

                Notes myNotes = new Notes();

                int id = Integer.parseInt(stringParts[0]);
                if (id > lastId) {
                    lastId = id;
                }
                myNotes.setNoteId(id);
                int dvdId = Integer.parseInt(stringParts[1]);
                myNotes.setDvdId(dvdId);
                myNotes.setTitle(stringParts[2]);
                myNotes.setNote(stringParts[3]);

                notes.add(myNotes);

            }

        
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NoteDao.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            sc.close();
        }

        
        this.nextId = (lastId + 1);
        return notes;
    }

    public Dvd setNotesToDvd(Dvd dvd) {

        List<Notes> notesList = getNoteList();
        List<Notes> notesPerMovie = new ArrayList();
        for (Notes note : notesList) {

            if (note.getDvdId() == dvd.getId()) {
                notesPerMovie.add(note);
            }

        }
        dvd.setNoteList(notesPerMovie);

        return dvd;

    }

    public List<Notes> getNoteList() {
        return this.noteList;
    }

}
