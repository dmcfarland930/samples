/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */

import com.mycompany.interestcalculatorweb.InterestCalculator;
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
    public void displayResults(){
        
        InterestCalculator ic = new InterestCalculator();
        
        ic.run(100, 10, 5, "yearly");
    }
}
