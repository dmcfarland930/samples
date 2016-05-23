/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */

import DVDController.ConsoleIO;
import com.mycompany.dao.DVDDao;
import com.mycompany.dao.DVDDaoLambdaImpl;
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
    DVDDao dvdDao = new DVDDaoLambdaImpl();
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
        try {
            date = sFormat.parse("01/15/1974");
        } catch (Exception ex) {
            console.readString("Error");
        }

        //set test properties
        newDvd.setTitle("TEST 2: THE TESTENING");
        newDvd.setDirector("TEST");
        newDvd.setRating("R");
        newDvd.setStudio("TEST PICTURES");
        newDvd.setDvdDate(date);
        //create DVD with above properties
        dvdDao.create(newDvd, date);

        //add the DVD to a list
        dvdList.add(dvdDao.get("TEST 2: THE TESTENING"));
        boolean empty = dvdList.isEmpty();

        //test to see if DVD made it to list
        Assert.assertEquals(false, empty);

        //pull DVD from list
        DVD getDvd = dvdDao.get("TEST 2: THE TESTENING");

        //pull Date from DVD
        Date pulledDate = getDvd.getDvdDate();
        //convert Date to string
        String dateString = sFormat.format(pulledDate);

        //pulled DVD should have these matching properties
        Assert.assertEquals("TEST 2: THE TESTENING", getDvd.getTitle());
        Assert.assertEquals("TEST", getDvd.getDirector());
        Assert.assertEquals("R", getDvd.getRating());
        Assert.assertEquals("TEST PICTURES", getDvd.getStudio());
        Assert.assertEquals("01/15/1974", dateString);

        //update DVD
        getDvd.setTitle("TEST 3: REVENGE OF THE QUIZ");
        dvdDao.update(getDvd);
        //DVD title should now be "TEST 3: REVENGE OF THE QUIZ"
        Assert.assertEquals("TEST 3: REVENGE OF THE QUIZ", getDvd.getTitle());

        //delete DVD
        dvdDao.delete(getDvd);
//        DVD delDvd = new DVD();
//        try {
//            delDvd = addDao.get("TEST 3: REVENGE OF THE QUIZ");
//        } catch (Exception ex) {
//            console.readString("EMPTY");
//        }
//        //DVD should no longer be found
//        Assert.assertEquals(null, delDvd);
    }

    @Test
    public void test2() {

        DVD newDvd = new DVD();
        List<DVD> dvds;
        String title = "";
        //create a date object
        Date date = new Date();
        //set formatting for date
        SimpleDateFormat sFormat = new SimpleDateFormat("MM/dd/yyyy");

        //parse string into a Date
        try {
            date = sFormat.parse("01/15/1974");
        } catch (Exception ex) {
            console.readString("Error");
        }

        //set test properties
        newDvd.setTitle("TEST 2: THE TESTENING");
        newDvd.setDvdDate(date);
        //create DVD with above properties
        dvdDao.create(newDvd, date);

        dvds = dvdDao.getMoviesAfterDate(50);

        for (DVD dvdsInTimeFrame : dvds) {
            title = dvdsInTimeFrame.getTitle();
        }
        Assert.assertEquals("TEST 2: THE TESTENING", title);
        
        dvdDao.delete(newDvd);

    }

    @Test
    public void test3() {
        DVD newDvd = new DVD();
        DVD newDvd2 = new DVD();
        String titleNewest;
        String titleOldest;
        //create a date object
        Date date = new Date();
        Date date2 = new Date();
        //set formatting for date
        SimpleDateFormat sFormat = new SimpleDateFormat("MM/dd/yyyy");

        //parse string into a Date
        try {
            date = sFormat.parse("01/15/1900");
        } catch (Exception ex) {
            console.readString("Error");
        }

        //set test properties
        newDvd.setTitle("TEST 2: THE TESTENING");
        newDvd.setDvdDate(date);
        newDvd2.setTitle("TEST 9: THE REVENGE OF THE TEST");
        newDvd2.setDvdDate(date2);
        //create DVD with above properties
        dvdDao.create(newDvd, date);
        dvdDao.create(newDvd2, date2);
        DVD dvd;
        DVD dvd2;
        dvd = dvdDao.findNewestMovie();
        dvd2 = dvdDao.findOldestMovie();

        titleNewest = dvd.getTitle();
        titleOldest = dvd2.getTitle();
        
        Assert.assertEquals("TEST 9: THE REVENGE OF THE TEST", titleNewest);
        Assert.assertEquals("TEST 2: THE TESTENING", titleOldest);
        
        dvdDao.delete(dvd);
        dvdDao.delete(dvd2);
        
    }

}
