package edu.interpreter.model.utilities;

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
}
