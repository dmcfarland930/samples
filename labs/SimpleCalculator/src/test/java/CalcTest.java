/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */

import com.mycompany.simplecalculator.CalculatorInterface;
import com.mycompany.simplecalculator.SimpleCalculator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class CalcTest {

    public CalcTest() {
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
    public void CalcTest1() {

        SimpleCalculator sc = new SimpleCalculator();
        //Test to see if result is equal to the sum of operands
        double result = sc.addOperands(10, 21);

        Assert.assertEquals(31, result, 0.01);

    }

    @Test
    public void CalcTest2() {

        SimpleCalculator sc = new SimpleCalculator();
        //Test to see if result subtracts from operand one properly
        double result = sc.subtractOperands(21, 10);

        Assert.assertEquals(11, result, 0.01);

    }

    @Test
    public void CalcTest3() {

        SimpleCalculator sc = new SimpleCalculator();
        //Test to see if result is equal operand one multiplied by operand two
        double result = sc.multiplyOperands(10, 10);

        Assert.assertEquals(100, result, 0.01);

    }

    @Test
    public void CalcTest4() {

        SimpleCalculator sc = new SimpleCalculator();
        //Test to see if result is equal to operand one divided by operand two
        double result = sc.divideOperands(100, 2);

        Assert.assertEquals(50, result, 0.01);

    }
    
    @Test
    public void CalcTest5(){
        
        double dub = 0;
        CalculatorInterface ci = new CalculatorInterface();
        //test to see if String returns a valid entry
        dub = ci.navigateUserInput("add");
        
        Assert.assertEquals(7, dub);
        
    }
}
