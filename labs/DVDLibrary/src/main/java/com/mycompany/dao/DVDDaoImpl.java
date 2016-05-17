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

/**
 *
 * @author apprentice
 */
public class DVDDaoImpl implements DVDDao {

    List<DVD> dvdList = new ArrayList();
    private int nextId = 1000;

    public DVDDaoImpl() {

        dvdList = decode();

        for (DVD myDvd : dvdList) {
            if (myDvd.getId() == nextId) {
                nextId++;
            }

        }
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

        for (DVD myDvd : dvdList) {

            String getTitle = myDvd.getTitle();

            if (getTitle.equalsIgnoreCase(title)) {

                return myDvd;

            }
        }
        return null;
    }

    @Override
    public DVD get(int id) {

        for (DVD myDvd : dvdList) {

            int getId = myDvd.getId();

            if (getId == id) {

                return myDvd;

            }
        }
        return null;
    }

    @Override
    public void update(DVD dvd) {

        for (DVD myDvd : dvdList) {
            if (myDvd.getId() == dvd.getId()) {
                dvdList.remove(myDvd);
                dvdList.add(dvd);

            }

        }
        encode();

    }

    @Override
    public void delete(DVD dvd) {

        DVD found = null;

        for (DVD myDvd : dvdList) {

            if (myDvd.getId() == dvd.getId()) {

                found = myDvd;
                break;
            }
        }
        dvdList.remove(found);

        encode();

    }

    @Override
    public void encode() {

        final String TOKEN = "::";
        PrintWriter out = null;

        try {
            out = new PrintWriter(new FileWriter("dvdList.txt"));

            for (DVD myDVD : dvdList) {

                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                Date date = myDVD.getDvdDate();
                String dateString = sdf.format(date);

                out.print(myDVD.getId());
                out.print(TOKEN);
                out.print(myDVD.getTitle());
                out.print(TOKEN);
                out.print(myDVD.getDirector());
                out.print(TOKEN);
                out.print(myDVD.getRating());
                out.print(TOKEN);
                out.print(myDVD.getStudio());
                out.print(TOKEN);
                out.print(dateString);
                out.println();

            }
            out.flush();

        } catch (IOException ex) {

            Logger.getLogger(DVDDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
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
            Logger.getLogger(DVDDaoImpl.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            sc.close();
        }

        return dvds;
    }

    @Override
    public List getByMPAA(String mpaa) {
        List<DVD> dvds = decode();
        List<DVD> foundDvds = new ArrayList();
        for (DVD foundDvd : dvds) {

            if (mpaa.toLowerCase().equalsIgnoreCase(foundDvd.getRating().toLowerCase())) {
                foundDvds.add(foundDvd);
            }

        }
        return foundDvds;
    }

    @Override
    public List getByStudio(String studio) {
        List<DVD> dvds = decode();
        List<DVD> foundDvds = new ArrayList();
        for (DVD foundDvd : dvds) {

            if (studio.toLowerCase().equalsIgnoreCase(foundDvd.getStudio().toLowerCase())) {
                foundDvds.add(foundDvd);
            }

        }
        return foundDvds;
    }

    @Override
    public Map getByDirector(String director) {
        List<DVD> dvds = decode();
        Map<String, List<DVD>> directorMap = new HashMap();
        List<DVD> dvdMpaaList = new ArrayList();
        String mpaa;

        for (DVD foundDvd : dvds) {

            if (director.toLowerCase().equalsIgnoreCase(foundDvd.getDirector().toLowerCase())) {

                mpaa = foundDvd.getRating();

                for (DVD dvd : dvds) {

                    if (mpaa.equalsIgnoreCase(dvd.getRating())) {
                        dvdMpaaList.add(dvd);
                        directorMap.put(director, dvdMpaaList);
                    }
                }
            }

        }
        return directorMap;
    }

    @Override
    public List getMoviesAfterDate(Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
