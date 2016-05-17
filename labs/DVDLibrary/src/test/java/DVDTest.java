/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */

import DVDController.ConsoleIO;
import com.mycompany.dao.DVDDaoImpl;
import com.mycompany.dto.DVD;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author apprentice
 */
public class DVDTest {

    public DVDTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    DVDDaoImpl addDao = new DVDDaoImpl();
    ConsoleIO console = new ConsoleIO();
    

    @Test
    public void testCreate() {

        //declare a new DVD
        DVD newDvd = new DVD();
        List<DVD> dvdList = new ArrayList();
        
        //create a date object
        Date date = new Date();
        //set formatting for date
        SimpleDateFormat sFormat = new SimpleDateFormat("MM/dd/yyyy");

        //parse string into a Date
        try{
            date = sFormat.parse("01/15/1974");
        }catch(Exception ex){
            console.readString("Error");
        }
        
        
        //set test properties
        newDvd.setTitle("TEST 2: THE TESTENING");
        newDvd.setDirector("TEST");
        newDvd.setRating("R");
        newDvd.setStudio("TEST PICTURES");
        newDvd.setUserNote("FANTASTIC ROM COM");
        newDvd.setDvdDate(date);
        //create DVD with above properties
        addDao.create(newDvd, date);

        //add the DVD to a list
        dvdList.add(addDao.get("TEST 2: THE TESTENING"));
        boolean empty = dvdList.isEmpty();

        //test to see if DVD made it to list
        Assert.assertEquals(false, empty);

        //pull DVD from list
        DVD getDvd = addDao.get("TEST 2: THE TESTENING");
        
        //pull Date from DVD
        Date pulledDate = getDvd.getDvdDate();
        //convert Date to string
        String dateString = sFormat.format(pulledDate);

        //pulled DVD should have these matching properties
        Assert.assertEquals("TEST 2: THE TESTENING", getDvd.getTitle());
        Assert.assertEquals("TEST", getDvd.getDirector());
        Assert.assertEquals("R", getDvd.getRating());
        Assert.assertEquals("TEST PICTURES", getDvd.getStudio());
        Assert.assertEquals("FANTASTIC ROM COM", getDvd.getUserNote());
        Assert.assertEquals("01/15/1974", dateString);

        //update DVD
        getDvd.setTitle("TEST 3: REVENGE OF THE QUIZ");
        addDao.update(getDvd);
        //DVD title should now be "TEST 3: REVENGE OF THE QUIZ"
        Assert.assertEquals("TEST 3: REVENGE OF THE QUIZ", getDvd.getTitle());

        //delete DVD
        addDao.delete(getDvd);
        DVD delDvd = new DVD();
        try {
            delDvd = addDao.get("TEST 3: REVENGE OF THE QUIZ");
        } catch (Exception ex) {
            console.readString("EMPTY");
        }
        //DVD should no longer be found
        Assert.assertEquals(null, delDvd);
    }
}
