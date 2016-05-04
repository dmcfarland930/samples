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

                System.out.println(dvdTitle + " added to Library!");
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

                        System.out.println("Are you sure you want to delete" + delTitle + "?");

                        boolean confirm = console.yesCheck("Are you sure you want to delete" + delTitle + "?[yes/no]\n>", "Enter [yes/no] to proceed.\n>");

                        if (confirm == true) {
                            dvdDao.delete(delDVD);
                            String upperTitle = delTitle.toUpperCase();
                            System.out.println(upperTitle + "DELETED");
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
                    for (DVD dvdInList : foundDvds) {

                        int fId = dvdInList.getId();
                        String fTitle = dvdInList.getTitle();
                        String fDirect = dvdInList.getDirector();
                        String fRate = dvdInList.getRating();
                        String fStudio = dvdInList.getStudio();
                        String fNote = dvdInList.getUserNote();
                        String fMonth = dvdInList.getDvdDate().getMonth();
                        String fDay = dvdInList.getDvdDate().getDay();
                        String fYear = dvdInList.getDvdDate().getYear();

                        System.out.println("+----------------------------+");
                        System.out.println(fId + " " + fTitle + " " + fDirect + "\n"
                                + fRate + "\n" + fStudio + ", " + fNote + "\n"
                                + fMonth+" "+fDay+", "+fYear);
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

    public void showTitlesAndId() {

        List<DVD> dvdList = dvdDao.decode();
        for (DVD dvdOnFile : dvdList) {

            String title = dvdOnFile.getTitle();
            int id = dvdOnFile.getId();

            System.out.println("ID # | Title");
            System.out.println(id + " | " + title);

        }

    }

}
