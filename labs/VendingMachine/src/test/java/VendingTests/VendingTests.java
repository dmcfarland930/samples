/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package VendingTests;

import com.mycompany.dao.ChangeDao;
import com.mycompany.dao.InventoryDao;
import com.mycompany.dto.Change;
import com.mycompany.dto.Inventory;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class VendingTests {

    public VendingTests() {
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
    ChangeDao changeDao = new ChangeDao();

    @Test
    public void testCreate() {

        //declare a new item
        Inventory newItem = new Inventory();
        List<Inventory> itemList = new ArrayList();

        //set item properties
        newItem.setItemName("TEST ITEM");
        newItem.setCost(.85f);
        newItem.setStock(5);

        //create item with above properties
        invDao.create(newItem);
        int id = newItem.getInvId();

        //add the item to a list
        itemList.add(invDao.get(id));
        boolean empty = itemList.isEmpty();

        //test to see if item made it to list
        Assert.assertEquals(false, empty);

        //pull item from list
        Inventory getItem = invDao.get(id);

        //pulled item should have these matching properties
        Assert.assertEquals("TEST ITEM", getItem.getItemName());
        Assert.assertEquals(.85f, getItem.getCost(), .01);
        Assert.assertEquals(5, getItem.getStock());

        //update item
        getItem.setItemName("TEST ITEM WITH ALMONDS");
        invDao.update(getItem);
        //item neam should now be "TEST ITEM WITH ALMONDS"
        Assert.assertEquals("TEST ITEM WITH ALMONDS", getItem.getItemName());

        //delete item
        invDao.delete(getItem);
        Inventory delItem = new Inventory();
        try {
            delItem = invDao.get(1001);
        } catch (Exception ex) {
            System.out.println("EMPTY");
        }
        //item should no longer be found
        Assert.assertEquals(null, delItem);
    }

    public void testCreate2() {

        //declare a new item
        Change newChange = new Change();
        List<Change> changeList = new ArrayList();

        //set item properties
        newChange.setChangeAmount(.50f);
        newChange.setEnteredAmount(1.50f);
        newChange.setSpentAmount(1.00f);
        

//        //create item with above properties
//        invDao.create(newItem);
//        int id = newItem.getInvId();
//
//        //add the item to a list
//        itemList.add(invDao.get(id));
//        boolean empty = itemList.isEmpty();
//
//        //test to see if item made it to list
//        Assert.assertEquals(false, empty);
//
//        //pull item from list
//        Inventory getItem = invDao.get(id);
//
//        //pulled item should have these matching properties
//        Assert.assertEquals("TEST ITEM", getItem.getItemName());
//        Assert.assertEquals(.85f, getItem.getCost(), .01);
//        Assert.assertEquals(5, getItem.getStock());
//
//        //update item
//        getItem.setItemName("TEST ITEM WITH ALMONDS");
//        invDao.update(getItem);
//        //item neam should now be "TEST ITEM WITH ALMONDS"
//        Assert.assertEquals("TEST ITEM WITH ALMONDS", getItem.getItemName());
//
//        //delete item
//        invDao.delete(getItem);
//        Inventory delItem = new Inventory();
//        try {
//            delItem = invDao.get(1001);
//        } catch (Exception ex) {
//            System.out.println("EMPTY");
//        }
//        //item should no longer be found
//        Assert.assertEquals(null, delItem);
    }
}
