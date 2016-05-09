/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.controller;

import com.mycompany.dao.AddressDao;
import com.mycompany.dto.Address;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class AddressController {

    ConsoleIO console = new ConsoleIO();
    private AddressDao addDao = new AddressDao();

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
                    System.out.println("That is not an option!");
            }
        }
        System.out.println("Good bye!");
        addDao.encode();
    }

    public void addAddress() {

        boolean addAgain = true;

        System.out.println("\n");
        System.out.println("+----------------------------+");
        System.out.println("| Add Address...             |");
        System.out.println("+----------------------------+");
        while (addAgain == true) {
            Address newAddress = new Address();
            String firstName = console.checkEmptyString("Enter the first name of your\ncontact:"
                    + "(Enter 1 to cancel)", "You must fill this field.");
            if (firstName.equals("1")) {
                addAgain = false;
            } else {
                String lastName = console.checkEmptyString("Enter the last name of your\ncontact:", "You must fill this field.");

                String stAddress = console.checkEmptyString("Enter the street address:", "You must fill this field.");
                String city = console.checkEmptyString("Enter the city:", "You must fill this field.");
                String state = console.checkEmptyString("Enter the state:", "You must fill this field.");
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

                addDao.create(newAddress);

                System.out.println("\nAddress added to address book.");
                addAgain = confirmAgain("\nAdd another address? [yes/no]\n>");
            }
        }
    }

    public void removeAddress() {

        boolean confirm;
        boolean removeAgain = true;

        System.out.println("\n");
        System.out.println("+----------------------------+");
        System.out.println("| Remove Address...          |");
        System.out.println("+----------------------------+");

        while (removeAgain == true) {
            String firstName = console.getString("To delete a contact, enter\ntheir first name:"
                    + "(Enter 1 to cancel)\n>");
            if (firstName.equals("1")) {
                removeAgain = false;
            } else {
                String lastName = console.getString("Now enter their last name:\n>");
                String stAddress = console.getString("Now enter their street address:\n>");

                List<Address> addresses = addDao.decode();

                for (Address delAddress : addresses) {

                    if (firstName.equalsIgnoreCase(delAddress.getFirstName()) && lastName.equalsIgnoreCase(delAddress.getLastName()) && stAddress.equalsIgnoreCase(delAddress.getStreetAddress())) {

                        showAddress(delAddress);

                        confirm = console.yesCheck("\nDelete this address? [yes/no]\n>", "Enter [yes/no] to proceed.\n>");

                        if (confirm == true) {
                            addDao.delete(delAddress);
                            System.out.println("\nADDRESS DELETED\n");

                            removeAgain = confirmAgain("Delete another address? [yes/no]\n>");

                            break;
                        }
                    } else {
                        System.out.println("\nAddress not found!\n");
                        removeAgain = confirmAgain("Seach again? [yes/no]");
                        break;
                    }
                }
            }
        }
    }

    public void findAddress() {

        boolean findAgain = true;
        String fLName = "";

        System.out.println("\n");
        System.out.println("+----------------------------+");
        System.out.println("| View Address...            |");
        System.out.println("+----------------------------+");
        while (findAgain == true) {
            String lastName = console.getString("To find a contact, enter their last name:"
                    + "(Enter 1 to cancel)\n>");
            if (lastName.equals("1")) {
                findAgain = false;
            } else {

                List<Address> addresses = addDao.decode();
                List<Address> foundAddresses = new ArrayList();
                for (Address foundAddress : addresses) {

                    foundAddress = addDao.get(lastName);

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

                    System.out.println("No contacts were found with that last name!");

                } else {
                    System.out.println("Contacts Found: " + addsFound);
                    for (Address addressSaved : addresses) {

                        showAddress(addressSaved);

                    }
                    findAgain = confirmAgain("Seach again? [yes/no]");
                }
            }
        }
    }

    public void countAdds() {

        boolean valid = false;
        System.out.println("\n");
        System.out.println("+----------------------------+");
        System.out.println("| Count Contacts...          |");
        System.out.println("+----------------------------+");

        List<Address> listOfAddresses = addDao.decode();

        int listSize = listOfAddresses.size();

        System.out.println("There are " + listSize + " contacts in\nyour address book.");
        while (!valid) {
            String goBack = console.getString("\nEnter 1 to go back.\n>");
            if (goBack.equals("1")) {
                break;
            }
        }

    }

    public void viewAll() {

        List<Address> addresses = addDao.decode();
        System.out.println("\n");
        System.out.println("+----------------------------+");
        System.out.println("| View All Contacts...       |");
        System.out.println("+----------------------------+");

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

                List<Address> addresses = addDao.decode();
                List<Address> foundAddresses = new ArrayList();
                for (Address foundAddress : addresses) {

                    foundAddress = addDao.get(lastName);
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
                    System.out.println("No contacts were found with that last name!");
                } else {
                    System.out.println("Contacts Found: " + addsFound);
                    for (Address addressInList : foundAddresses) {

                        //add
                        showAddress(addressInList);

                        addressId = console.getInteger("Enter the Address ID for the address you wish to change:\n>", "That is an invalid entry!");

                        for (Address addWithId : foundAddresses) {

                            if (addWithId.getIdentifier() == addressId) {

                                addWithId = addDao.get(addressId);
                                showEditMenu();

                                while (!valid) {

                                    valid = validateEdit(addWithId);

                                }

                                System.out.println("\nAddress updated!");
                            }

                        }
                    }

                }

                editAgain = confirmAgain("Edit another address? [yes/no]\n>");

            }
        }
    }

    public void showMenu() {

        System.out.println("+----------------------------+");
        System.out.println("| Welcome! Choose an option! |");
        System.out.println("+----------------------------+");
        System.out.println("|   1)Add Address            |");
        System.out.println("|   2)Delete Address         |");
        System.out.println("|   3)Find Address           |");
        System.out.println("|   4)View Address Count     |");
        System.out.println("|   5)View All Addresses     |");
        System.out.println("|   6)Update Addresses       |");
        System.out.println("|  ------------------------  |");
        System.out.println("|                    [quit]  |");
        System.out.println("+----------------------------+");

    }

    public void showEditMenu() {

        System.out.println("+----------------------------+");
        System.out.println("| Edit your address!         |");
        System.out.println("+----------------------------+");
        System.out.println("|   1)Name                   |");
        System.out.println("|   2)Street Address         |");
        System.out.println("|   3)City                   |");
        System.out.println("|   4)State                  |");
        System.out.println("|   5)Zip                    |");
        System.out.println("|   6)Country                |");
        System.out.println("|   7)Secondary Address      |");
        System.out.println("+----------------------------+");
    }

    public void showAddress(Address address) {

        String fSecAdd = address.getSecAdd();

        if (fSecAdd.equalsIgnoreCase("none")) {
            fSecAdd = "";
        }
        System.out.println("+----------------------------+");
        System.out.println("Address ID: " + address.getIdentifier());
        System.out.println("\n" + address.getFirstName() + " " + address.getLastName() + "\n"
                + address.getStreetAddress() + "\n" + address.getCity() + ", " + address.getState() + ", " + address.getZip() + "\n"
                + address.getCountry() + " " + fSecAdd + "\n");
        System.out.println("+----------------------------+");
    }

    public boolean validateEdit(Address address) {

        boolean valid = false;
        int selection = console.getInteger("What field would you like to change?\n>", "That is an invalid entry!");

        checkEditEntry(selection, address);

        return valid;
    }

    public boolean confirmAgain(String question) {

        boolean again;
        boolean confirm = console.yesCheck(question, "Enter [yes/no] to proceed.");
        again = confirm == true;

        return again;
    }

    public boolean checkEditEntry(int selection, Address address) {

        boolean valid = false;
        
        switch (selection) {

            case 1:
                //change name
                String newFirstName = console.checkEmptyString("Enter the first name of your\ncontact:", "You must fill this field.");
                String newLastName = console.checkEmptyString("Enter the last name of your\ncontact:", "You must fill this field.");
                address.setFirstName(newFirstName);
                address.setLastName(newLastName);
                addDao.update(address);
                valid = true;
                break;
            case 2:
                //change st address
                String newStAddress = console.checkEmptyString("Enter the street address:", "You must fill this field.");
                address.setStreetAddress(newStAddress);
                addDao.update(address);
                valid = true;
                break;
            case 3:
                //change city
                String newCity = console.checkEmptyString("Enter the city:", "You must fill this field.");
                address.setCity(newCity);
                addDao.update(address);
                valid = true;
                break;
            case 4:
                //change state
                String newState = console.checkEmptyString("Enter the state:", "You must fill this field.");
                address.setState(newState);
                addDao.update(address);
                valid = true;
                break;
            case 5:
                //change zip
                String newZip = console.checkEmptyString("Enter the zip code:", "You must fill this field.");
                address.setZip(newZip);
                addDao.update(address);
                valid = true;
                break;
            case 6:
                //change country
                String newCountry = console.checkEmptyString("Enter the country:", "You must fill this field.");
                address.setCountry(newCountry);
                addDao.update(address);
                valid = true;
                break;
            case 7:
                //change secondary address
                String newSecondAdd = console.checkEmptyString("Enter your secondary address:"
                        + "\n(P.O. Box, Apt #, Suite #)\nEnter 'none' to leave blank.", "You must fill this field.");
                address.setSecAdd(newSecondAdd);
                addDao.update(address);
                valid = true;
                break;
            default:
                //error
                System.out.println("That is not an option!");
                break;

        }

        return valid;

    }

}
