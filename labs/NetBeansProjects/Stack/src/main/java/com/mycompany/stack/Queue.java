/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.stack;

/**
 *
 * @author apprentice
 * @param <T>
 */
public interface Queue<T> {
    
    public void enqueue(T elememt);
    public T dequeue();
    public boolean isEmpty();
    public int size();

}
