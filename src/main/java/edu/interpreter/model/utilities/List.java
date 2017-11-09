package edu.interpreter.model.utilities;

import java.util.ArrayList;
import java.util.ListIterator;

import edu.interpreter.model.utilities.exceptions.IndexOutOfRangeException;
import edu.interpreter.model.utilities.interfaces.IList;

/**
 * Represents a collection of objects that can be accessed by index.
 * @author David Perisanu
 */
public class List<T> implements IList<T> {
    private ArrayList<T> container;

    /**
     * Initializes a new instance of the <code>List<></code> class that is empty and has the default initial capacity.
     */
    public List() {
        container = new ArrayList<>();
    }

    /**
     * Initializes a new instance of the <code>List<></code> class that is empty and has the specified inital capacity.
     * @param capacity The number of elements that the new list can initially store.
     */
    public List(int capacity) {
        container = new ArrayList<>(capacity);
    }

    /**
     * Adds an item to the <code>List<></code>.
     * @param item The object to add to the <code>List<></code>.
     */
    @Override
    public void add(T item) {
        container.add(item);
    }

    /**
     * Inserts an item to the <code>List<></code> at the specified index.
     * @param index The zero-based index at which item should be inserted.
     * @param item The object to insert into the <code>List<></code>.
     * @throws IndexOutOfRangeException if the index is outside the bounds of the <code>List<></code>.
     */
    @Override
    public void insert(int index, T item) throws IndexOutOfRangeException {
        if (index < 0 || index > container.size() - 1)
            throw new IndexOutOfRangeException("Index was out of range. Must be non-negative and less than the size of the collection.");
        
        container.add(index, item);
    }

    /**
     * Gets the element at the specified index.
     * @param index The zero-based index of the element to get.
     * @return The element at the specified index.
     * @throws IndexOutOfRangeException if the index is outside the bounds of the <code>List<></code>.
     */
    @Override
    public T get(int index) throws IndexOutOfRangeException {
        if (index < 0 || index > container.size() - 1)
            throw new IndexOutOfRangeException("Index was out of range. Must be non-negative and less than the size of the collection.");

        return container.get(index);
    }

    /**
     * Sets the value of an element at the specified index with the provided object.
     * @param index The zero-based index of the element to set.
     * @param item The object that will represent the new value found at the specified index.
     * @throws IndexOutOfRangeException if the index is outside the bounds of the <code>List<></code>.
     */
    @Override
    public void set(int index, T item) throws IndexOutOfRangeException {
        if (index < 0 || index > container.size() - 1)
            throw new IndexOutOfRangeException("Index was out of range. Must be non-negative and less than the size of the collection.");

        container.set(index, item);
    }

    /**
     * Removes the first occurrence of a specific object from the <code>List<></code>.
     * @param item The object to remove from the <code>List<></code>.
     */
    @Override
    public void remove(T item) {
        container.remove(item);
    }

    /**
     * Removes the <code>List<></code> item at the specified index.
     * @param index The zero-based index of the item to remove.
     * @throws IndexOutOfRangeException if the index is outside the bounds of the <code>List<></code>.
     */
    @Override
    public void removeAt(int index) throws IndexOutOfRangeException {
        if (index < 0 || index > container.size() - 1)
            throw new IndexOutOfRangeException("Index was out of range. Must be non-negative and less than the size of the collection.");

            container.remove(index);
    }

    /**
     * Gets an iterable <code>ArrayList<></code> of all the existing elements.
     * @return An iterable <code>ArrayList<></code> of all the existing elements.
     */
    @Override
    public ArrayList<T> all() {
        return container;
    }

    /**
     * Gets a <code>ListIterator<></code>.
     * @return A <code>ListIterator<></code>.
     */
    @Override
    public ListIterator<T> iterator() {
        return container.listIterator();
    }
    
    /**
     * Gets a <code>ListIterator<></code> starting at the specified index.
     * @param index Iterator starting position.
     * @return A <code>ListIterator<></code> starting at the specified index.
     * @throws IndexOutOfRangeExceptionif the index is outside the bounds of the <code>IList<></code>.
     */
    @Override
    public ListIterator<T> iterator(int index) throws IndexOutOfRangeException {
        if (index < -1 || index > container.size())
            throw new IndexOutOfRangeException("Index was outside the expected bounds.");

        return container.listIterator(index);
    }

    /**
     * Gets a string representation of the <code>List<></code>.
     * @return The string representation of the <code>List<></code>.
     */
    @Override
    public String toString() {
        if (container.size() == 0)
            return "{ }";
        
        StringBuilder stringBuilder = new StringBuilder();

        for (T item : container)
            stringBuilder.append(item + ", ");

        return "{ " + stringBuilder.substring(0, stringBuilder.length() - 2) + " }";
    }
}
