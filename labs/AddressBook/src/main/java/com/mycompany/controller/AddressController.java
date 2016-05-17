/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.controller;

import com.mycompany.dao.AddressBookLambdaImpl;
import com.mycompany.dao.AddressDao;
import com.mycompany.dao.AddressDaoImpl;
import com.mycompany.dto.Address;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author apprentice
 */
public class AddressController {

    ConsoleIO console = new ConsoleIO();
    private AddressDao addressDao = new AddressBookLambdaImpl();

    List<Address> addressList = new ArrayList();

    public void runApp() {

        boolean runAgain = true;

        while (runAgain == true) {

            showMenu();

            String selection = console.getString(">");

            switch (selection) {

                case "1":
                    //add address
                    addAddress();
                    break;
                case "2":
                    //remove address
                    removeAddress();
                    break;
                case "3":
                    //find address
                    findAddress();
                    break;
                case "4":
                    //view address count
                    countAdds();
                    break;
                case "5":
                    //view all addresses
                    viewAll();
                    break;
                case "6":
                    //update address
                    editAddress();
                    break;
                case "quit":
                case "q":
                    //quit app
                    runAgain = false;
                    break;
                default:
                    //error message
                    console.readString("That is not an option!");
            }
        }
        console.readString("Good bye!");
        addressDao.encode();
    }

    public void addAddress() {

        boolean addAgain = true;
        boolean valid = false;

        console.readString("\n");
        console.readString("+----------------------------+");
        console.readString("| Add Address...             |");
        console.readString("+----------------------------+");
        while (addAgain == true) {
            Address newAddress = new Address();
            String firstName = console.checkEmptyString("Enter the first name of your\ncontact:"
                    + "(Enter 1 to cancel)", "You must fill this field.");
            if (firstName.equals("1")) {
                return;
            } else {
                String lastName = console.checkEmptyString("Enter the last name of your\ncontact:", "You must fill this field.");

                String stAddress = console.checkEmptyString("Enter the street address:", "You must fill this field.");
                String city = console.checkEmptyString("Enter the city:", "You must fill this field.");
                while (!valid) {
                    String state = console.checkEmptyString("Enter the state by its abbreviation:", "You must fill this field.");
                    if (state.length() != 2) {
                        console.readString("That is an invalid abbreviation.");
                    } else {
                        valid = true;
                    }

                    String zip = console.checkEmptyString("Enter the zip code:", "You must fill this field.");
                    String country = console.checkEmptyString("Enter the country:", "You must fill this field.");
                    String secondAdd = console.checkEmptyString("Enter your secondary address:"
                            + "\n(P.O. Box, Apt #, Suite #)\nEnter 'none' to leave blank.", "You must fill this field.");

                    newAddress.setFirstName(firstName);
                    newAddress.setLastName(lastName);
                    newAddress.setStreetAddress(stAddress);
                    newAddress.setCity(city);
                    newAddress.setState(state);
                    newAddress.setZip(zip);
                    newAddress.setCountry(country);
                    newAddress.setSecAdd(secondAdd);

                    addressDao.create(newAddress);

                    console.readString("\nAddress added to address book.");
                    addAgain = confirmAgain("\nAdd another address? [yes/no]\n>");
                }
            }
        }
    }

    public void removeAddress() {

        boolean confirm;
        boolean found = false;
        boolean removeAgain = true;

        console.readString("\n");
        console.readString("+----------------------------+");
        console.readString("| Remove Address...          |");
        console.readString("+----------------------------+");

        while (removeAgain == true) {
            String firstName = console.getString("To delete a contact, enter\ntheir first name:"
                    + "(Enter 1 to cancel)\n>");
            if (firstName.equals("1")) {
                return;
            } else {
                String lastName = console.getString("Now enter their last name:\n>");
                String stAddress = console.getString("Now enter their street address:\n>");

                List<Address> addresses = addressDao.decode();

                for (Address delAddress : addresses) {

                    if (firstName.equalsIgnoreCase(delAddress.getFirstName()) && lastName.equalsIgnoreCase(delAddress.getLastName()) && stAddress.equalsIgnoreCase(delAddress.getStreetAddress())) {

                        showAddress(delAddress);
                        found = true;

                        confirm = console.yesCheck("\nDelete this address? [yes/no]\n>", "Enter [yes/no] to proceed.\n>");

                        if (confirm == true) {
                            addressDao.delete(delAddress);
                            console.readString("\nADDRESS DELETED\n");

                            removeAgain = confirmAgain("Delete another address? [yes/no]\n>");
                            break;
                        }
                    } else {
                        found = false;
                    }

                }
                if (found == false) {

                    console.readString("\nAddress not found!\n");

                    break;

                }
            }
        }
    }

    public void findAddress() {
        showFindMenu();
        findContactSwitch();
    }

    public void findAddressByLastName() {

        boolean findAgain = true;

        console.readString("\n");
        console.readString("+----------------------------+");
        console.readString("| Find Address...            |");
        console.readString("+----------------------------+");
        while (findAgain == true) {
            String lastName = console.getString("To find a contact, enter their last name:"
                    + "(Enter 1 to cancel)\n>");
            if (lastName.equals("1")) {
                return;
            } else {

                List<Address> foundAddresses;
                foundAddresses = addressDao.getByLastName(lastName);
                int addsFound = foundAddresses.size();

                if (foundAddresses.isEmpty()) {

                    console.readString("No contacts were found with that last name!");

                } else {
                    console.readString("Contacts Found: " + addsFound);

                    foundAddresses
                            .stream()
                            .forEach(e -> showAddress(e));

                    findAgain = confirmAgain("Search again? [yes/no]\n>");
                }
            }
        }
    }

    public void findAddressByCity() {

        boolean findAgain = true;

        console.readString("\n");
        console.readString("+----------------------------+");
        console.readString("| Find Address...            |");
        console.readString("+----------------------------+");
        while (findAgain == true) {
            String city = console.getString("To find a contact, enter their city:"
                    + "(Enter 1 to cancel)\n>");
            if (city.equals("1")) {
                return;
            } else {

                List<Address> foundAddresses;
                foundAddresses = addressDao.getByCity(city);
                int addsFound = foundAddresses.size();

                if (foundAddresses.isEmpty()) {

                    console.readString("No contacts were found in that city!");

                } else {
                    String lower = city.toLowerCase();
                    String capCity = lower.substring(0, 1).toUpperCase() + lower.substring(1);
                    console.readString("Contacts Found in " + capCity + ": " + addsFound);

                    foundAddresses
                            .stream()
                            .forEach(e -> showAddress(e));

                    findAgain = confirmAgain("Search again? [yes/no]\n>");
                }
            }
        }
    }

    public void findAddressByZip() {

        boolean findAgain = true;

        console.readString("\n");
        console.readString("+----------------------------+");
        console.readString("| Find Address...            |");
        console.readString("+----------------------------+");
        while (findAgain == true) {
            String zip = console.getString("To find a contact, enter their zip code:"
                    + "(Enter 1 to cancel)\n>");
            if (zip.equals("1")) {
                return;
            } else {

                List<Address> foundAddresses;
                foundAddresses = addressDao.getByZip(zip);
                int addsFound = foundAddresses.size();

                if (foundAddresses.isEmpty()) {

                    console.readString("No contacts were found by that zip code!");

                } else {
                    console.readString("Contacts Found : " + addsFound);

                    foundAddresses
                            .stream()
                            .forEach(e -> showAddress(e));

                    findAgain = confirmAgain("Search again? [yes/no]\n>");
                }
            }
        }
    }

    public void findAddressByState() {

        boolean findAgain = true;
        boolean valid = false;
        String state = "";

        console.readString("\n");
        console.readString("+----------------------------+");
        console.readString("| Find Address...            |");
        console.readString("+----------------------------+");
        while (findAgain == true) {
            while (!valid) {
                state = console.getString("To find a contact, enter their state by it's abbreviation:"
                        + "(Enter 1 to cancel)\n>");
                if (state.length() != 2) {
                    console.readString("That is an invalid abbreviation.");
                } else {
                    valid = true;
                }

            }

            if (state.equals("1")) {
                return;
            } else {

                Map<String, List<Address>> cityMap;
                cityMap = addressDao.getByState(state);

                if (cityMap.isEmpty()) {

                    console.readString("No contacts were found by that state!");
                    break;

                } else {

                    console.readString("State: " + state.toUpperCase());

                    Set<String> cities = cityMap.keySet();
                    for (String keys : cities) {
                        List<Address> addresses = cityMap.get(keys);
                        console.readString("City: " + keys);
                        int addsFound = addresses.size();
                        console.readString("Contacts Found in " + keys + ": " + addsFound);
                        for (Address address : addresses) {
                            showAddress(address);
                        }
                    }
                }
                findAgain = confirmAgain("Search again? [yes/no]\n>");
            }
        }
    }

    public void countAdds() {

        boolean valid = false;
        console.readString("\n");
        console.readString("+----------------------------+");
        console.readString("| Count Contacts...          |");
        console.readString("+----------------------------+");

        List<Address> listOfAddresses = addressDao.decode();

        int listSize = listOfAddresses.size();

        console.readString("There are " + listSize + " contacts in\nyour address book.");
        while (!valid) {
            String goBack = console.getString("\nEnter 1 to go back.\n>");
            if (goBack.equals("1")) {
                break;
            }
        }

    }

    public void viewAll() {

        List<Address> addresses = addressDao.decode();
        console.readString("\n");
        console.readString("+----------------------------+");
        console.readString("| View All Contacts...       |");
        console.readString("+----------------------------+");

        for (Address addressSaved : addresses) {

            showAddress(addressSaved);
        }
    }

    public void editAddress() {

        boolean editAgain = true;
        boolean valid = false;
        String fLName = "";
        int addressId;

        while (editAgain == true) {

            String lastName = console.getString("To edit a contact, enter their last name:"
                    + "(Enter 1 to cancel)\n>");
            if (lastName.equals("1")) {

                editAgain = false;

            } else {

                List<Address> addresses = addressDao.decode();
                List<Address> foundAddresses = new ArrayList();
                for (Address foundAddress : addresses) {

                    foundAddress = addressDao.get(lastName);
                    try {
                        fLName = foundAddress.getLastName();
                    } catch (Exception ex) {

                    }
                    if (fLName.toLowerCase().equalsIgnoreCase(lastName.toLowerCase())) {
                        foundAddresses.add(foundAddress);
                    }
                }

                int addsFound = foundAddresses.size();
                if (foundAddresses.isEmpty()) {
                    console.readString("No contacts were found with that last name!");
                } else {
                    console.readString("Contacts Found: " + addsFound);
                    for (Address addressInList : foundAddresses) {

                        showAddress(addressInList);

                        addressId = console.getInteger("Enter the Address ID for the address you wish to change:\n>", "That is an invalid entry!");

                        for (Address addWithId : foundAddresses) {

                            if (addWithId.getIdentifier() == addressId) {

                                addWithId = addressDao.get(addressId);
                                showEditMenu();
                                editSwitch(addWithId);
                            }

                        }
                    }

                }

                editAgain = confirmAgain("Edit another address? [yes/no]\n>");

            }
        }
    }

    public void showMenu() {

        console.readString("+----------------------------+");
        console.readString("| Welcome! Choose an option! |");
        console.readString("+----------------------------+");
        console.readString("|   1)Add Address            |");
        console.readString("|   2)Delete Address         |");
        console.readString("|   3)Find Address           |");
        console.readString("|   4)View Address Count     |");
        console.readString("|   5)View All Addresses     |");
        console.readString("|   6)Update Addresses       |");
        console.readString("|  ------------------------  |");
        console.readString("|                    [quit]  |");
        console.readString("+----------------------------+");

    }

    public void showFindMenu() {
        console.readString("+----------------------------+");
        console.readString("| Find your contact by...    |");
        console.readString("+----------------------------+");
        console.readString("|   1)Last Name              |");
        console.readString("|   2)City                   |");
        console.readString("|   3)State                  |");
        console.readString("|   4)Zip                    |");
        console.readString("|  ------------------------  |");
        console.readString("|                    [quit]  |");
        console.readString("+----------------------------+");
    }

    public void showEditMenu() {

        console.readString("+----------------------------+");
        console.readString("| Edit your address!         |");
        console.readString("+----------------------------+");
        console.readString("|   1)Name                   |");
        console.readString("|   2)Street Address         |");
        console.readString("|   3)City                   |");
        console.readString("|   4)State                  |");
        console.readString("|   5)Zip                    |");
        console.readString("|   6)Country                |");
        console.readString("|   7)Secondary Address      |");
        console.readString("|  ------------------------  |");
        console.readString("|                    [quit]  |");
        console.readString("+----------------------------+");
    }

    public void showAddress(Address address) {

        String fSecAdd = address.getSecAdd();

        if (fSecAdd.equalsIgnoreCase("none")) {
            fSecAdd = "";
        }
        console.readString("+----------------------------+");
        console.readString("Address ID: " + address.getIdentifier());
        console.readString("\n" + address.getFirstName() + " " + address.getLastName() + "\n"
                + address.getStreetAddress() + "\n" + address.getCity() + ", " + address.getState() + ", " + address.getZip() + "\n"
                + address.getCountry() + " " + fSecAdd + "\n");
        console.readString("+----------------------------+");
    }


    public boolean confirmAgain(String question) {

        boolean again;
        boolean confirm = console.yesCheck(question, "Enter [yes/no] to proceed.");
        again = confirm == true;

        return again;
    }

    public void findContactSwitch() {

        boolean valid = false;
        while (!valid) {
            String selection = console.getString(">");
            switch (selection) {

                case "1":
                    findAddressByLastName();
                    valid = true;
                    break;
                case "2":
                    findAddressByCity();
                    valid = true;
                    break;
                case "3":
                    findAddressByState();
                    valid = true;
                    break;
                case "4":
                    findAddressByZip();
                    valid = true;
                    break;
                case "quit":
                    valid = true;
                case "q":
                    break;
                default:
                    console.readString("That is an invalid entry!");
            }

        }

    }

    public void editSwitch(Address address) {

        boolean valid = false;

        while (!valid) {
            String selection = console.getString("What field would you like to change?\n>");
            switch (selection) {

                case "1":
                    //change name
                    String newFirstName = console.checkEmptyString("Enter the first name of your\ncontact:", "You must fill this field.");
                    String newLastName = console.checkEmptyString("Enter the last name of your\ncontact:", "You must fill this field.");
                    address.setFirstName(newFirstName);
                    address.setLastName(newLastName);
                    addressDao.update(address);
                    console.readString("\nAddress updated!");
                    valid = true;
                    break;
                case "2":
                    //change st address
                    String newStAddress = console.checkEmptyString("Enter the street address:", "You must fill this field.");
                    address.setStreetAddress(newStAddress);
                    addressDao.update(address);
                    console.readString("\nAddress updated!");
                    valid = true;
                    break;
                case "3":
                    //change city
                    String newCity = console.checkEmptyString("Enter the city:", "You must fill this field.");
                    address.setCity(newCity);
                    addressDao.update(address);
                    console.readString("\nAddress updated!");
                    valid = true;
                    break;
                case "4":
                    //change state
                    String newState = console.checkEmptyString("Enter the state:", "You must fill this field.");
                    address.setState(newState);
                    addressDao.update(address);
                    console.readString("\nAddress updated!");
                    valid = true;
                    break;
                case "5":
                    //change zip
                    String newZip = console.checkEmptyString("Enter the zip code:", "You must fill this field.");
                    address.setZip(newZip);
                    addressDao.update(address);
                    console.readString("\nAddress updated!");
                    valid = true;
                    break;
                case "6":
                    //change country
                    String newCountry = console.checkEmptyString("Enter the country:", "You must fill this field.");
                    address.setCountry(newCountry);
                    addressDao.update(address);
                    console.readString("\nAddress updated!");
                    valid = true;
                    break;
                case "7":
                    //change secondary address
                    String newSecondAdd = console.checkEmptyString("Enter your secondary address:"
                            + "\n(P.O. Box, Apt #, Suite #)\nEnter 'none' to leave blank.", "You must fill this field.");
                    address.setSecAdd(newSecondAdd);
                    addressDao.update(address);
                    console.readString("\nAddress updated!");
                    valid = true;
                    break;
                case "quit":
                case "q":
                    valid = true;
                    break;
                default:
                    //error
                    console.readString("That is not an option!");
                    break;
            }
        }

    }

}
