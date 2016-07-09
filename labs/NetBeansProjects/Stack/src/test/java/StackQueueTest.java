/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.stack.Queue;
import com.mycompany.stack.QueueArrayImpl;
import com.mycompany.stack.Stack;
import com.mycompany.stack.StackArrayImpl;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author apprentice
 */
public class StackQueueTest {
    
    public StackQueueTest() {
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
    public void stackTest() {
        
        //Test push
        Stack <String> stringStack = new StackArrayImpl<>();
        
        stringStack.push("Bill");
        stringStack.push("Jones");
        stringStack.push("Brennan");
        stringStack.push("Bob");
        stringStack.push("James");
        
        boolean works = true;
        if(stringStack.size() != 5) {
            works = false;
        } 
        Assert.assertEquals(true, works);
        
        //test pop
        String pop1 = stringStack.pop();
        String pop2 = stringStack.pop();
        
        Assert.assertEquals("James", pop1);
        Assert.assertEquals("Bob", pop2);
        
        //test size
        int size = stringStack.size();
        if (size != 3)    {
            works = false;
        }
        Assert.assertEquals(true, works);
        
        //test isEmpty
        Stack <String> emptyStack = new StackArrayImpl<>();
        boolean result = emptyStack.isEmpty();
        Assert.assertEquals(true, result);
        
        
    }
    
    @Test
    public void queueTests()    {
        //Test Enqueue
        Queue <String> stringQueue = new QueueArrayImpl<>();
        
        stringQueue.enqueue("Brennan");
        stringQueue.enqueue("Bennett");
        stringQueue.enqueue("Bob");
        stringQueue.enqueue("John");
        stringQueue.enqueue("Peter");
        
        //Test size
       int queueSize = stringQueue.size();
       Assert.assertEquals(5, queueSize);
        
        //Test Dequeue
        String d1 = stringQueue.dequeue();
        String d2 = stringQueue.dequeue();
        
        stringQueue.enqueue("Jacob");
        
        String d3 = stringQueue.dequeue();
        Assert.assertEquals("Brennan", d1);
        Assert.assertEquals("Bennett", d2);
        Assert.assertEquals("Bob", d3);
        
        String d4 = stringQueue.dequeue();
        String d5 = stringQueue.dequeue();
        String d6 = stringQueue.dequeue();
        
        Assert.assertEquals("Jacob", d6);
        
        //Test isEmpty
        Queue <String> emptyQueue = new QueueArrayImpl<>();
        queueSize = emptyQueue.size();
        Assert.assertEquals(0, queueSize);

    }
}
