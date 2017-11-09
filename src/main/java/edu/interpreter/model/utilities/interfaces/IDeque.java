package edu.interpreter.model.utilities.interfaces;

import java.util.ArrayList;
import java.util.ListIterator;

import edu.interpreter.model.utilities.exceptions.InvalidOperationException;

/**
 * Represents a generalized type of queue.
 * @author David Perisanu
 */
public interface IDeque<T> {
    /**
     * Inserts an object at the beginning of the <code>IDeque<></code>.
     * @param item The object to push onto the <code>IDeque<></code>.
     */
    public void pushFront(T item);

    /**
     * Inserts an object at the end of the <code>IDeque<></code>.
     * @param item The object to push onto the <code>IDeque<></code>.
     */
    public void pushBack(T item);

    /**
     * Removes the object at the beginning of the <code>IDeque<></code>.
     * @return The object that has just been removed.
     * @throws InvalidOperationException if the <code>IDeque<></code> is empty.
     */
    public T popFront() throws InvalidOperationException;

    /**
     * Removes the object at the end of the <code>IDeque<></code>.
     * @return The object that has just been removed.
     * @throws InvalidOperationException if the <code>IDeque<></code> is empty.
     */
    public T popBack() throws InvalidOperationException;

    /**
     * Determines whether the <code>IDeque<></code> is empty.
     * @return A boolean value which indicates whether the <code>IDeque<></code> is empty or not.
     */
    public boolean isEmpty();

    /**
     * Gets the object at the beginning of the <code>IDeque<></code>.
     * @return The object at the beginning of the <code>IDeque<></code>.
     * @throws InvalidOperationException if the <code>IDeque<></code> is empty.
     */
    public T front();

    /**
     * Gets the object at the end of the <code>IDeque<></code>.
     * @return The object at the end of the <code>IDeque<></code>.
     * @throws InvalidOperationException if the <code>IDeque<></code> is empty.
     */
    public T back();

    /**
     * Gets an iterable <code>ArrayList<></code> of all the existing elements.
     * @return An iterable <code>ArrayList<></code> of all the existing elements.
     */
    public ArrayList<T> all();

    /**
     * Gets a front-to-back <code>ListIterator<></code>.
     * @return A front-to-back <code>ListIterator<></code>.
     */
    public ListIterator<T> iteratorFront();

    /**
     * Gets a back-to-front <code>ListIterator<></code>.
     * @return A back-to-front <code>ListIterator<></code>.
     */
    public ListIterator<T> iteratorBack();
}
