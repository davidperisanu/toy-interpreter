package edu.interpreter.model.utilities;

import java.util.LinkedList;

import edu.interpreter.model.utilities.exceptions.InvalidOperationException;

/**
 * Represents a double-ended-queue that supports element insertion and removal at both ends.
 * @author David Perisanu
 */
public class Deque<T> implements IDeque<T> {
    private LinkedList<T> container;

    /**
     * Initializes a new instance of the <code>Deque<></code> class that is empty. 
     */
    public Deque() {
        container = new LinkedList<>();
    }

    /**
     * Inserts an object at the beginning of the <code>Deque<></code>.
     * @param item The object to push onto the <code>Deque<></code>.
     */
    @Override
    public void pushFront(T item) {
        container.addFirst(item);
    }

    /**
     * Inserts an object at the end of the <code>Deque<></code>.
     * @param item The object to push onto the <code>Deque<></code>.
     */
    @Override
    public void pushBack(T item) {
        container.addLast(item);
    }

    /**
     * Removes the object at the beginning of the <code>Deque<></code>.
     * @return The object that has just been removed.
     * @throws InvalidOperationException if the <code>Deque<></code> is empty.
     */
    @Override
    public T popFront() throws InvalidOperationException {
        if (container.isEmpty())
            throw new InvalidOperationException("Deque is empty.");

        return container.pollFirst();
    }

    /**
     * Removes the object at the end of the <code>Deque<></code>.
     * @return The object that has just been removed.
     * @throws InvalidOperationException if the <code>Deque<></code> is empty.
     */
    @Override
    public T popBack() throws InvalidOperationException {
        if (container.isEmpty())
            throw new InvalidOperationException("Deque is empty.");

        return container.pollLast();
    }

    /**
     * Determines whether the <code>Deque<></code> is empty.
     * @return A boolean value which indicates whether the <code>Deque<></code> is empty or not.
     */
    @Override
    public boolean isEmpty() {
        return container.isEmpty();
    }

    /**
     * Gets the object at the beginning of the <code>Deque<></code>.
     * @return The object at the beginning of the <code>Deque<></code>.
     * @throws InvalidOperationException if the <code>Deque<></code> is empty.
     */
    @Override
    public T front() throws InvalidOperationException {
        if (container.isEmpty())
            throw new InvalidOperationException("Deque is empty.");

        return container.getFirst();
    }

    /**
     * Gets the object at the end of the <code>Deque<></code>.
     * @return The object at the end of the <code>Deque<></code>.
     * @throws InvalidOperationException if the <code>Deque<></code> is empty.
     */
    @Override
    public T back() throws InvalidOperationException {
        if (container.isEmpty())
            throw new InvalidOperationException("Deque is empty.");

        return container.getLast();
    }

    /**
     * Gets a string representation of the <code>Deque<></code>.
     * @return The string representation of the <code>Deque<></code>.
     */
    @Override
    public String toString() {
        if (container.isEmpty())
            return "{ }";
        
        StringBuilder stringBuilder = new StringBuilder();

        for (T item : container)
            stringBuilder.append(item + ", ");

        return "{ " + stringBuilder.substring(0, stringBuilder.length() - 2) + " }";
    }
}
