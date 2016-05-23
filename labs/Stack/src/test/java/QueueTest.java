/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */

import com.mycompany.stack.Queue;
import com.mycompany.stack.QueueArrayImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author apprentice
 */
public class QueueTest {

    public QueueTest() {
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
    public void testEnqueueDQ() {

        Queue<String> stringQueue = new QueueArrayImpl();

        stringQueue.enqueue("KANYE");
        stringQueue.enqueue("YEEZY");
        stringQueue.enqueue("JAY Z");

        String dq = stringQueue.dequeue();
        String dq2 = stringQueue.dequeue();
        String dq3 = stringQueue.dequeue();

        Assert.assertEquals("KANYE", dq);
        Assert.assertEquals("YEEZY", dq2);
        Assert.assertEquals("JAY Z", dq3);

    }

    @Test
    public void testEmpty() {

        Queue<String> stringQueue = new QueueArrayImpl();

        stringQueue.enqueue("KANYE");
        //queue should contain KANYE
        //array size is 1, top variable is 0
        boolean empty = stringQueue.isEmpty();

        //because queue contains KANYE, return false
        Assert.assertEquals(empty, false);
    }

    @Test
    public void testEmpty2() {

        Queue<String> stringQueue = new QueueArrayImpl();

        //queue has not enqueued any objects
        boolean empty = stringQueue.isEmpty();

        //because queue contains no objects return true
        Assert.assertEquals(empty, true);
       
    }
    
    @Test
    public void sizeTest(){
        
        Queue<String> stringQueue = new QueueArrayImpl();

        stringQueue.enqueue("KANYE");
        stringQueue.enqueue("YEEZY");
        stringQueue.enqueue("JAY Z");
        
        //queue test contains three objects: KANYE, YEEZY, JAYZ
        //.size should count three objects
        int size = stringQueue.size();
        
        Assert.assertEquals(size, 3, 0);
        
        
        stringQueue.dequeue();
        //should only have 2 now.
        int size2 = stringQueue.size();
        Assert.assertEquals(size2, 2, 0);
    }
}
