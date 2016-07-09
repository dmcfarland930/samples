/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */

import com.mycompany.controller.ConsoleIO;
import com.mycompany.dao.InventoryDao;
import com.mycompany.dto.Coat;
import com.mycompany.dto.Pants;
import com.mycompany.dto.Product;
import com.mycompany.dto.Shirt;
import com.mycompany.dto.Shoe;
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
public class ProductInventoryTest {

    public ProductInventoryTest() {
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
    InventoryDao invDao = new InventoryDao();
    ConsoleIO console = new ConsoleIO();

    @Test
    public void testCreate() {

        //declare a new Product
        Product newShirt = new Shirt();
        List<Product> productList = new ArrayList();

        //set test properties
        newShirt.setProductType("Shirt");
        newShirt.setProductName("TEST SHIRT");
        String size = newShirt.setSizeInput();
        newShirt.setSize(size);
        newShirt.setPrice(20);
        newShirt.setStock(10);
        newShirt.setProductId(0);
        //create Product with above properties
        invDao.create(newShirt);

        //add the Product to a list
        productList.add(invDao.get(0));
        boolean empty = productList.isEmpty();

        //test to see if Product made it to list
        Assert.assertEquals(false, empty);

        //pull Product from list
        Product getProduct = invDao.get(0);

        //pulled Product should have these matching properties
        Assert.assertEquals("Shirt", getProduct.getProductType());
        Assert.assertEquals("TEST SHIRT", getProduct.getProductName());
        Assert.assertEquals(size, getProduct.getSize());
        Assert.assertEquals(20, getProduct.getPrice(), 0);
        Assert.assertEquals(10, getProduct.getStock(), 0);

        //update Product
        getProduct.setProductName("TEST SWEATER");
        invDao.update(getProduct);
        //Product title should now be "TEST SWEATER"
        Assert.assertEquals("TEST SWEATER", getProduct.getProductName());

        //delete Product
        invDao.delete(getProduct);
    }

    @Test
    public void testCreate2() {

        //declare a new Product
        Product newShirt = new Pants();
        List<Product> productList = new ArrayList();

        //set test properties
        newShirt.setProductType("Pants");
        newShirt.setProductName("TEST PANTS");
        String size = newShirt.setSizeInput();
        newShirt.setSize(size);
        newShirt.setPrice(20);
        newShirt.setStock(10);
        newShirt.setProductId(0);
        //create Product with above properties
        invDao.create(newShirt);

        //add the Product to a list
        productList.add(invDao.get(0));
        boolean empty = productList.isEmpty();

        //test to see if Product made it to list
        Assert.assertEquals(false, empty);

        //pull Product from list
        Product getProduct = invDao.get(0);

        //pulled Product should have these matching properties
        Assert.assertEquals("Pants", getProduct.getProductType());
        Assert.assertEquals("TEST PANTS", getProduct.getProductName());
        Assert.assertEquals(size, getProduct.getSize());
        Assert.assertEquals(20, getProduct.getPrice(), 0);
        Assert.assertEquals(10, getProduct.getStock(), 0);

        //update Product
        getProduct.setProductName("TEST KHAKIS");
        invDao.update(getProduct);
        //Product title should now be "TEST KHAKIS"
        Assert.assertEquals("TEST KHAKIS", getProduct.getProductName());

        //delete Product
        invDao.delete(getProduct);
    }

    @Test
    public void testCreate3() {

        //declare a new Product
        Product newShirt = new Coat();
        List<Product> productList = new ArrayList();

        //set test properties
        newShirt.setProductType("Coat");
        newShirt.setProductName("TEST COAT");
        String size = newShirt.setSizeInput();
        newShirt.setSize(size);
        newShirt.setPrice(20);
        newShirt.setStock(10);
        newShirt.setProductId(0);
        //create Product with above properties
        invDao.create(newShirt);

        //add the Product to a list
        productList.add(invDao.get(0));
        boolean empty = productList.isEmpty();

        //test to see if Product made it to list
        Assert.assertEquals(false, empty);

        //pull Product from list
        Product getProduct = invDao.get(0);

        //pulled Product should have these matching properties
        Assert.assertEquals("Coat", getProduct.getProductType());
        Assert.assertEquals("TEST COAT", getProduct.getProductName());
        Assert.assertEquals(size, getProduct.getSize());
        Assert.assertEquals(20, getProduct.getPrice(), 0);
        Assert.assertEquals(10, getProduct.getStock(), 0);

        //update Product
        getProduct.setProductName("TEST JACKET");
        invDao.update(getProduct);
        //Product title should now be "TEST JACKET"
        Assert.assertEquals("TEST JACKET", getProduct.getProductName());

        //delete Product
        invDao.delete(getProduct);
    }

    @Test
    public void testCreate4() {

        //declare a new Product
        Product newShirt = new Shoe();
        List<Product> productList = new ArrayList();

        //set test properties
        newShirt.setProductType("Shoe");
        newShirt.setProductName("TEST SHOE");
        String size = newShirt.setSizeInput();
        newShirt.setSize(size);
        newShirt.setPrice(20);
        newShirt.setStock(10);
        newShirt.setProductId(0);
        //create Product with above properties
        invDao.create(newShirt);

        //add the Product to a list
        productList.add(invDao.get(0));
        boolean empty = productList.isEmpty();

        //test to see if Product made it to list
        Assert.assertEquals(false, empty);

        //pull Product from list
        Product getProduct = invDao.get(0);

        //pulled Product should have these matching properties
        Assert.assertEquals("Shoe", getProduct.getProductType());
        Assert.assertEquals("TEST SHOE", getProduct.getProductName());
        Assert.assertEquals(size, getProduct.getSize());
        Assert.assertEquals(20, getProduct.getPrice(), 0);
        Assert.assertEquals(10, getProduct.getStock(), 0);

        //update Product
        getProduct.setProductName("TEST BOOTS");
        invDao.update(getProduct);
        //Product title should now be "TEST 3: REVENGE OF THE QUIZ"
        Assert.assertEquals("TEST BOOTS", getProduct.getProductName());

        //delete Product
        invDao.delete(getProduct);
    }

}
