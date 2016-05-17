/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dao;

import com.mycompany.dto.DVD;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public class DVDDaoLambdaImpl implements DVDDao {

    List<DVD> dvdList = new ArrayList();
    private int nextId = 1000;

    public DVDDaoLambdaImpl() {

        dvdList = decode();

    }

    @Override
    public DVD create(DVD dvd, Date date) {

        dvd.setId(nextId);
        dvd.setDvdDate(date);
        nextId++;
        dvdList.add(dvd);

        encode();

        return dvd;

    }

    @Override
    public DVD get(String title) {

        return dvdList
                .stream()
                .filter(a -> a.getTitle().equals(title))
                .collect(Collectors.toList()).get(0);

    }

    @Override
    public DVD get(int id) {

        return dvdList
                .stream()
                .filter(a -> a.getId() == id)
                .collect(Collectors.toList()).get(0);
    }

    @Override
    public void update(DVD dvd) {

        List<DVD> modifiedDvdList = decode();
        dvdList = modifiedDvdList
                .stream()
                .filter(a -> a.getId() != dvd.getId())
                .collect(Collectors.toList());

        dvdList.add(dvd);

        encode();

    }

    @Override
    public void delete(DVD dvd) {

        List<DVD> modifiedDvdList = decode();
        dvdList = modifiedDvdList
                .stream()
                .filter(a -> a.getId() != dvd.getId())
                .collect(Collectors.toList());

        encode();

    }

    @Override
    public void encode() {

        final String TOKEN = "::";

        try {
            PrintWriter out = new PrintWriter(new FileWriter("dvdList.txt"));

            dvdList
                    .stream()
                    .forEach((DVD myDVD) -> {

                        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                        Date date = myDVD.getDvdDate();
                        String dateString = sdf.format(date);

                        out.print(myDVD.getId());
                        out.print(TOKEN);
                        out.print(myDVD.getTitle());
                        out.print(TOKEN);
                        out.print(myDVD.getDirector());
                        out.print(TOKEN);
                        out.print(myDVD.getStudio());
                        out.print(TOKEN);
                        out.print(myDVD.getRating());
                        out.print(TOKEN);
                        out.print(dateString);
                        out.println();

                    });
            out.flush();
            out.close();

        } catch (IOException ex) {

            Logger.getLogger(DVDDaoLambdaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List decode() {

        Scanner sc = null;
        List<DVD> dvds = new ArrayList();

        try {
            sc = new Scanner(new BufferedReader(new FileReader("dvdList.txt")));

            while (sc.hasNext()) {

                String currentLine = sc.nextLine();
                String[] stringParts = currentLine.split("::");

                DVD myDVD = new DVD();
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

                int id = Integer.parseInt(stringParts[0]);
                myDVD.setId(id);
                myDVD.setTitle(stringParts[1]);
                myDVD.setDirector(stringParts[2]);
                myDVD.setRating(stringParts[3]);
                myDVD.setStudio(stringParts[4]);
                try {
                    Date myDate = sdf.parse(stringParts[5]);
                    myDVD.setDvdDate(myDate);
                } catch (Exception ex) {
                    System.out.println("This format is incorrect.");
                }

                dvds.add(myDVD);

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(DVDDaoLambdaImpl.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            sc.close();
        }

        return dvds;
    }

    @Override
    public List getByMPAA(String mpaa) {
        return dvdList
                .stream()
                .filter(a -> a.getRating().equalsIgnoreCase(mpaa))
                .collect(Collectors.toList());
    }

    @Override
    public List getByStudio(String studio) {
        return dvdList
                .stream()
                .filter(a -> a.getStudio().equalsIgnoreCase(studio))
                .collect(Collectors.toList());
    }

    @Override
    public Map getByDirector(String director) {
        Map<String, List<DVD>> directorMap = new HashMap();
        return directorMap
                = dvdList
                .stream()
                .filter(a -> a.getDirector().equalsIgnoreCase(director))
                .collect(Collectors.groupingBy(a -> a.getRating()));
    }

    @Override
    public List getMoviesAfterDate(Date date) {
        
        
        
    }
}
