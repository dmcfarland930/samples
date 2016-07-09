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
public class QueueArrayImpl<T> implements Queue<T> {

    private T[] queue;
    private int size;
    private int front = 0;
    private int end;

    public QueueArrayImpl() {

        queue = (T[]) new Object[size];
        end = -1;

    }

    @Override
    public void enqueue(T element) {
        size++;
        T[] newQueue = (T[]) new Object[size];
        System.arraycopy(queue, 0, newQueue, 0, queue.length);
        end = end + 1;
        newQueue[end] = element;
        queue = newQueue;
    }

    @Override
    public T dequeue() {
        T elementInQueue = null;
        if (!isEmpty()) {
            elementInQueue = queue[front];
            size = size -1;
            T[] newQueue = (T[]) new Object[size];
            for (int i = 0; i < size; i++) {
                newQueue[i] = queue[i+1];
            }
         queue = newQueue;
        }
        return elementInQueue;
    }

    @Override
    public boolean isEmpty() {

        boolean empty = false;

        if (end == -1) {
            empty = true;
        }

        return empty;
    }

    @Override
    public int size() {
        int count = 0;
        for (T t : queue) {
            count++;
        }
        return count;
    }

}
