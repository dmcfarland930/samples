/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.thesoftwareguild.interfaces.dao.AddressBookDao;

import com.thesoftwareguild.interfaces.dto.Address;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class AddressBookTest {

    public AddressBookTest() {
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
    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

    AddressBookDao dao = ctx.getBean("addressDao", AddressBookDao.class);

    @Test
    public void testAddressBook() {
        
        //If it doesn't work, check your Impl nextId.

        //Create empty Address Object
        Address myAddress = new Address();

        //Set address attributes
        myAddress.setFirstName("THIS");
        myAddress.setLastName("ISTEST");
        myAddress.setStreetNumber("1234");
        myAddress.setStreetName("TEST ADDRESS");
        myAddress.setCity("AKRON");
        myAddress.setState("OH");
        myAddress.setZip("12345");
        myAddress.setId(1111);

        //Add the address to the list.
        dao.create(myAddress);

        //Find and get the address just created from the list.
        Address actual2 = dao.get(1);

        //Check attributes of Address Object pulled from the list.
        Assert.assertEquals("THIS", actual2.getFirstName());
        Assert.assertEquals("ISTEST", actual2.getLastName());
        Assert.assertEquals("1234", actual2.getStreetNumber());
        Assert.assertEquals("TEST ADDRESS", actual2.getStreetName());
        Assert.assertEquals("AKRON", actual2.getCity());
        Assert.assertEquals("OH", actual2.getState());
        Assert.assertEquals("12345", actual2.getZip());

        //Delete address
        dao.delete(1);

        //Attempt to pull deleted Address Object
        Address actual3 = dao.get(1);

        //Confirm  attempt returns null(no address).
        Assert.assertEquals(null, actual3);
    }

    @Test
    public void testCreate2() {

        Address myAddress = new Address();

        myAddress.setFirstName("MICHAEL");
        myAddress.setLastName("MOXLEY");
        myAddress.setStreetNumber("123");
        myAddress.setStreetName("MAIN ST");
        myAddress.setCity("YOUNGSTOWN");
        myAddress.setState("OHIO");
        myAddress.setZip("54321");
        myAddress.setId(2222);

        dao.create(myAddress);

        Address actual2 = dao.get(1);

        Assert.assertEquals("MICHAEL", actual2.getFirstName());
        Assert.assertEquals("MOXLEY", actual2.getLastName());
        Assert.assertEquals("123", actual2.getStreetNumber());
        Assert.assertEquals("MAIN ST", actual2.getStreetName());
        Assert.assertEquals("YOUNGSTOWN", actual2.getCity());
        Assert.assertEquals("OHIO", actual2.getState());
        Assert.assertEquals("54321", actual2.getZip());

        dao.delete(1);

        Address actual3 = dao.get(1);

        Assert.assertEquals(null, actual3);
    }

    @Test
    public void testCreate3() {

        Address myAddress = new Address();

        myAddress.setFirstName("FIRST");
        myAddress.setLastName("LAST");
        myAddress.setStreetNumber("1234");
        myAddress.setStreetName("Zzz RD");
        myAddress.setCity("CITY");
        myAddress.setState("STATE");
        myAddress.setZip("69696");
        myAddress.setId(3333);

        dao.create(myAddress);

        Address actual2 = dao.get(1);

        Assert.assertEquals("FIRST", actual2.getFirstName());
        Assert.assertEquals("LAST", actual2.getLastName());
        Assert.assertEquals("1234", actual2.getStreetNumber());
        Assert.assertEquals("Zzz RD", actual2.getStreetName());
        Assert.assertEquals("CITY", actual2.getCity());
        Assert.assertEquals("STATE", actual2.getState());
        Assert.assertEquals("69696", actual2.getZip());

        dao.delete(1);

        Address actual3 = dao.get(1);

        Assert.assertEquals(null, actual3);
    }
}
