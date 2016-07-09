/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */

import com.mycompany.factorizerweb.Factorizer;
import java.util.List;
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
    public void fillList() {

        Factorizer fZ = new Factorizer();

        int number = 5;
        fZ.run(number);

        List<String> results = fZ.getResults();

        for (String str : results) {

            System.out.println(str);
        }

        if (fZ.getPrimeCount() == 1) {
            System.out.println(number + " is a prime number.");
        } else {
            System.out.println(number + " is not a prime number.");
        }

    }
}
