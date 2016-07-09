/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.mycompany.addressbook.App;
import com.thesoftwareguild.interfaces.dao.AddressBookDao;
import com.thesoftwareguild.interfaces.dto.Address;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class AddressBookDaoImplTest {

    public AddressBookDaoImplTest() {
    }

    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    AddressBookDao dao = ctx.getBean("addressDao", AddressBookDao.class);

    @Before
    public void setUp() {

        Address address = new Address();
        address.setFirstName("Adam");
        address.setLastName("Pasternack");
        address.setState("Ohio");
        address.setCity("Westerville");
        address.setStreetName("Spring");
        address.setStreetNumber("593");
        address.setZip("43081");
        dao.create(address);

        Address address2 = new Address();

        address2.setFirstName("Dan");
        address2.setLastName("Decarlo");
        address2.setState("NY");
        address2.setCity("Buffalo");
        address2.setStreetName("Crescent");
        address2.setStreetNumber("241");
        address2.setZip("14214");
        dao.create(address2);

        Address address3 = new Address();

        address3.setFirstName("Matt");
        address3.setLastName("Pasternack");
        address3.setState("OH");
        address3.setCity("Columbus");
        address3.setStreetName("Como");
        address3.setStreetNumber("235");
        address3.setZip("43015");
        dao.create(address3);

    }

    @After
    public void tearDown() {

        try {
            try (PrintWriter out = new PrintWriter(new FileWriter("addresses.txt"))) {
                out.flush();
            }

        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void createTest() {

        Address address = dao.get(2);
        String result = address.getFirstName();
        Assert.assertEquals("Dan", result);

        address = dao.get(2);
        result = address.getLastName();
        Assert.assertEquals("Decarlo", result);

        address = dao.get(2);
        result = address.getState();
        Assert.assertEquals("NY", result);

        address = dao.get(2);
        result = address.getCity();
        Assert.assertEquals("Buffalo", result);

        address = dao.get(2);
        result = address.getStreetName();
        Assert.assertEquals("Crescent", result);

        address = dao.get(2);
        result = address.getStreetNumber();
        Assert.assertEquals("241", result);

        address = dao.get(2);
        result = address.getZip();
        Assert.assertEquals("14214", result);

    }

    @Test
    public void getTest() {

        Address address = dao.get(1);
        String result = address.getZip();

        Assert.assertEquals("43081", result);

        address = dao.get(3);
        result = address.getFirstName();

        Assert.assertEquals("Matt", result);

    }

    @Test
    public void updateTest() {

        Address newAddress = new Address();

        newAddress.setFirstName("Willy");
        newAddress.setLastName("Wonka");
        newAddress.setState("SweetState");
        newAddress.setCity("ChocoCity");
        newAddress.setStreetName("sweets drive");
        newAddress.setStreetNumber("777");
        newAddress.setZip("tasty");

        Address oldAddress = dao.get(1);

        newAddress.setId(oldAddress.getId());

        dao.update(newAddress);

        Address testAddress = dao.get(1);

        String result = testAddress.getState();
        Assert.assertEquals("SweetState", result);

        result = testAddress.getZip();
        Assert.assertEquals("tasty", result);

        result = testAddress.getCity();
        Assert.assertEquals("ChocoCity", result);

    }

    @Test
    public void deleteTest() {

        dao.delete(3);

        Address result = dao.get(3);

        Assert.assertEquals(null, result);

    }
}
