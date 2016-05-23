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
public class StackArrayImpl<T> implements Stack<T> {

    private T[] stack;
    private int size;
    private int top;

    public StackArrayImpl() {

        stack = (T[]) new Object[size];
        top = -1;

    }

    @Override
    public void push(T element) {
        if(element != null){
        size++;
        T[] newStack = (T[]) new Object[size];
        System.arraycopy(stack, 0, newStack, 0, stack.length);
        top = top + 1;
        newStack[top] = element;
        stack = newStack;
        }
    }

    @Override
    public T pop() {

        T elementInStack = null;
        if (!isEmpty()) {
            elementInStack = stack[top];
            top = top - 1;
            size = size - 1;
            T[] newStack = (T[]) new Object[size];
            for (int i = 0; i < size; i++) {
                newStack[i] = stack[i];
            }
           stack = newStack;
        }
        return elementInStack;
    }

    @Override
    public boolean isEmpty() {

        boolean empty = false;

        if (top == -1) {
            empty = true;
        }

        return empty;
    }

    @Override
    public int size() {

        int count = 0;
        for (T t : stack) {
            count++;
        }
        return count;
    }

}
