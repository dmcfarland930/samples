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
            System.out.println("+----------------------------+");
            System.out.println("| Welcome! Choose an option! |");
            System.out.println("+----------------------------+");
            System.out.println("|   1)Add Address            |");
            System.out.println("|   2)Delete Address         |");
            System.out.println("|   3)Find Address           |");
            System.out.println("|   4)View Address Count     |");
            System.out.println("|   5)View All Addresses     |");
            System.out.println("|  ------------------------  |");
            System.out.println("|                    [quit]  |");
            System.out.println("+----------------------------+");

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
    }

    public void addAddress() {

        boolean addAgain = true;

        System.out.println("\n");
        System.out.println("+----------------------------+");
        System.out.println("| Add Address...             |");
        System.out.println("+----------------------------+");
        while (addAgain == true) {
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
                Address newAddress = new Address();
                newAddress.setFirstName(firstName);
                newAddress.setLastName(lastName);
                newAddress.setStreetAddress(stAddress);
                newAddress.setCity(city);
                newAddress.setState(state);
                newAddress.setZip(zip);
                newAddress.setCountry(country);
                newAddress.setSecAdd(secondAdd);

                System.out.println("\nAddress added to address book.");
                addDao.create(newAddress);
                boolean confirm = console.yesCheck("\nAdd another address? [yes/no]\n>", "Enter [yes/no] to proceed.");
                if (confirm == true) {
                    addAgain = true;
                } else {
                    addAgain = false;
                }
            }
        }
    }

    public void removeAddress() {
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

                    String delFName = delAddress.getFirstName();
                    String delLName = delAddress.getLastName();
                    String delStAdd = delAddress.getStreetAddress();
                    String delCity = delAddress.getCity();
                    String delState = delAddress.getState();
                    String delZip = delAddress.getZip();
                    String delCountry = delAddress.getCountry();
                    String delSecAdd = delAddress.getSecAdd();

                    if (delSecAdd.equalsIgnoreCase("none")) {
                        delSecAdd = "";
                    }
                    if (firstName.equalsIgnoreCase(delFName) && lastName.equalsIgnoreCase(delLName) && stAddress.equalsIgnoreCase(delStAdd)) {

                        System.out.println("\n" + delFName + " " + delLName + "\n"
                                + delStAdd + "\n" + delCity + ", " + delState + ", " + delZip + "\n"
                                + delCountry + " " + delSecAdd);

                        boolean confirm = console.yesCheck("\nDelete this address? [yes/no]\n>", "Enter [yes/no] to proceed.\n>");

                        if (confirm == true) {
                            addDao.delete(delAddress);
                            System.out.println("\nADDRESS DELETED\n");
                            confirm = console.yesCheck("Delete another address? [yes/no]\n>", "Enter [yes/no] to proceed.\n>");
                            removeAgain = confirm == true;
                            break;
                        }
                    } else {
                        System.out.println("\nAddress not found!\n");
                        boolean confirm = console.yesCheck("Search again? [yes/no]\n>", "Enter [yes/no] to proceed.\n>");
                        removeAgain = confirm == true;
                        break;
                    }
                }
            }
        }
    }

    public void findAddress() {

        boolean findAgain = true;

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
                    String fLName = foundAddress.getLastName();
                    if (fLName.equalsIgnoreCase(lastName)) {
                        foundAddresses.add(foundAddress);
                    }

                }
                int addsFound = foundAddresses.size();
                if (foundAddresses.isEmpty()) {
                    System.out.println("No contacts were found with that last name!");
                } else {
                    System.out.println("Contacts Found: " + addsFound);
                    for (Address addressInList : foundAddresses) {

                        String fFName = addressInList.getFirstName();
                        String fLName = addressInList.getLastName();
                        String fStAdd = addressInList.getStreetAddress();
                        String fCity = addressInList.getCity();
                        String fState = addressInList.getState();
                        String fZip = addressInList.getZip();
                        String fCountry = addressInList.getCountry();
                        String fSecAdd = addressInList.getSecAdd();

                        if (fSecAdd.equalsIgnoreCase("none")) {
                            fSecAdd = "";
                        }
                        System.out.println("+----------------------------+");
                        System.out.println("\n" + fFName + " " + fLName + "\n"
                                + fStAdd + "\n" + fCity + ", " + fState + ", " + fZip + "\n"
                                + fCountry + " " + fSecAdd + "\n");
                    }
                    boolean confirm = console.yesCheck("Search again? [yes/no]\n>", "Enter [yes/no] to proceed.\n>");
                    if (confirm == true) {
                        findAgain = true;
                    } else {
                        findAgain = false;
                    }
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

        List<Address> onFile = addDao.decode();
        List<Address> addresses = addDao.decode();
        System.out.println("\n");
        System.out.println("+----------------------------+");
        System.out.println("| View All Contacts...       |");
        System.out.println("+----------------------------+");

        for (Address addressSaved : addresses) {

            String fFName = addressSaved.getFirstName();
            String fLName = addressSaved.getLastName();
            String fStAdd = addressSaved.getStreetAddress();
            String fCity = addressSaved.getCity();
            String fState = addressSaved.getState();
            String fZip = addressSaved.getZip();
            String fCountry = addressSaved.getCountry();
            String fSecAdd = addressSaved.getSecAdd();
            if (fSecAdd.equalsIgnoreCase("none")) {
                fSecAdd = "";
            }
            System.out.println("+----------------------------+");
            System.out.println(fFName + " " + fLName + "\n"
                    + fStAdd + "\n" + fCity + ", " + fState + ", " + fZip + "\n"
                    + fCountry + " " + fSecAdd);
            System.out.println("+----------------------------+\n\n");
        }
    }
}
