/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */

import com.thesoftwareguild.interfaces.dao.AddressBookDao;
import com.thesoftwareguild.interfaces.dto.Address;
import java.util.ArrayList;
import java.util.List;
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
public class AddressTests {

    
    AddressBookDao lambdaDao;
    
    public AddressTests() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        lambdaDao = ctx.getBean("addressDao", AddressBookDao.class);
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

    @Test
    public void testCreate() {

        //declare a new address
        Address newAddress = new Address();
        List<Address> addressList = new ArrayList();
        //set test properties
        newAddress.setFirstName("TEST");
        newAddress.setLastName("McTESTER");
        newAddress.setStreetNumber("1337 TEST STREET");
        newAddress.setCity("TESTVILLE");
        newAddress.setState("TEST VIRGINIA");
        //newAddress.setCountry("TEST BERLIN");
        newAddress.setZip("29183");
        //newAddress.setSecAdd("Apt. 78A");

        //create Address with above properties
        lambdaDao.create(newAddress);

        //add the address to a list
        addressList.add(newAddress);
        boolean empty = addressList.isEmpty();

        //test to see if address made it to list
        Assert.assertEquals(false, empty);

        //pull address from list
        Address getAddress = lambdaDao.get(newAddress.getId());

        //pulled address should have these matching properties
        Assert.assertEquals("TEST", getAddress.getFirstName());
        Assert.assertEquals("McTESTER", getAddress.getLastName());
        Assert.assertEquals("1337 TEST STREET", getAddress.getStreetNumber());
        Assert.assertEquals("TESTVILLE", getAddress.getCity());
        Assert.assertEquals("TEST VIRGINIA", getAddress.getState());
//        Assert.assertEquals("TEST BERLIN", getAddress.getCountry());
        Assert.assertEquals("29183", getAddress.getZip());
//        Assert.assertEquals("Apt. 78A", getAddress.getSecAdd());

        //update address
        getAddress.setFirstName("CHAMP");
        lambdaDao.update(getAddress);
        //address first name should now be "CHAMP"
        Assert.assertEquals("CHAMP", getAddress.getFirstName());

        //delete address
        lambdaDao.delete(getAddress.getId());
//        Address delAddress = new Address();
//        try {
//            delAddress = addDao.get("McTESTER");
//        } catch (Exception ex){
//            console.readString("EMPTY");
//        } 
//        //address should no longer be found
//        Assert.assertEquals(null, delAddress);
    }

    @Test
    public void test2() {

        Address newAddress = new Address();

        newAddress.setFirstName("TEST");
        newAddress.setLastName("SNACKS");
        newAddress.setStreetNumber("1337 TEST STREET");
        newAddress.setCity("TESTVILLE");
        newAddress.setState("TEST VIRGINIA");
//        newAddress.setCountry("TEST BERLIN");
        newAddress.setZip("29183");
//        newAddress.setSecAdd("Apt. 78A");

        lambdaDao.create(newAddress);
        newAddress = lambdaDao.get(newAddress.getId());
        System.out.println(newAddress.getStreetNumber());
        lambdaDao.delete(newAddress.getId());

    }

    @Test
    public void test3() {

        Address newAddress = new Address();
        newAddress.setFirstName("TEST");
        newAddress.setLastName("SNACKS");
        newAddress.setStreetNumber("1337 TEST STREET");
        newAddress.setCity("TESTVILLE");
        newAddress.setState("TEST VIRGINIA");
//        newAddress.setCountry("TEST BERLIN");
        newAddress.setZip("29183");
//        newAddress.setSecAdd("Apt. 78A");
        lambdaDao.create(newAddress);

        Assert.assertEquals("1337 TEST STREET", newAddress.getStreetNumber());
        lambdaDao.delete(newAddress.getId());

    }

}
