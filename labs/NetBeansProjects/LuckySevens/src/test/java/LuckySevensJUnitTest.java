/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */

import com.mycompany.luckysevens.LuckySevens;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class LuckySevensJUnitTest {

    public LuckySevensJUnitTest() {
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
    public void testLuckySevens() {

        LuckySevens ls = new LuckySevens();

        double result = ls.manipulateWinnings(7);

        Assert.assertEquals(result, 104, 0);

        double result2 = ls.manipulateWinnings(4);

        Assert.assertEquals(result2, 99, 0);

    }
}
