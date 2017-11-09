package edu.interpreter.model.utilities.interfaces;

import java.util.ArrayList;
import java.util.ListIterator;

import edu.interpreter.model.utilities.exceptions.InvalidArgumentException;

/**
 * Represents a generic collection of key/value pairs.
 * @author David Perisanu
 */
public interface IDictionary<Key, Value> {
    /**
     * Adds an element with the provided key and value to the<code>IDictionary<></code>.
     * If an element with the provided key exists, its value will be updated.
     * @param key The object to use as the key of the element to add.
     * @param item The object to add to the<code>IDictionary<></code> elements collection.
     */
    public void add(Key key, Value value);

    /**
     * Determines whether the<code>IDictionary<></code> contains the specified key.
     * @param key The key to locate in the<code>IDictionary<></code>.
     * @return A boolean value which indicates whether the specified key exists or not.
     */
    public boolean contains(Key key);

    /**
     * Gets the value with the specified key from the<code>IDictionary<></code>.
     * @param key The key of the value to get.
     * @return The value of the provided key.
     * @throws InvalidArgumentException if the key could not be found inside the<code>IDictionary<></code>.
     */
    public Value get(Key key) throws InvalidArgumentException;

    /**
     * Removes the value with the specified key from the<code>IDictionary<></code>.
     * @param key They of the element to remove.
     * @throws InvalidArgumentException if the key could not be found inside the<code>IDictionary<></code>.
     */
    public void remove(Key key) throws InvalidArgumentException;

    /**
     * Gets an iterable <code>ArrayList<></code> of all the existing keys.
     * @return An iterable <code>ArrayList<></code> of all the existing keys.
     */
    public ArrayList<Key> allKeys();

    /**
     * Gets an iterable <code>ArrayList<></code> of all the existing values.
     * @return An iterable <code>ArrayList<></code> of all the existing values.
     */
    public ArrayList<Value> allValues();

    /**
     * Gets a <code>ListIterator<></code> for the keys.
     * @return A <code>ListIterator<></code> for the keys.
     */
    public ListIterator<Key> keysIterator();

    /**
     * Gets a <code>ListIterator<></code> for the values.
     * @return A <code>ListIterator<></code> for the values.
     */
    public ListIterator<Value> valuesIterator();
}
