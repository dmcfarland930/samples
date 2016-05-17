/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dao;

import com.mycompany.dto.DVD;
import com.mycompany.dto.Notes;
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
public class NoteDao{

    List<Notes> noteList = new ArrayList();

    public NoteDao() {
        noteList = decode();

    }

    public Notes create(Notes note) {

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

    public void delete(Notes note) {

        List<Notes> modifiedNotesList = decode();
        noteList = modifiedNotesList
                .stream()
                .filter(a -> a.getTitle().equals(note.getTitle()))
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

                myNotes.setTitle(stringParts[0]);
                myNotes.setNote(stringParts[1]);

                addressList.add(myNotes);

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(NoteDao.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            sc.close();
        }

        return addressList;
    }


    
}
