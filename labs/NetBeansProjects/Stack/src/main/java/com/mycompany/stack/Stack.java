/*
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
public interface Stack <T>{
    
    public void push(T element);
    public T pop();
    public boolean isEmpty();
    public int size(); 
        
        
    
}
