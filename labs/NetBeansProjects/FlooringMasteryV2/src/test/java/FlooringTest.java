/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */

import com.mycompany.aop.Audit;
import com.mycompany.dao.OrderDao;
import com.mycompany.dao.TaxesDao;
import com.mycompany.dao.TaxesXmlDao;
import com.mycompany.data.FlooringData;
import com.mycompany.dto.Order;
import com.mycompany.dto.Taxes;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author apprentice
 */
public class FlooringTest {

    public FlooringTest() {
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
    public void OrderDaoTest() {

        Order testOrder = new Order();
        List<Order> testList;
        FlooringData fd = new FlooringData();
        OrderDao testDao = new OrderDao(fd);
        boolean isEmpty;

        //set testOrder properties
        testOrder.setCustomerName("TEST CUSTOMER");
        testOrder.setProductType("TEST PRODUCT");
        testOrder.setState("TEST VIRGINIA");
        testOrder.setOrderNumber(1000);
        testOrder.setArea(100);
        testOrder.setCostPerSqFt(10);
        testOrder.setLaborCostPerSqFt(10);
        testOrder.setTaxRate(5);
        testOrder.setMaterialCost(100);
        testOrder.setTotalLaborCost(100);
        testOrder.setTax(50);
        testOrder.setOrderTotal(250);
        testOrder.setOrderDate("051920");

        //encode test order into a txt file, then decode into testList
        testDao.create(testOrder, ("051920"));
        testList = fd.orderDecode("051920");

        /* Test if testOrder was properly encoded. testList should contain test
        order*/
        isEmpty = testList.isEmpty();
        Assert.assertEquals(false, isEmpty);
        int orderId = testOrder.getOrderNumber();

        //Now test to see if testDao returns set propeties./
        testOrder = testDao.get(orderId);
        Assert.assertEquals("TEST CUSTOMER", testOrder.getCustomerName());
        Assert.assertEquals("TEST PRODUCT", testOrder.getProductType());
        Assert.assertEquals("TEST VIRGINIA", testOrder.getState());
        Assert.assertEquals(100, testOrder.getArea(), 0);
        Assert.assertEquals(10, testOrder.getCostPerSqFt(), 0);
        Assert.assertEquals(10, testOrder.getLaborCostPerSqFt(), 0);
        Assert.assertEquals(5, testOrder.getTaxRate(), 0);
        Assert.assertEquals(100, testOrder.getMaterialCost(), 0);
        Assert.assertEquals(100, testOrder.getTotalLaborCost(), 0);
        Assert.assertEquals(50, testOrder.getTax(), 0);
        Assert.assertEquals(250, testOrder.getOrderTotal(), 0);
        Assert.assertEquals("051920", testOrder.getOrderDate());

        //Set tetsOrder's state to "OH"
        testOrder.setState("OH");

        //now update the txt file
        testDao.update(testOrder, "051920", false);

        //check if testOrder was properly updated
        Assert.assertEquals("OH", testOrder.getState());

        //now delete testOrder from text file
        testDao.delete(testOrder, "051920");

        //check if testOrder was removed
        testList = fd.orderDecode("051920");

        // Test if testOrder was properly deleted. testList should be empty
        isEmpty = testList.isEmpty();
        Assert.assertEquals(true, isEmpty);

    }

//    @Test
//    public void ProductDaoTest1() {
//
//        Product testProduct = new Product();
//        List<Product> testList;
//        ProductDao testDao = new ProductDao();
//        FlooringData fd = new FlooringData();
//        boolean found = false;
//
//        //set testProduct properties
//        testProduct.setProductType("TEST PRODUCT");
//        testProduct.setCostPerSqFt(3);
//        testProduct.setLaborCostPerSqFt(4);
//
//        //encode test product into a txt file, then decode into testList
//        testDao.create(testProduct);
//        testList = fd.productDecode();
//
//        /* Test if testProduct was properly encoded. testList should contain test
//        product*/
//        for (Product productOnFile : testList) {
//
//            found = productOnFile.getProductType().equals(testProduct.getProductType());
//
//        }
//
//        Assert.assertEquals(true, found);
//
//        //Now test to see if testProduct returns set propeties.
//        testProduct = testDao.get("TEST PRODUCT");
//        Assert.assertEquals("TEST PRODUCT", testProduct.getProductType());
//        Assert.assertEquals(3, testProduct.getCostPerSqFt(), 0);
//        Assert.assertEquals(4, testProduct.getLaborCostPerSqFt(), 0);
//
//        //Set testProduct's new type to "NEW PRODUCT"
//        testProduct.setProductType("NEW PRODUCT");
//
//        //now update the txt file
//        testDao.update(testProduct);
//
//        //check if testProduct was properly updated
//        Assert.assertEquals("NEW PRODUCT", testProduct.getProductType());
//
//        //now delete testProduct from text file
//        testDao.delete(testProduct);
//
//        //check if testProduct was removed
//        testList = fd.productDecode();
//
//        /* Test if testProduct was properly deleted. testList should no longer
//        contain a product by the name of "NEW PRODUCT"*/
//        for (Product productOnFile : testList) {
//
//            found = productOnFile.getProductType().equals(testProduct.getProductType());
//
//        }
//
//        fd.productEncode(testList);
//        Assert.assertEquals(false, found);
//
//    }
//
//    @Test
//    public void ProductDaoTest2() {
//        Product testProduct = new Product();
//        ProductDao testDao = new ProductDao();
//
//        //set testProduct properties
//        testProduct.setProductType("TEST PRODUCT");
//        testProduct.setCostPerSqFt(3);
//        testProduct.setLaborCostPerSqFt(4);
//
//        //encode test product into a txt file, then decode into testList
//        testDao.create(testProduct);
//
//        double costPerSqFt = testDao.getCostPerSqFt("TEST PRODUCT");
//
//        Assert.assertEquals(3, costPerSqFt, 0);
//
//    }
//
//    @Test
//    public void ProductDaoTest3() {
//        Product testProduct = new Product();
//        ProductDao testDao = new ProductDao();
//
//        //set testProduct properties
//        testProduct.setProductType("TEST PRODUCT");
//        testProduct.setCostPerSqFt(3);
//        testProduct.setLaborCostPerSqFt(4);
//
//        //encode test product into a txt file, then decode into testList
//        testDao.create(testProduct);
//
//        double laborCost = testDao.getLaborCostPerSqFt("TEST PRODUCT");
//
//        Assert.assertEquals(4, laborCost, 0);
//
//    }
//
//    @Test
//    public void ProductDaoTest4() {
//        Product testProduct = new Product();
//        ProductDao testDao = new ProductDao();
//
//        //set testProduct properties
//        testProduct.setProductType("TEST PRODUCT");
//        testProduct.setCostPerSqFt(3);
//        testProduct.setLaborCostPerSqFt(4);
//
//        //encode test product into a txt file, then decode into testList
//        testDao.create(testProduct);
//
//        double totalCostPerSqFt = testDao.calculateTotalCostPerSqFt(50, "TEST PRODUCT");
//
//        Assert.assertEquals(150, totalCostPerSqFt, 0);
//
//    }
//
//    @Test
//    public void ProductDaoTest5() {
//        Product testProduct = new Product();
//        ProductDao testDao = new ProductDao();
//
//        //set testProduct properties
//        testProduct.setProductType("TEST PRODUCT");
//        testProduct.setCostPerSqFt(3);
//        testProduct.setLaborCostPerSqFt(4);
//
//        //encode test product into a txt file, then decode into testList
//        testDao.create(testProduct);
//
//        double totalLaborCost = testDao.calculateTotalLaborCost(50, "TEST PRODUCT");
//
//        Assert.assertEquals(200, totalLaborCost, 0);
//
//    }
    @Test
    public void TaxDaoTest1() {

        Taxes testTax = new Taxes();
        List<Taxes> testList;
        FlooringData fd = new FlooringData();
        TaxesXmlDao tXml = new TaxesXmlDao();
        TaxesDao testDao = new TaxesDao(fd, tXml);
        boolean found = false;

        //set testTax properties
        testTax.setState("TEST VIRGINIA");
        testTax.setTaxRate(6);

        //encode test tax into a txt file, then decode into testList
        testDao.create(testTax);
        testList = fd.taxDecode();

        /* Test if testTax was properly encoded. testList should contain test
        tax*/
        for (Taxes taxOnFile : testList) {

            found = taxOnFile.getState().equals(testTax.getState());

        }

        Assert.assertEquals(true, found);

        //Now test to see if testTax returns set propeties.
        testTax = testDao.get("TEST VIRGINIA");
        Assert.assertEquals("TEST VIRGINIA", testTax.getState());
        Assert.assertEquals(6, testTax.getTaxRate(), 0);

        //Set testTaxe's new type to "NEW STATE"
        testTax.setState("NEW STATE");

        //now update the txt file
        testDao.update(testTax);

        //check if testTax was properly updated
        Assert.assertEquals("NEW STATE", testTax.getState());

        //now delete testTax from text file
        testDao.delete(testTax);

        //check if testTax was removed
        testList = fd.taxDecode();

        /* Test if testTax was properly deleted. testList should no longer
        contain a product by the name of "NEW STATE"*/
        for (Taxes taxOnFile : testList) {

            found = taxOnFile.getState().equals(testTax.getState());

        }

        fd.taxEncode(testList);
        Assert.assertEquals(false, found);

    }

    @Test
    public void AuditTest() {

        Audit newAudit = new Audit();

    }

    public void TaxDaoTest2() {

        FlooringData fd = new FlooringData();
        TaxesXmlDao tXml = new TaxesXmlDao();
        TaxesDao testDao = new TaxesDao(fd, tXml);
        
        double taxTotal = testDao.calculateTaxTotal(100, 100, 20);
        Assert.assertEquals(220, taxTotal, 0);

    }
}
