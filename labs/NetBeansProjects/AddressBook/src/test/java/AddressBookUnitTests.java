/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.thesoftwareguild.interfaces.dao.AddressBookDao;
import com.thesoftwareguild.interfaces.dto.Address;
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
public class AddressBookUnitTests {
    
    //AddressDao addressDaoOriginal = new AddressDaoImpl();
    //AddressDao addressDaoLambdas = new AddressDaoLambaImpl();
    AddressBookDao addressDao;
    
    String firstName = "Bruce";
    String lastName = "Wayne";
    String streetNum = "12321";
    String streetName = "Testing Street";
    String city = "Gotham";
    String state = "New York";
    String zip = "32123";
    String country = "USA";
    
    public AddressBookUnitTests() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        addressDao = ctx.getBean("addressDao", AddressBookDao.class);
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
    
    @Test
    public void crudAddressOrignalTest() {
        
        //Create a new Address using the original implementation
        Address newAddress = new Address();
        newAddress.setFirstName(firstName);
        newAddress.setLastName(lastName);
        newAddress.setStreetNumber(streetNum);
        newAddress.setStreetName(streetName);
        newAddress.setCity(city);
        newAddress.setState(state);
        newAddress.setZip(zip);
//        newAddress.setCountry(country);
        
        Address originalAddress = addressDao.create(newAddress);
        Assert.assertNotNull(originalAddress);
        
    //****************************************************
    
    //Check to make sure all Properties were correctly set        
        Assert.assertEquals(originalAddress.getId(), newAddress.getId());
        Assert.assertEquals(firstName, originalAddress.getFirstName());
        Assert.assertEquals(lastName, originalAddress.getLastName());
        Assert.assertEquals(streetNum, originalAddress.getStreetNumber());
        Assert.assertEquals(streetName, originalAddress.getStreetName());
        Assert.assertEquals(city, originalAddress.getCity());
        Assert.assertEquals(state, originalAddress.getState());
        Assert.assertEquals(zip, originalAddress.getZip());
//        Assert.assertEquals(country, originalAddress.getCountry());
        
    //****************************************************
        
        //Was it successfully added to the AddressList        
        List<Address> originalList = addressDao.list();
        int countBeforeAdd = originalList.size();
        int expectedCount = countBeforeAdd + 1;
        originalList.add(originalAddress);
        
        Assert.assertEquals(expectedCount, originalList.size());
        
    //****************************************************
    
        //retrieve the AddressList by last name        
        List<Address> addressListByName = addressDao.searchByLastName(lastName);        
        int nameListSize = addressListByName.size();
        
        Assert.assertNotEquals(0, nameListSize);
        
    //*****************************************************
    
        //retrieve the AddressList by city        
        List<Address> addressListByCity = addressDao.searchByCity(city);        
        int cityListSize = addressListByCity.size();
        
        Assert.assertNotEquals(0, cityListSize);
        
    //*****************************************************
    
        //retrieve the AddressList by zip        
        List<Address> addressListZip = addressDao.searchByZip(zip);        
        int zipListSize = addressListZip.size();
        
        Assert.assertNotEquals(0, zipListSize);
        
    //*****************************************************
    
        //retrieve the Address Map by state
        List<Address> addressListState = addressDao.searchByState(state);        
        int stateListSize = addressListState.size();
        
        Assert.assertNotEquals(0, zipListSize);
    //*****************************************************
    
    }
    
    /*@Test
    public void crudAddressLambdasTest() {
        
        //Create a new Address using the Lambda implementation
        Address newAddress = new Address();
        newAddress.setFirstName(firstName);
        newAddress.setLastName(lastName);
        newAddress.setStreetNumber(streetNum);
        newAddress.setStreetName(streetName);
        newAddress.setCity(city);
        newAddress.setState(state);
        newAddress.setZip(zip);
        newAddress.setCountry(country);
        
        Address lambdaAddress = addressDaoLambdas.create(newAddress);
        
        boolean addressExists = false;
        if (lambdaAddress != null) {
            addressExists = true;
        }
       
        Assert.assertEquals(true, addressExists);
    //***************************************************
        
        //Check to make sure all Properties were correctly set        
        Assert.assertEquals(lambdaAddress.getId(), newAddress.getId());
        Assert.assertEquals(firstName, lambdaAddress.getFirstName());
        Assert.assertEquals(lastName, lambdaAddress.getLastName());
        Assert.assertEquals(streetNum, lambdaAddress.getStreetNumber());
        Assert.assertEquals(streetName, lambdaAddress.getStreetName());
        Assert.assertEquals(city, lambdaAddress.getCity());
        Assert.assertEquals(state, lambdaAddress.getState());
        Assert.assertEquals(zip, lambdaAddress.getZip());
        Assert.assertEquals(country, lambdaAddress.getCountry());
        
    //****************************************************
        
        //Was it successfully added to the AddressList        
        List<Address> lambdaList = addressDaoLambdas.getAddressList();
        int countBeforeAdd = lambdaList.size();
        int expectedCount = countBeforeAdd + 1;
        lambdaList.add(lambdaAddress);
        
        Assert.assertEquals(expectedCount, lambdaList.size());
        
    //****************************************************
    
    //retrieve the AddressList by last name        
        List<Address> addressListByName = addressDaoLambdas.getByName(lastName);        
        int nameListSize = addressListByName.size();
        
        Assert.assertNotEquals(0, nameListSize);
        
    //*****************************************************
    
        //retrieve the AddressList by city        
        List<Address> addressListByCity = addressDaoLambdas.getByCity(city);        
        int cityListSize = addressListByCity.size();
        
        Assert.assertNotEquals(0, cityListSize);
        
    //*****************************************************
    
        //retrieve the AddressList by zip        
        List<Address> addressListZip = addressDaoLambdas.getByZip(zip);        
        int zipListSize = addressListZip.size();
        
        Assert.assertNotEquals(0, zipListSize);
        
    //*****************************************************
    
        //retrieve the Address Map by state
        Map<String, List<Address>>  stateAddressMap = new HashMap();
        stateAddressMap = addressDaoLambdas.getByState(state);
        
        List<Address> cityAddressList = stateAddressMap.get(city);
        
        Assert.assertNotEquals(0, cityAddressList.size());
    //*****************************************************
    
    }*/
}
