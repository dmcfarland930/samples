/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.stack;

/**
 *
 * @author apprentice
 */
public class DataStructure {

    public static void main(String[] args) {
        
        
        Stack<String> stringStack = new StackArrayImpl<>();

        stringStack.push("Bill");
        stringStack.push("Jones");
        
        String p1 = stringStack.pop();
        String p2 = stringStack.pop();
        
        Queue<String> stringQueue = new QueueArrayImpl();
        
        stringQueue.enqueue("Bill");
        stringQueue.enqueue("Jones");
        stringQueue.enqueue("Mark");
        
        String dq = stringQueue.dequeue();
        
        String dq2 = stringQueue.dequeue();
        
        
    }
}
