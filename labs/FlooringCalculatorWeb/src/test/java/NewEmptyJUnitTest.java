/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */

import com.mycompany.flooringcalculatorweb.FlooringCalculator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author apprentice
 */
public class NewEmptyJUnitTest {
    
    public NewEmptyJUnitTest() {
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
    public void testRun(){
        
        FlooringCalculator fC = new FlooringCalculator();
        fC.run(10, 10, 5, 10, 10);
        
        System.out.println(fC.getCostLabor());
        System.out.println(fC.getTileTotal());
        System.out.println(fC.getFinalTotal());
    }
}
