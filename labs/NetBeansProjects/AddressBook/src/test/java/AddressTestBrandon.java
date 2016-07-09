
import com.thesoftwareguild.interfaces.dao.AddressBookDao;
import com.thesoftwareguild.interfaces.dto.Address;
import java.text.ParseException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AddressTestBrandon {
    @Autowired
    private AddressBookDao dao;
    public AddressTestBrandon() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        dao = ctx.getBean("addressDao", AddressBookDao.class);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
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
    public void AddressTest() throws ParseException  {
        Address address = new Address();
        address.setFirstName("Brandon");
        address.setLastName("Anderson");
        Address address1 = dao.create(address);
        address = new Address();
        address.setFirstName("Tom");
        address.setLastName("Haverford");
        Address address2 = dao.create(address);
        List<Address> result = dao.list();
        Assert.assertEquals(true, result.contains(address1));
        Assert.assertEquals(true, result.contains(address2));
        dao.delete(address1.getId());
        dao.delete(address2.getId());
    }
    
    @Test
    public void searchByStateTest() {
        Address address = new Address();
        address.setState("Testylvania");
        address=dao.create(address);
        Assert.assertEquals("Testylvania", dao.searchByState("Testylvania").get(0).getState());
        dao.delete(address.getId());
    }
    
    @Test
    public void searchByCityTest() {
        Address address = new Address();
        address.setCity("Testtown");
        address=dao.create(address);
        Assert.assertEquals("Testtown", dao.searchByCity("Testtown").get(0).getCity());
        dao.delete(address.getId());
    }
    
    @Test
    public void searchByZipTest() {
        Address address = new Address();
        address.setCity("12345");
        address=dao.create(address);
        Assert.assertEquals("12345", dao.searchByZip("T12345").get(0).getZip());
        dao.delete(address.getId());
    }
    
    @Test
    public void searchByLastNameTest() {
        Address address = new Address();
        address.setLastName("Tester");
        address=dao.create(address);
        Assert.assertEquals("Tester", dao.searchByLastName("Tester").get(0).getZip());
        dao.delete(address.getId());
    }
    
}
