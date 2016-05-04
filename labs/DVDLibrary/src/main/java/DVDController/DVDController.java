/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package DVDController;

import com.mycompany.dao.DVDDao;
import com.mycompany.dto.DVD;
import com.mycompany.dto.Date;

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
                    break;
                case "3":
                    //find by title
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
                dvdDao.create(newDvd);
                
                boolean confirm = console.yesCheck("\nAdd another DVD? [yes/no]\n>", "Enter [yes/no] to proceed.");
                addAgain = confirm == true;
                
            }
            
        }
        
    }
    
}
