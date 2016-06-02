/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dvdlibraryweb.dao;

import com.mycompany.dvdlibraryweb.dto.DVD;
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

    public NoteDao() {
        noteList = decode();

    }

    public Notes create(Notes note, int id) {

        note.setId(id);
        noteList.add(note);

        encode();

        return note;

    }

    public Notes get(String title) {

        return noteList
                .stream()
                .filter(a -> a.getTitle().equals(title))
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

    public void delete(DVD dvd) {

        List<Notes> modifiedNotesList = decode();
        noteList = modifiedNotesList
                .stream()
                .filter(a -> a.getTitle().equals(dvd.getTitle()))
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
                        out.print(myNotes.getId());
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

        Scanner sc = null;
        List<Notes> addressList = new ArrayList();

        try {
            sc = new Scanner(new BufferedReader(new FileReader("notes.txt")));

            while (sc.hasNext()) {

                String currentLine = sc.nextLine();
                String[] stringParts = currentLine.split("::");

                Notes myNotes = new Notes();

                int id = Integer.parseInt(stringParts[0]);
                myNotes.setId(id);
                myNotes.setTitle(stringParts[1]);
                myNotes.setNote(stringParts[2]);

                addressList.add(myNotes);

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(NoteDao.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            sc.close();
        }

        return addressList;
    }

    public DVD setNotesToDVD(DVD dvd) {

        List<Notes> notesList = getNoteList();
        List<Notes> notesPerMovie = new ArrayList();
        for (Notes note : notesList) {

            if (note.getId() == dvd.getId()) {
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
