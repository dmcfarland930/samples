/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dao;

import com.mycompany.dto.DVD;
import com.mycompany.dto.Date;
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

/**
 *
 * @author apprentice
 */
public class DVDDao {

    Date dvdDate = new Date();
    List<DVD> dvdList = new ArrayList();
    int nextId;

    public void DVDDao() {

        dvdList = decode();

        for (DVD myDvd : dvdList) {
            if (myDvd.getId() == nextId) {
                nextId++;
            }

        }
    }

    public DVD create(DVD dvd) {

        dvd.setId(nextId);
        nextId++;
        dvdList.add(dvd);

        encode();

        return dvd;

    }

    public DVD get(String title) {

        for (DVD myDvd : dvdList) {
            if (myDvd.getTitle().equalsIgnoreCase(title)) {
                return myDvd;
            }
        }
        return null;
    }

    public void update(DVD dvd) {

        for (DVD myDvd : dvdList) {
            if (myDvd.getId() == dvd.getId()) {
                dvdList.remove(myDvd);
                dvdList.add(dvd);

            }

        }
        encode();

    }

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

    public void encode() {

        final String TOKEN = "::";
        PrintWriter out = null;

        try {
            out = new PrintWriter(new FileWriter("dvdList.txt"));

            for (DVD myDVD : dvdList) {

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
                out.print(myDVD.getUserNote());
                out.print(TOKEN);
                out.print(dvdDate.getMonth());
                out.print(TOKEN);
                out.print(dvdDate.getDay());
                out.print(TOKEN);
                out.print(dvdDate.getYear());
                out.println();

            }
            out.flush();

        } catch (IOException ex) {

            Logger.getLogger(DVDDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }

    }

    public List decode() {

        Scanner sc = null;
        List<DVD> addressList = new ArrayList();

        try {
            sc = new Scanner(new BufferedReader(new FileReader("dvdList.txt")));

            while (sc.hasNext()) {

                String currentLine = sc.nextLine();
                String[] stringParts = currentLine.split("::");

                DVD myDVD = new DVD();
                Date dvdDating = new Date();

                int id = Integer.parseInt(stringParts[0]);
                myDVD.setId(id);
                myDVD.setTitle(stringParts[1]);
                myDVD.setDirector(stringParts[2]);
                myDVD.setRating(stringParts[3]);
                myDVD.setStudio(stringParts[4]);
                myDVD.setUserNote(stringParts[5]);
                dvdDating.setMonth(stringParts[6]);
                dvdDating.setDay(stringParts[7]);
                dvdDating.setYear(stringParts[8]);

                addressList.add(myDVD);

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(DVDDao.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            sc.close();
        }

        return addressList;
    }
}
