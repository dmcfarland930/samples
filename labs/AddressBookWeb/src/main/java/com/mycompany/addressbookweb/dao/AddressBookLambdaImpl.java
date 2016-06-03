/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.addressbookweb.dao;

import com.mycompany.addressbookweb.dto.Address;
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
public class AddressBookLambdaImpl implements AddressBookDao{

    List<Address> addresses = new ArrayList();
    private int nextId = 1;

    public AddressBookLambdaImpl() {
        addresses = decode();

    }

    @Override
    public Address create(Address address) {

        address.setId(nextId);
        nextId++;
        addresses.add(address);

        encode();

        return address;

    }

//    @Override
//    public Address get(String lastName) {
//
//        return addresses
//                .stream()
//                .filter(a -> a.getLastName().equals(lastName))
//                .collect(Collectors.toList()).get(0);
//
//    }

    @Override
    public Address get(Integer identifier) {

        return addresses
                .stream()
                .filter(a -> a.getId() == identifier)
                .collect(Collectors.toList()).get(0);
        
    }

    @Override
    public void update(Address address) {

        List<Address> modifiedAddressList = decode();
        addresses = modifiedAddressList
                .stream()
                .filter(a -> a.getId() != address.getId())
                .collect(Collectors.toList());

        addresses.add(address);

        encode();

    }

    @Override
    public void delete(Integer identifier) {

        List<Address> modifiedAddressList = decode();

        addresses = modifiedAddressList
                .stream()
                .filter(a -> identifier != a.getId())
                .collect(Collectors.toList());

        encode();

    }

    public void encode() {

        final String TOKEN = "::";

        try {
            PrintWriter out = new PrintWriter(new FileWriter("addresses.txt"));

            addresses
                    .stream()
                    .forEach((Address myAddress) -> {
                        out.print(myAddress.getId());
                        out.print(TOKEN);
                        out.print(myAddress.getFirstName());
                        out.print(TOKEN);
                        out.print(myAddress.getLastName());
                        out.print(TOKEN);
                        out.print(myAddress.getStreetNumber());
                        out.print(TOKEN);
                        out.print(myAddress.getStreetName());
                        out.print(TOKEN);
                        out.print(myAddress.getCity());
                        out.print(TOKEN);
                        out.print(myAddress.getState());
                        out.print(TOKEN);
                        out.print(myAddress.getZip());
//                        out.print(TOKEN);
//                        out.print(myAddress.getCountry());
//                        out.print(TOKEN);
//                        out.print(myAddress.getSecAdd());
                        out.println();
                    }
                    );
            out.flush();
            out.close();

        } catch (IOException ex) {

            Logger.getLogger(AddressBookLambdaImpl.class.getName()).log(Level.SEVERE, null, ex);
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
                if (id == nextId) {
                    nextId++;
                }
                myAddress.setId(id);
                myAddress.setFirstName(stringParts[1]);
                myAddress.setLastName(stringParts[2]);
                myAddress.setStreetNumber(stringParts[3]);
                myAddress.setStreetName(stringParts[4]);
                myAddress.setCity(stringParts[5]);
                myAddress.setState(stringParts[6]);
                myAddress.setZip(stringParts[7]);
//                myAddress.setCountry(stringParts[7]);
//                myAddress.setSecAdd(stringParts[8]);

                addressList.add(myAddress);

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(AddressBookLambdaImpl.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            sc.close();
        }

        return addressList;
    }

    @Override
    public List<Address> searchByLastName(String lastName) {

        return addresses
                .stream()
                .filter(a -> a.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());

    }

    @Override
    public List<Address>  searchByCity(String city) {
        return addresses
                .stream()
                .filter(a -> a.getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    @Override
    public List<Address>  searchByState(String state) {

        return addresses
                .stream()
                .filter(a -> a.getState().equalsIgnoreCase(state))
                .collect(Collectors.toList());

    }

    @Override
    public List<Address>  searchByZip(String zip) {
        return addresses
                .stream()
                .filter(a -> a.getZip().equalsIgnoreCase(zip))
                .collect(Collectors.toList());
    }

    @Override
    public List<Address> list() {
        return this.addresses;
    }

}
