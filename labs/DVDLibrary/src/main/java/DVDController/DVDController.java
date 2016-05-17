/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package DVDController;

import com.mycompany.dao.DVDDao;
import com.mycompany.dao.DVDDaoLambdaImpl;
import com.mycompany.dao.NoteDao;
import com.mycompany.dto.DVD;
import com.mycompany.dto.Notes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class DVDController {

    private DVDDao dvdDao = new DVDDaoLambdaImpl();
    private NoteDao noteDao = new NoteDao();
    ConsoleIO console = new ConsoleIO();

    public void runApp() {

        boolean run = true;

        while (run == true) {

            showMainMenu();
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
                    console.readString("Invalid entry!");
                    break;
            }

        }
        console.readString("Good bye!");
        dvdDao.encode();

    }

    public void addDvd() {
        String notes = "";
        boolean addNoteAgain = true;
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
                while (addNoteAgain == true) {

                    Notes newNote = new Notes();
                    newNote.setTitle(dvdTitle);
                    newNote.setNote(console.checkEmptyString("Enter any notes you have for the film:",
                            "You cannot leave this field blank!"));
                    noteDao.create(newNote);

                    boolean confirm = console.yesCheck("Enter another note for " + dvdTitle + "? [yes/no]\n>", "Enter [yes/no] to proceed.\n>");
                    if (!confirm) {
                        break;
                    }
                }

                DVD newDvd = new DVD();
                Date newDate = console.getDate("Enter the film's release date. [mm/dd/yyyy]\n>", "That date does not meet the correct format! Please try again!");

                newDvd.setTitle(dvdTitle);
                newDvd.setDirector(director);
                newDvd.setRating(rating);
                newDvd.setStudio(studio);
                newDvd.setUserNote(notes);
                newDvd.setDvdDate(newDate);

                console.readString("\n" + dvdTitle + " added to Library!");
                dvdDao.create(newDvd, newDate);

                boolean confirm = console.yesCheck("\nAdd another DVD? [yes/no]\n>", "Enter [yes/no] to proceed.");
                addAgain = confirm == true;

            }

        }

    }

    public void removeDvd() {

        boolean removeAgain = true;
        boolean confirm;
        boolean found = false;

        int entryId = 0;

        showTitlesAndId();
        while (removeAgain == true) {
            String dvdId = console.getString("To remove a DVD, enter it's ID:"
                    + "(Enter 0 to cancel)\n>");
            if (dvdId.equals("0")) {
                removeAgain = false;
            } else {

                try {

                    entryId = Integer.parseInt(dvdId);

                } catch (Exception ex) {

                }
                List<DVD> dvds = dvdDao.decode();

                for (DVD delDVD : dvds) {

                    String delTitle = delDVD.getTitle();
                    int delId = delDVD.getId();

                    if (delId == entryId) {

                        found = true;
                        confirm = console.yesCheck("Are you sure you want to delete " + delTitle + "?[yes/no]\n>", "Enter [yes/no] to proceed.\n>");

                        if (confirm == true) {
                            dvdDao.delete(delDVD);
                            String upperTitle = delTitle.toUpperCase();
                            console.readString("'" + upperTitle + "' DELETED");
                            break;
                        }
                    }
                }
                if (!found) {
                    console.readString("\nDVD not found!\n");
                }
                removeAgain = console.yesCheck("Search again? [yes/no]\n>", "Enter [yes/no] to proceed.\n>");

            }
        }
    }

    public void findByTitle() {

        boolean findAgain = true;
        boolean found = false;
        String fTitle;
        while (findAgain == true) {
            String dvdTitle = console.getString("To find a DVD, enter their its title:"
                    + "(Enter 0 to cancel)\n>");
            if (dvdTitle.equals("0")) {
                return;
            } else {

                List<DVD> dvds = dvdDao.decode();
                List<DVD> foundDvds = new ArrayList();
                for (DVD foundDvd : dvds) {

                    try {
                        foundDvd = dvdDao.get(dvdTitle);
                        fTitle = foundDvd.getTitle();
                        if (fTitle.equalsIgnoreCase(dvdTitle)) {
                            foundDvds.add(foundDvd);
                            found = true;
                        }
                    } catch (Exception ex) {

                        console.readString("No DVDs were found with that title!");

                    }
                    if (found == true) {
                        int dvdsFound = foundDvds.size();
                        console.readString(dvdsFound + " were found with that title.");
                        getDvdList(foundDvds);
                    }
                    boolean confirm = console.yesCheck("\nSearch again? [yes/no]\n>", "Enter [yes/no] to proceed.\n>");
                    findAgain = confirm == true;

                }
            }
        }

    }

    public void viewAll() {

        List<DVD> dvds = dvdDao.decode();

        console.readString("\nAll Dvds:");

        getDvdList(dvds);

    }

    public void getDvdList(List<DVD> dvdList) {

        for (DVD dvdsSaved : dvdList) {

            displayDvdInfo(dvdsSaved);

        }
    }

    public void displayDvdInfo(DVD savedDvd) {

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        List<Notes> noteList = noteDao.decode();

        console.readString("\n+----------------------------+");
        console.readString("DVD No.: " + savedDvd.getId() + "\n+----------------------------+\nTitle: " + savedDvd.getTitle() + "\nDirector: " + savedDvd.getDirector() + "\nMPAA Rating: "
                + savedDvd.getRating().toUpperCase() + "\nStudio: " + savedDvd.getStudio() + "\nRelease Date: "
                + sdf.format(savedDvd.getDvdDate()) + "\nUser Notes:");
        for (Notes note : noteList) {

            if (note.getTitle().equalsIgnoreCase(savedDvd.getTitle())) {
                console.readString(note.getNote());
            }

        }

    }

    public void showTitlesAndId() {

        List<DVD> dvdList = dvdDao.decode();

        console.readString("ID # | Title");
        for (DVD dvdOnFile : dvdList) {

            String title = dvdOnFile.getTitle();
            int id = dvdOnFile.getId();

            console.readString(id + " | " + title);

        }

    }

    public void editDvd() {

        boolean addNoteAgain = true;
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

                        while (!valid) {

                            showEditMenu();
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

                                    while (addNoteAgain == true) {

                                        Notes newNote = new Notes();
                                        newNote.setTitle(dvdWithId.getTitle());
                                        newNote.setNote(console.checkEmptyString("Enter any notes you have for the film:",
                                                "You cannot leave this field blank!"));
                                        noteDao.create(newNote);

                                        boolean confirm = console.yesCheck("Enter another note for " + dvdWithId.getTitle() + "? [yes/no]\n>", "Enter [yes/no] to proceed.\n>");
                                        if (!confirm) {
                                            break;
                                        }
                                    }
                                    valid = true;
                                    break;

                                case 6:
                                    //date
                                    Date newDate = console.getDate("Enter the film's release date. [mm/dd/yyyy]\n>", "That date does not meet the format! Please try again!");
                                    dvdWithId.setDvdDate(newDate);

                                    dvdDao.update(dvdWithId);
                                    valid = true;
                                    break;
                                default:
                                    //error
                                    console.readString("That is not an option!");
                                    break;

                            }

                        }

                        console.readString("\nDVD updated!");
                    } else {
                        console.readString("No DVDs with that ID# were found!");
                    }

                }

                boolean confirm = console.yesCheck("Edit another DVD? [yes/no]\n>", "Enter [yes/no] to proceed.\n>");
                editAgain = confirm == true;

            }
        }
    }

    public void showMainMenu() {

        console.readString("\n+----------------------------+");
        console.readString("| Welcome to DVD Library!    |");
        console.readString("+----------------------------+");
        console.readString("|   1)Add DVD                |");
        console.readString("|   2)Remove DVD             |");
        console.readString("|   3)Find DVD by Title      |");
        console.readString("|   4)View Collection        |");
        console.readString("|   5)Edit DVD Info          |");
        console.readString("|   6)Date                   |");
        console.readString("|----------------------------|");
        console.readString("|                      [quit]|");
        console.readString("+----------------------------+");
    }

    public void showEditMenu() {
        console.readString("+----------------------------+");
        console.readString("| Edit your DVD!             |");
        console.readString("+----------------------------+");
        console.readString("|   1)Title                  |");
        console.readString("|   2)Director               |");
        console.readString("|   3)Rating                 |");
        console.readString("|   4)Studio                 |");
        console.readString("|   5)User Note              |");
        console.readString("|   6)Date                   |");
        console.readString("+----------------------------+");
    }
}
