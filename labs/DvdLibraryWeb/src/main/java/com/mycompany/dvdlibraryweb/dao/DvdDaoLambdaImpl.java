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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public class DvdDaoLambdaImpl implements DvdDao {

    List<Dvd> dvdList = new ArrayList();
    NoteDaoImpl noteDao = new NoteDaoImpl();
    private int nextId = 1000;

    public DvdDaoLambdaImpl() {

        dvdList = decode();

    }

    @Override
    public Dvd create(Dvd dvd) {

        dvd.setId(nextId);
        nextId++;
        dvdList.add(dvd);

        encode();

        return dvd;

    }

    @Override
    public List<Dvd> getByTitle(String title) {

        return dvdList
                .stream()
                .filter(a -> a.getTitle().equals(title))
                .collect(Collectors.toList());

    }

    @Override
    public Dvd get(Integer id) {

        return dvdList
                .stream()
                .filter(a -> a.getId() == id)
                .collect(Collectors.toList()).get(0);
    }

    @Override
    public void update(Dvd dvd) {

        List<Dvd> modifiedDvdList = decode();
        dvdList = modifiedDvdList
                .stream()
                .filter(a -> a.getId() != dvd.getId())
                .collect(Collectors.toList());

        dvdList.add(dvd);

        

        encode();
        
    }

    @Override
    public void delete(Dvd dvd) {

        List<Dvd> modifiedDvdList = decode();
        dvdList = modifiedDvdList
                .stream()
                .filter(a -> a.getId() != dvd.getId())
                .collect(Collectors.toList());

        List<Notes> notes = noteDao.getNoteList();
        for(Notes note : notes){
            if(note.getDvdId() == dvd.getId()){
                noteDao.delete(note);
            }
        }
        encode();

    }

    public void encode() {

        final String TOKEN = "::";

        try {
            PrintWriter out = new PrintWriter(new FileWriter("dvdList.txt"));

            dvdList
                    .stream()
                    .forEach((Dvd myDvd) -> {

                        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                        Date date = myDvd.getDvdDate();
                        String dateString = sdf.format(date);
                        out.print(myDvd.getId());
                        out.print(TOKEN);
                        out.print(myDvd.getTitle());
                        out.print(TOKEN);
                        out.print(myDvd.getDirector());
                        out.print(TOKEN);
                        out.print(myDvd.getStudio());
                        out.print(TOKEN);
                        out.print(myDvd.getRating());
                        out.print(TOKEN);
                        out.print(dateString);
                        out.println();

                    });
            out.flush();
            out.close();

        } catch (IOException ex) {

            Logger.getLogger(DvdDaoLambdaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public List<Dvd> decode() {

        int lastId = 0;
        Scanner sc = null;
        List<Dvd> dvds = new ArrayList();

        try {
            sc = new Scanner(new BufferedReader(new FileReader("dvdList.txt")));

            while (sc.hasNext()) {

                String currentLine = sc.nextLine();
                String[] stringParts = currentLine.split("::");

                Dvd myDvd = new Dvd();
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

                int id = Integer.parseInt(stringParts[0]);
                if (id > lastId) {
                    lastId = id;
                }
                
                myDvd.setId(id);
                myDvd.setTitle(stringParts[1]);
                myDvd.setDirector(stringParts[2]);
                myDvd.setStudio(stringParts[3]);
                myDvd.setRating(stringParts[4]);
                myDvd.setDate(stringParts[5]);
                try {
                    Date myDate = sdf.parse(stringParts[5]);
                    myDvd.setDvdDate(myDate);
                } catch (Exception ex) {
                    System.out.println("This format is incorrect.");
                }

                List<Notes> notesList = noteDao.getNoteList();
                List<Notes> notesPerMovie = new ArrayList();
                for (Notes note : notesList) {

                    if (note.getDvdId() == myDvd.getId()) {
                        notesPerMovie.add(note);
                    }

                }
                myDvd.setNoteList(notesPerMovie);
                myDvd.setDvdNoteAmount(notesPerMovie);
                dvds.add(myDvd);

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(DvdDaoLambdaImpl.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            sc.close();
        }

        this.nextId = (lastId + 1);
        return dvds;
    }

    @Override
    public List<Dvd> getByMPAA(String mpaa) {
        return dvdList
                .stream()
                .filter(a -> a.getRating().equalsIgnoreCase(mpaa))
                .collect(Collectors.toList());
    }

    @Override
    public List<Dvd> getByStudio(String studio) {
        return dvdList
                .stream()
                .filter(a -> a.getStudio().equalsIgnoreCase(studio))
                .collect(Collectors.toList());
    }

    @Override
    public List<Dvd> getByDirector(String director) {

        return dvdList
                .stream()
                .filter(a -> a.getDirector().equalsIgnoreCase(director))
                .collect(Collectors.toList());

    }

    @Override
    public List<Dvd> getMoviesAfterDate(int years) {

        List<Dvd> dvds = new ArrayList();
        Calendar cal = Calendar.getInstance();
        int presentYear = cal.get(Calendar.YEAR);

        dvdList
                .stream()
                .forEach((Dvd myDvd) -> {
                    Date dvdDate = myDvd.getDvdDate();
                    cal.setTime(dvdDate);
                    int year = cal.get(Calendar.YEAR);
                    if (year > (presentYear - years)) {
                        dvds.add(myDvd);
                    }
                });

        return dvds;
    }

    @Override
    public Dvd findNewestMovie() {

        Dvd youngestDvd;

        Comparator<Dvd> compAge = (d1, d2) -> d1.getDvdDate().compareTo(d2.getDvdDate());

        youngestDvd = dvdList
                .stream()
                .max(compAge)
                .get();

        return youngestDvd;
    }

    @Override
    public Dvd findOldestMovie() {

        Dvd oldestDvd;

        Comparator<Dvd> compAge = (d1, d2) -> d1.getDvdDate().compareTo(d2.getDvdDate());

        oldestDvd = dvdList
                .stream()
                .min(compAge)
                .get();

        return oldestDvd;
    }

    @Override
    public int findAverageAgeOfMovies() {

        int averAge;
        Integer sum = dvdList
                .stream()
                .map(Dvd::getDvdAge)
                .reduce(0, (a, b) -> a + b);

        averAge = sum / dvdList.size();
        return averAge;
    }

//    public int findAverageAmountOfNotes() {
//
//        int averAge;
//        Integer sum = dvdList
//                .stream()
//                .map(Dvd::getDvdNoteAmount)
//                .reduce(0, (a, b) -> a + b);
//
//        averAge = sum / dvdList.size();
//        return averAge;
//        
//    }

    @Override
    public List<Dvd> list() {
    
        return this.dvdList;
    }
    
    

}
