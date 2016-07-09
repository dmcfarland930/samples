/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dao;

import com.thesoftwareguild.interfaces.dao.AddressBookDao;
import com.thesoftwareguild.interfaces.dto.Address;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apprentice
 */
public class AddressDaoImpl implements AddressBookDao {

    List<Address> addresses = new ArrayList();
    private int nextId = 1;

    public AddressDaoImpl() {
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
//        for (Address myAddress : addresses) {
//            if (myAddress.getLastName().equalsIgnoreCase(lastName)) {
//                return myAddress;
//            }
//        }
//        return null;
//    }
    @Override
    public Address get(Integer identifier) {

        for (Address myAddress : addresses) {
            if (myAddress.getId() == identifier) {
                return myAddress;
            }
        }
        return null;
    }

    @Override
    public void update(Address address) {

        for (Address myAddress : addresses) {
            if (myAddress.getId() == address.getId()) {
                addresses.remove(myAddress);
                addresses.add(address);
                break;
            }

        }
        encode();

    }

    @Override
    public void delete(Integer identifier) {

        Address found = null;

        for (Address myAddress : addresses) {

            if (Objects.equals(myAddress.getId(), identifier)) {

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
//                out.print(TOKEN);
//                out.print(myAddress.());
//                out.print(TOKEN);
//                out.print(myAddress.getSecAdd());
                out.println();

            }
            out.flush();

        } catch (IOException ex) {

            Logger.getLogger(AddressDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AddressDaoImpl.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            sc.close();
        }

        return addressList;
    }

    @Override
    public List searchByLastName(String lastName) {

        List<Address> addresses = decode();
        List<Address> foundAddresses = new ArrayList();
        for (Address foundAddress : addresses) {

            if (lastName.toLowerCase().equalsIgnoreCase(foundAddress.getLastName().toLowerCase())) {
                foundAddresses.add(foundAddress);
            }

        }
        return foundAddresses;
    }

    @Override
    public List<Address> searchByCity(String city) {
        List<Address> addresses = decode();
        List<Address> foundAddresses = new ArrayList();
        for (Address foundAddress : addresses) {

            if (city.toLowerCase().equalsIgnoreCase(foundAddress.getCity().toLowerCase())) {
                foundAddresses.add(foundAddress);
            }

        }
        return foundAddresses;
    }

    @Override
    public List searchByState(String state) {
        List<Address> addresses = decode();
//        Map<String, List<Address>> stateMap = new HashMap();
        List<Address> cityAddressList = new ArrayList();
        String city;

        for (Address foundAddress : addresses) {

            if (state.toLowerCase().equalsIgnoreCase(foundAddress.getState().toLowerCase())) {

                cityAddressList.add(foundAddress);

//                for (Address address : addresses) {
//
//                    if (city.equalsIgnoreCase(address.getCity())) {
//                        cityAddressList.add(address);
//                        stateMap.put(city, cityAddressList);
//                    }
//                }
            }

        }
        return cityAddressList;
    }

    @Override
    public List<Address> searchByZip(String zip) {
        List<Address> addresses = decode();
        List<Address> foundAddresses = new ArrayList();
        for (Address foundAddress : addresses) {

            if (zip.toLowerCase().equalsIgnoreCase(foundAddress.getZip().toLowerCase())) {
                foundAddresses.add(foundAddress);
            }

        }
        return foundAddresses;
    }

    @Override
    public List<Address> list() {
        return this.addresses;
    }

}
