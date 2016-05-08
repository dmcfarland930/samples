/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package DVDController;

import com.mycompany.dao.DVDDao;
import com.mycompany.dto.DVD;
import com.mycompany.dto.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class DVDController {

    DVDDao dvdDao = new DVDDao();
    ConsoleIO console = new ConsoleIO();

    public void runApp() {

        boolean run = true;

        while (run == true) {

            System.out.println("Welcome to DVD Library!");
            System.out.println("~Because DVDs are better than books!~");
            System.out.println("-Select an Option-");
            System.out.println("\n1) Add DVD");
            System.out.println("2) Remove DVD");
            System.out.println("3) Find DVD by Title");
            System.out.println("4) View DVD Collection");
            System.out.println("5) Edit DVD");
            System.out.println("                [quit]");

            String selection = console.getString(">");

            switch (selection) {

                case "1":
                    //add dvd
                    addDvd();
                    break;
                case "2":
                    //remove dvd
                    removeDvd();
                    break;
                case "3":
                    //find by title
                    findByTitle();
                    break;
                case "4":
                    //view all
                    viewAll();
                    break;
                case "5":
                    //edit Dvd
                    editDvd();
                    break;
                case "q":
                case "quit":
                    //end app
                    run = false;
                    break;
                default:
                    System.out.println("Invalid entry!");
                    break;
            }

        }
        System.out.println("Good bye!");
        dvdDao.encode();

    }

    public void addDvd() {

        boolean addAgain = true;

        while (addAgain == true) {

            String dvdTitle = console.checkEmptyString("Enter the title of your "
                    + "DVD (Enter 0 to cancel):", "You cannot leave this field blank!");
            if (dvdTitle.equals("0")) {
                addAgain = false;
            } else {
                String director = console.checkEmptyString("Enter the film's director:",
                        "You cannot leave this field blank!");
                String rating = console.checkEmptyString("Enter the film's MPAA rating:",
                        "You cannot leave this field blank!");
                String studio = console.checkEmptyString("Enter the film's studio:",
                        "You cannot leave this field blank!");
                String notes = console.checkEmptyString("Enter any notes you have for the film:",
                        "You cannot leave this field blank!");
                System.out.println("Enter the film's release date:");
                String month = console.checkEmptyString("Month:", "You cannot leave this field blank!");
                String day = console.checkEmptyString("Day:", "You cannot leave this field blank!");
                String year = console.checkEmptyString("Year:", "You cannot leave this field blank!");

                DVD newDvd = new DVD();
                Date newDate = new Date();

                newDvd.setTitle(dvdTitle);
                newDvd.setDirector(director);
                newDvd.setRating(rating);
                newDvd.setStudio(studio);
                newDvd.setUserNote(notes);
                newDate.setMonth(month);
                newDate.setDay(day);
                newDate.setYear(year);

                System.out.println("\n" + dvdTitle + " added to Library!");
                dvdDao.create(newDvd, newDate);

                boolean confirm = console.yesCheck("\nAdd another DVD? [yes/no]\n>", "Enter [yes/no] to proceed.");
                addAgain = confirm == true;

            }

        }

    }

    public void removeDvd() {
        boolean removeAgain = true;

        showTitlesAndId();
        while (removeAgain == true) {
            String dvdId = console.getString("To remove a DVD, enter it's ID:"
                    + "(Enter 0 to cancel)\n>");
            if (dvdId.equals("0")) {
                removeAgain = false;
            } else {

                int entryId = Integer.parseInt(dvdId);
                List<DVD> dvds = dvdDao.decode();

                for (DVD delDVD : dvds) {

                    String delTitle = delDVD.getTitle();
                    int delId = delDVD.getId();

                    if (delId == entryId) {

                        boolean confirm = console.yesCheck("Are you sure you want to delete " + delTitle + "?[yes/no]\n>", "Enter [yes/no] to proceed.\n>");

                        if (confirm == true) {
                            dvdDao.delete(delDVD);
                            String upperTitle = delTitle.toUpperCase();
                            System.out.println("'" + upperTitle + "' DELETED");
                            confirm = console.yesCheck("Delete another DVD? [yes/no]\n>", "Enter [yes/no] to proceed.\n>");
                            removeAgain = confirm == true;
                            break;
                        }
                    } else {
                        System.out.println("\nDVD not found!\n");
                        boolean confirm = console.yesCheck("Search again? [yes/no]\n>", "Enter [yes/no] to proceed.\n>");
                        removeAgain = confirm == true;
                        break;
                    }
                }
            }
        }
    }

    public void findByTitle() {

        boolean findAgain = true;

        while (findAgain == true) {
            String dvdTitle = console.getString("To find a DVD, enter their its title:"
                    + "(Enter 0 to cancel)\n>");
            if (dvdTitle.equals("0")) {
                findAgain = false;
            } else {

                List<DVD> dvds = dvdDao.decode();
                List<DVD> foundDvds = new ArrayList();
                for (DVD foundDvd : dvds) {

                    foundDvd = dvdDao.get(dvdTitle);
                    String fTitle = foundDvd.getTitle();
                    if (fTitle.equalsIgnoreCase(dvdTitle)) {
                        foundDvds.add(foundDvd);
                    }

                }
                int dvdsFound = foundDvds.size();
                if (foundDvds.isEmpty()) {
                    System.out.println("No DVDs were found with that title!");
                } else {
                    System.out.println(dvdsFound + " were found with that title.");
                    getDvdList(foundDvds);
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

    public void viewAll() {

        List<DVD> dvds = dvdDao.decode();

        System.out.println("All Dvds:\n");

        getDvdList(dvds);

    }

    public void getDvdList(List<DVD> dvdList) {

        for (DVD dvdsSaved : dvdList) {

            displayDvdInfo(dvdsSaved);

        }
    }

    public void displayDvdInfo(DVD savedDvd) {
        System.out.println("+----------------------------+");
        System.out.println(savedDvd.getId() + " " + savedDvd.getTitle() + " " + savedDvd.getDirector() + "\n"
                + savedDvd.getRating() + "\n" + savedDvd.getStudio() + ", " + savedDvd.getUserNote() + "\n"
                + savedDvd.getDvdDate().getMonth() + " " + savedDvd.getDvdDate().getDay() + ", " + savedDvd.getDvdDate().getYear());
    }

    public void showTitlesAndId() {

        List<DVD> dvdList = dvdDao.decode();
        for (DVD dvdOnFile : dvdList) {

            String title = dvdOnFile.getTitle();
            int id = dvdOnFile.getId();

            System.out.println("ID # | Title");
            System.out.println(id + " | " + title);

        }

    }

    public void editDvd() {

        boolean editAgain = true;
        boolean valid = false;

        while (editAgain == true) {

            viewAll();

            int idNum = console.getInteger("To edit a Dvd, enter its ID #: "
                    + "(Enter 0 to cancel)\n>", "That is an invalid number!");

            if (idNum == 0) {

                editAgain = false;

            } else {

                List<DVD> dvds = dvdDao.decode();

                for (DVD dvdWithId : dvds) {

                    if (dvdWithId.getId() == idNum) {

                        dvdWithId = dvdDao.get(idNum);

                        System.out.println("+----------------------------+");
                        System.out.println("| Edit your DVD!             |");
                        System.out.println("+----------------------------+");
                        System.out.println("|   1)Title                  |");
                        System.out.println("|   2)Director               |");
                        System.out.println("|   3)Rating                 |");
                        System.out.println("|   4)Studio                 |");
                        System.out.println("|   5)User Note              |");
                        System.out.println("|   6)Date                   |");
                        System.out.println("+----------------------------+");
                        
                        while (!valid) {
                            int selection = console.getInteger("What field would you like to change?\n>", "That is an invalid entry!");

                            switch (selection) {

                                case 1:
                                    //change name
                                    String dvdTitle = console.checkEmptyString("Enter the title of your "
                                            + "DVD (Enter 0 to cancel):", "You cannot leave this field blank!");
                                    dvdWithId.setTitle(dvdTitle);
                                    dvdDao.update(dvdWithId);
                                    valid = true;
                                    break;
                                case 2:
                                    //change director
                                    String director = console.checkEmptyString("Enter the film's director:",
                                            "You cannot leave this field blank!");
                                    dvdWithId.setDirector(director);
                                    dvdDao.update(dvdWithId);
                                    valid = true;
                                    break;
                                case 3:
                                    //change rating                                                
                                    String rating = console.checkEmptyString("Enter the film's MPAA rating:",
                                            "You cannot leave this field blank!");
                                    dvdWithId.setRating(rating);
                                    dvdDao.update(dvdWithId);
                                    valid = true;
                                    break;
                                case 4:
                                    //change studio
                                    String studio = console.checkEmptyString("Enter the film's studio:",
                                            "You cannot leave this field blank!");
                                    dvdWithId.setStudio(studio);
                                    dvdDao.update(dvdWithId);
                                    valid = true;
                                    break;
                                case 5:
                                    //change notes
                                    String notes = console.checkEmptyString("Enter any notes you have for the film:",
                                            "You cannot leave this field blank!");
                                    dvdWithId.setUserNote(notes);
                                    dvdDao.update(dvdWithId);
                                    valid = true;
                                    break;
                                case 6:
                                    //date
                                    Date newDate = new Date();
                                    System.out.println("Enter the film's release date:");
                                    String month = console.checkEmptyString("Month:", "You cannot leave this field blank!");
                                    String day = console.checkEmptyString("Day:", "You cannot leave this field blank!");
                                    String year = console.checkEmptyString("Year:", "You cannot leave this field blank!");
                                    newDate.setMonth(month);
                                    newDate.setDay(day);
                                    newDate.setYear(year);
                                    dvdWithId.setDvdDate(newDate);

                                    dvdDao.update(dvdWithId);
                                    valid = true;
                                    break;
                                default:
                                    //error
                                    System.out.println("That is not an option!");
                                    break;

                            }

                        }

                        System.out.println("\nDVD updated!");
                    } else {
                        System.out.println("No DVDs with that ID# were found!");
                    }

                }

                boolean confirm = console.yesCheck("Edit another DVD? [yes/no]\n>", "Enter [yes/no] to proceed.\n>");
                if (confirm == true) {
                    editAgain = true;
                } else {
                    editAgain = false;

                }

            }
        }
    }
}
