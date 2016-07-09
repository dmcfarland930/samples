/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */

import com.mycompany.stack.Stack;
import com.mycompany.stack.StackArrayImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author apprentice
 */
public class StackTest {

    public StackTest() {
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
    public void TestPushPop() {

        Stack<String> stackTest = new StackArrayImpl<>();

        stackTest.push("JIM");
        stackTest.push("JAMES");

        String firstOut = stackTest.pop();
        String firstIn = stackTest.pop();

        Assert.assertEquals("JAMES", firstOut);
        Assert.assertEquals("JIM", firstIn);

    }

    @Test
    public void isEmptyTest() {

        Stack<String> stackTest = new StackArrayImpl<>();

        stackTest.push("JIM");

        //stack should contain JIM
        //array size is 1, top variable is 0
        boolean empty = stackTest.isEmpty();

        //because stack contains Jim, return false
        Assert.assertEquals(empty, false);

    }

    @Test
    public void isEmptyTest2() {

        Stack<String> stackTest = new StackArrayImpl<>();

        //stack has not pushed any objects
        boolean empty = stackTest.isEmpty();

        //because stack contains no objects return true
        Assert.assertEquals(empty, true);

    }

    @Test
    public void sizeTest() {

        Stack<String> stackTest = new StackArrayImpl<>();

        stackTest.push("JIM");
        stackTest.push("JAMES");

        //stack test contains two objects: JIM, JAMES
        //.size should count two objects
        int size = stackTest.size();

        Assert.assertEquals(size, 2, 0);
    }

    @Test
    public void sizeTest2() {

        Stack<String> stackTest = new StackArrayImpl<>();

        stackTest.push("JIM");
        stackTest.push("JAMES");
        stackTest.push("KANYE");
        stackTest.push("YEEZUS");


        //stack test contains four objects: JIM, JAMES, KANYE, YEEZUS
        //.size should count four objects
        int size = stackTest.size();

        Assert.assertEquals(size, 4, 0);
    }
}
