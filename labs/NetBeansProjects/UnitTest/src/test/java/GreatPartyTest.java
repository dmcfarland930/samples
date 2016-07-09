/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class GreatPartyTest {

    public GreatPartyTest() {
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
    public void testGreatParty() {

        GreatParty gp = new GreatParty();
        //If 30 cigars and it's not the weekend, bad party
        boolean result = gp.GreatParty(30, false);
        
        Assert.assertEquals(false, result);
    
    }

    @Test
    public void testGreatParty2() {

        GreatParty gp = new GreatParty();
        //If 50 cigars and it's not the weekend, good party
        boolean result = gp.GreatParty(50, false);
    
        Assert.assertEquals(true, result);
        
    }

    @Test
    public void testGreatParty3() {

        GreatParty gp = new GreatParty();
        //If 70 cigars and it is the weekend, good party
        boolean result = gp.GreatParty(70, true);
        
        Assert.assertEquals(true, result);
        
    }

}
