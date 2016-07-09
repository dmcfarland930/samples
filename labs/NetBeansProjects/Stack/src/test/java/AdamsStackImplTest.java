/*
 * Stack Tests By Adam Tegtmeier
 */

import com.mycompany.stack.Stack;
import com.mycompany.stack.StackArrayImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Adam Tegtmeier
 */
public class AdamsStackImplTest {

    // Stack<String> instance;
    public AdamsStackImplTest() {
    }

    @Before
    public void setUp() {
        //   instance = new StackArrayImpl();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testPushAndPopA() {

        Stack<String> instance = new StackArrayImpl();
        String elementOne = "Bill";
        String elementTwo = "Steve";
        String elementThree = "Tim";
        String elementFour = "Dave";

        //Stack<String> instance = new StackArrayImpl();
        int emptySize = instance.size();
        assertEquals(emptySize, 0);
        assertEquals(instance.isEmpty(), true);

        instance.push(elementOne);
        instance.push(elementTwo);
        instance.push(elementThree);
        instance.push(elementFour);

        int fourSize = instance.size();
        assertEquals(fourSize, 4);
        assertEquals(instance.isEmpty(), false);

        String resultFour = instance.pop();
        String resultThree = instance.pop();
        String resultTwo = instance.pop();
        String resultOne = instance.pop();

        assertEquals(elementOne, resultOne);
        assertEquals(elementTwo, resultTwo);
        assertEquals(elementThree, resultThree);
        assertEquals(elementFour, resultFour);

        int zeroSize = instance.size();
        assertEquals(zeroSize, 0);
        assertEquals(instance.isEmpty(), true);

        String shouldBeNull = instance.pop();

        assertEquals(shouldBeNull, null);

        assertEquals(instance.size(), 0);
        assertEquals(instance.isEmpty(), true);

    }

    @Test
    public void testPushAndPopB() {

        System.out.println("push");
        Integer elementOne = 1;
        Integer elementTwo = 2;
        Integer elementThree = 3;
        Integer elementFour = 4;

        Stack<Integer> instance = new StackArrayImpl();

        int emptySize = instance.size();
        assertEquals(emptySize, 0);
        assertEquals(instance.isEmpty(), true);

        instance.push(elementOne);
        instance.push(elementTwo);
        instance.push(elementThree);
        instance.push(elementFour);

        int fourSize = instance.size();
        assertEquals(fourSize, 4);
        assertEquals(instance.isEmpty(), false);

        Integer resultFour = instance.pop();
        Integer resultThree = instance.pop();
        Integer resultTwo = instance.pop();
        Integer resultOne = instance.pop();

        assertEquals(elementOne, resultOne);
        assertEquals(elementTwo, resultTwo);
        assertEquals(elementThree, resultThree);
        assertEquals(elementFour, resultFour);

        int zeroSize = instance.size();
        assertEquals(zeroSize, 0);
        assertEquals(instance.isEmpty(), true);

        Integer shouldBeNull = instance.pop();

        assertEquals(shouldBeNull, null);

        assertEquals(instance.size(), 0);
        assertEquals(instance.isEmpty(), true);

    }

    /**
     * Test of push method, of class StackArrayImpl.
     */
    @Test
    public void testPushAndPopC() {

        Stack<String> instance = new StackArrayImpl();
        System.out.println("pushB - All Nulls");
        String elementOne = null;
        String elementTwo = null;
        String elementThree = null;
        String elementFour = null;

        //Stack<String> instance = new StackArrayImpl();
        int emptySize = instance.size();
        assertEquals(emptySize, 0);
        assertEquals(instance.isEmpty(), true);

        instance.push(elementOne);
        instance.push(elementTwo);
        instance.push(elementThree);
        instance.push(elementFour);

        int fourSize = instance.size();
        assertEquals(fourSize, 0);
        assertEquals(instance.isEmpty(), true);

        String resultFour = instance.pop();
        String resultThree = instance.pop();
        String resultTwo = instance.pop();
        String resultOne = instance.pop();

        assertEquals(elementOne, resultOne);
        assertEquals(elementTwo, resultTwo);
        assertEquals(elementThree, resultThree);
        assertEquals(elementFour, resultFour);

        int zeroSize = instance.size();
        assertEquals(zeroSize, 0);
        assertEquals(instance.isEmpty(), true);

        String shouldBeNull = instance.pop();

        assertEquals(shouldBeNull, null);

        assertEquals(instance.size(), 0);
        assertEquals(instance.isEmpty(), true);

    }

    /**
     * Test of pop method, of class StackArrayImpl.
     */
    @Test
    public void testPushAndPopD() {
        Stack<String> instance = new StackArrayImpl();
        System.out.println("popC - Out of Order");

        String elementOne = "Bill";
        String elementTwo = "Steve";
        String elementThree = "Tim";
        String elementFour = "Dave";

        //Stack<String> instance = new StackArrayImpl();
        int emptySize = instance.size();
        assertEquals(emptySize, 0);
        assertEquals(instance.isEmpty(), true);

        instance.push(elementOne);
        instance.push(elementTwo);
        instance.push(elementThree);

        int fourSize = instance.size();
        assertEquals(fourSize, 3);
        assertEquals(instance.isEmpty(), false);

        String resultThree = instance.pop();
        String resultTwo = instance.pop();

        instance.push(elementFour);
        String resultFour = instance.pop();

        String resultOne = instance.pop();

        assertEquals(elementOne, resultOne);
        assertEquals(elementTwo, resultTwo);
        assertEquals(elementThree, resultThree);
        assertEquals(elementFour, resultFour);

        int zeroSize = instance.size();
        assertEquals(zeroSize, 0);
        assertEquals(instance.isEmpty(), true);

        String shouldBeNull = instance.pop();

        assertEquals(shouldBeNull, null);

        assertEquals(instance.size(), 0);
        assertEquals(instance.isEmpty(), true);

    }

    /**
     * Test of isEmpty method, of class StackArrayImpl.
     */
    @Test
    public void testPushAndPopPerformance() {
        Stack<String> instance = new StackArrayImpl();

        System.out.println("Test Push And Pop Using Large Numbers");

        int sizeTest = 50;

        List<String> elementList = new ArrayList();
        String tempString = "";

        for (int i = 0; i < sizeTest; i++) {

            tempString = tempString + "z";
            elementList.add(tempString);

        }

        int emptySize = instance.size();
        assertEquals(emptySize, 0);
        assertEquals(instance.isEmpty(), true);

        for (String string : elementList) {
            instance.push(string);
        }

        int fourSize = instance.size();
        assertEquals(fourSize, sizeTest);
        assertEquals(instance.isEmpty(), false);

        for (int i = sizeTest; i > 0; i--) {

            String result = instance.pop();

            String expected = elementList.get(i - 1);

            assertEquals(expected, result);

        }

        int zeroSize = instance.size();
        assertEquals(zeroSize, 0);
        assertEquals(instance.isEmpty(), true);

        String shouldBeNull = instance.pop();

        assertEquals(shouldBeNull, null);

        assertEquals(instance.size(), 0);
        assertEquals(instance.isEmpty(), true);

    }

    /**
     * Test of isEmpty method, of class StackArrayImpl.
     */
    @Test
    public void testBigStack() {

        Stack<String> instance = new StackArrayImpl();
        System.out.println("isEmpty");

        int sizeTest = 500;

        List<String> elementList = new ArrayList();
        String tempString = "";

        for (int i = 0; i < sizeTest; i++) {

            tempString = tempString + "z";
            elementList.add(tempString);

        }

        int emptySize = instance.size();
        assertEquals(emptySize, 0);
        assertEquals(instance.isEmpty(), true);

        for (String string : elementList) {
            instance.push(string);
        }

        int fourSize = instance.size();
        assertEquals(fourSize, sizeTest);
        assertEquals(instance.isEmpty(), false);

        for (int i = sizeTest; i > 0; i--) {

            String result = instance.pop();

            String expected = elementList.get(i - 1);

            assertEquals(expected, result);

        }

        int zeroSize = instance.size();
        assertEquals(zeroSize, 0);
        assertEquals(instance.isEmpty(), true);

        String shouldBeNull = instance.pop();

        assertEquals(shouldBeNull, null);

        assertEquals(instance.size(), 0);
        assertEquals(instance.isEmpty(), true);

    }

    /**
     * Test of isEmpty method, of class StackArrayImpl.
     */
    @Test
    public void testBiggerStack() {

        Stack<String> instance = new StackArrayImpl();
        System.out.println("isEmpty");

        int sizeTest = 5000;

        List<String> elementList = new ArrayList();
        String tempString = "";

        for (int i = 0; i < sizeTest; i++) {

            tempString = tempString + "z";
            elementList.add(tempString);

        }

        int emptySize = instance.size();
        assertEquals(emptySize, 0);
        assertEquals(instance.isEmpty(), true);

        for (String string : elementList) {
            instance.push(string);
        }

        int fourSize = instance.size();
        assertEquals(fourSize, sizeTest);
        assertEquals(instance.isEmpty(), false);

        for (int i = sizeTest; i > 0; i--) {

            String result = instance.pop();

            String expected = elementList.get(i - 1);

            assertEquals(expected, result);

        }

        int zeroSize = instance.size();
        assertEquals(zeroSize, 0);
        assertEquals(instance.isEmpty(), true);

        String shouldBeNull = instance.pop();

        assertEquals(shouldBeNull, null);

        assertEquals(instance.size(), 0);
        assertEquals(instance.isEmpty(), true);

    }

    /**
     * Test of isEmpty method, of class StackArrayImpl.
     */
    @Test
    public void testMassiveStack() {
        Stack<String> instance = new StackArrayImpl();

        System.out.println("isEmpty");

        int sizeTest = 10000;

        List<String> elementList = new ArrayList();
        String tempString = "";

        for (int i = 0; i < sizeTest; i++) {

            tempString = tempString + "z";
            elementList.add(tempString);

        }

        int emptySize = instance.size();
        assertEquals(emptySize, 0);
        assertEquals(instance.isEmpty(), true);

        for (String string : elementList) {
            instance.push(string);
        }

        int fourSize = instance.size();
        assertEquals(fourSize, sizeTest);
        assertEquals(instance.isEmpty(), false);

        for (int i = sizeTest; i > 0; i--) {

            String result = instance.pop();

            String expected = elementList.get(i - 1);

            assertEquals(expected, result);

        }

        int zeroSize = instance.size();
        assertEquals(zeroSize, 0);
        assertEquals(instance.isEmpty(), true);

        String shouldBeNull = instance.pop();

        assertEquals(shouldBeNull, null);

        assertEquals(instance.size(), 0);
        assertEquals(instance.isEmpty(), true);

    }

    /**
     * Test of isEmpty method, of class StackArrayImpl.
     */
    @Test
    public void testMassiveStackOfNothing() {

        Stack<String> instance = new StackArrayImpl();
        System.out.println("isEmpty");

        int sizeTest = 10000;
        int expectedSize = 0;

        String tempString = null;
        String expected = null;

        int emptySize = instance.size();
        assertEquals(emptySize, 0);
        assertEquals(instance.isEmpty(), true);

        for (int i = 0; i < sizeTest; i++) {
            instance.push(tempString);
        }

        int fourSize = instance.size();
        assertEquals(fourSize, expectedSize);
        assertEquals(instance.isEmpty(), true);

        for (int i = sizeTest; i > 0; i--) {

            String result = instance.pop();

            assertEquals(expected, result);

        }

        int zeroSize = instance.size();
        assertEquals(zeroSize, 0);
        assertEquals(instance.isEmpty(), true);

        String shouldBeNull = instance.pop();

        assertEquals(shouldBeNull, null);

        assertEquals(instance.size(), 0);
        assertEquals(instance.isEmpty(), true);

    }

}
