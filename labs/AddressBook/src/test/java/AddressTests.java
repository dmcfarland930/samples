/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 * Help was received from Mike Moxley
 */

import com.mycompany.dao.AddressDao;
import com.mycompany.dto.Address;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author apprentice
 */
public class AddressTests {

    public AddressTests() {
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
    AddressDao addDao = new AddressDao();

    @Test
    public void testCreate() {

        //declare a new address
        Address newAddress = new Address();
        List<Address> addressList = new ArrayList();
        //set test properties
        newAddress.setFirstName("TEST");
        newAddress.setLastName("McTESTER");
        newAddress.setStreetAddress("1337 TEST STREET");
        newAddress.setCity("TESTVILLE");
        newAddress.setState("TEST VIRGINIA");
        newAddress.setCountry("TEST BERLIN");
        newAddress.setZip("29183");
        newAddress.setSecAdd("Apt. 78A");

        //create Address with above properties
        addDao.create(newAddress);

        //add the address to a list
        addressList.add(addDao.get("McTESTER"));
        boolean empty = addressList.isEmpty();

        //test to see if address made it to list
        Assert.assertEquals(false, empty);

        //pull address from list
        Address getAddress = addDao.get("McTESTER");
        
        //pulled address should have these matching properties
        Assert.assertEquals("TEST", getAddress.getFirstName());
        Assert.assertEquals("McTESTER", getAddress.getLastName());
        Assert.assertEquals("1337 TEST STREET", getAddress.getStreetAddress());
        Assert.assertEquals("TESTVILLE", getAddress.getCity());
        Assert.assertEquals("TEST VIRGINIA", getAddress.getState());
        Assert.assertEquals("TEST BERLIN", getAddress.getCountry());
        Assert.assertEquals("29183", getAddress.getZip());
        Assert.assertEquals("Apt. 78A", getAddress.getSecAdd());

        //update address
        getAddress.setFirstName("CHAMP");
        addDao.update(getAddress);
        //address first name should now be "CHAMP"
        Assert.assertEquals("CHAMP", getAddress.getFirstName());

        //delete address
        addDao.delete(getAddress);
        Address delAddress = new Address();
        try {
            delAddress = addDao.get("McTESTER");
        } catch (Exception ex){
            System.out.println("EMPTY");
        } 
        //address should no longer be found
        Assert.assertEquals(null, delAddress);
        }

    }