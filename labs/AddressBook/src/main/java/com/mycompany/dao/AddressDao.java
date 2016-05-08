/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dao;

import com.mycompany.dto.Address;
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
public class AddressDao {

    List<Address> addresses = new ArrayList();
    private int nextId = 1;

    public AddressDao() {
        addresses = decode();

        for (Address myAddress : addresses) {
            if (myAddress.getIdentifier() == nextId) {
                nextId++;
            }

        }
    }

    public Address create(Address address) {

        address.setIdentifier(nextId);
        nextId++;
        addresses.add(address);

        encode();

        return address;

    }

    public Address get(String lastName) {

        for (Address myAddress : addresses) {
            if (myAddress.getLastName().equalsIgnoreCase(lastName)) {
                return myAddress;
            }
        }
        return null;
    }

    public Address get(Integer identifier) {

        for (Address myAddress : addresses) {
            if (myAddress.getIdentifier() == identifier) {
                return myAddress;
            }
        }
        return null;
    }

    public void update(Address address) {

        for (Address myAddress : addresses) {
            if (myAddress.getIdentifier() == address.getIdentifier()) {
                addresses.remove(myAddress);
                addresses.add(address);

            }

        }
        encode();

    }

    public void delete(Address address) {

        Address found = null;

        for (Address myAddress : addresses) {

            if (myAddress.getIdentifier() == address.getIdentifier()) {

                found = myAddress;
                break;
            }
        }
        addresses.remove(found);

        encode();

    }

    public void encode() {

        final String TOKEN = "::";
        PrintWriter out = null;

        try {
            out = new PrintWriter(new FileWriter("addresses.txt"));

            for (Address myAddress : addresses) {

                out.print(myAddress.getIdentifier());
                out.print(TOKEN);
                out.print(myAddress.getFirstName());
                out.print(TOKEN);
                out.print(myAddress.getLastName());
                out.print(TOKEN);
                out.print(myAddress.getStreetAddress());
                out.print(TOKEN);
                out.print(myAddress.getCity());
                out.print(TOKEN);
                out.print(myAddress.getState());
                out.print(TOKEN);
                out.print(myAddress.getZip());
                out.print(TOKEN);
                out.print(myAddress.getCountry());
                out.print(TOKEN);
                out.print(myAddress.getSecAdd());
                out.println();

            }
            out.flush();

        } catch (IOException ex) {

            Logger.getLogger(AddressDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }

    }

    public List decode() {

        Scanner sc = null;
        List<Address> addressList = new ArrayList();

        try {
            sc = new Scanner(new BufferedReader(new FileReader("addresses.txt")));

            while (sc.hasNext()) {

                String currentLine = sc.nextLine();
                String[] stringParts = currentLine.split("::");

                Address myAddress = new Address();

                int id = Integer.parseInt(stringParts[0]);
                myAddress.setIdentifier(id);
                myAddress.setFirstName(stringParts[1]);
                myAddress.setLastName(stringParts[2]);
                myAddress.setStreetAddress(stringParts[3]);
                myAddress.setCity(stringParts[4]);
                myAddress.setState(stringParts[5]);
                myAddress.setCountry(stringParts[6]);
                myAddress.setZip(stringParts[7]);
                myAddress.setSecAdd(stringParts[8]);

                addressList.add(myAddress);

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(AddressDao.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            sc.close();
        }

        return addressList;
    }
}
