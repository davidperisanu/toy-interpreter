package edu.interpreter.model.utilities.interfaces;

import java.util.ArrayList;
import java.util.ListIterator;

import edu.interpreter.model.utilities.exceptions.IndexOutOfRangeException;

/**
 * Represents a collection of objects that can be individually accessed by index.
 * @author David Perisanu
 */
public interface IList<T> {
    /**
     * Adds an item to the <code>IList<></code>.
     * @param item The object to add to the <code>IList<></code>.
     */
    public void add(T item);

    /**
     * Inserts an item to the <code>IList<></code> at the specified index.
     * @param index The zero-based index at which item should be inserted.
     * @param item The object to insert into the <code>IList<></code>.
     * @throws IndexOutOfRangeException if the index is outside the bounds of the <code>IList<></code>.
     */
    public void insert(int index, T item) throws IndexOutOfRangeException;

    /**
     * Gets the element at the specified index.
     * @param index The zero-based index of the element to get.
     * @return The element at the specified index.
     * @throws IndexOutOfRangeException if the index is outside the bounds of the <code>IList<></code>.
     */
    public T get(int index) throws IndexOutOfRangeException;

    /**
     * Sets the value of an element at the specified index with the provided object.
     * @param index The zero-based index of the element to set.
     * @param item The object that will represent the new value found at the specified index.
     * @throws IndexOutOfRangeException if the index is outside the bounds of the <code>IList<></code>.
     */
    public void set(int index, T item) throws IndexOutOfRangeException;

    /**
     * Removes the first occurrence of a specific object from the <code>IList<></code>.
     * @param item The object to remove from the <code>IList<></code>.
     */
    public void remove(T item);

    /**
     * Removes the <code>IList<></code> item at the specified index.
     * @param index The zero-based index of the item to remove.
     * @throws IndexOutOfRangeException if the index is outside the bounds of the <code>IList<></code>.
     */
    public void removeAt(int index) throws IndexOutOfRangeException;

    /**
     * Gets an iterable <code>ArrayList<></code> of all the existing elements.
     * @return An iterable <code>ArrayList<></code> of all the existing elements.
     */
    public ArrayList<T> all();

    /**
     * Gets a <code>ListIterator<></code>.
     * @return A <code>ListIterator<></code>.
     */
    public ListIterator<T> iterator();
    
    /**
     * Gets a <code>ListIterator<></code> starting at the specified index.
     * @param index Iterator starting position.
     * @return A <code>ListIterator<></code> starting at the specified index.
     * @throws IndexOutOfRangeExceptionif the index is outside the bounds of the <code>IList<></code>.
     */
    public ListIterator<T> iterator(int index) throws IndexOutOfRangeException;

    /**
     * Gets the number of elements inside the <code>IList<></code>.
     * @return The number of elements inside the <code>IList<></code>.
     */
    public int size();
}
