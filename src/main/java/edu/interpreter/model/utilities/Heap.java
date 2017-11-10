package edu.interpreter.model.utilities;

import java.util.ArrayList;
import java.util.ListIterator;

import edu.interpreter.model.utilities.exceptions.InvalidArgumentException;
import edu.interpreter.model.utilities.interfaces.IHeap;

/**
 * Represents a heap of keys and values.
 * @author David Perisanu
 */
public class Heap<Key, Value> extends Dictionary<Key, Value> implements IHeap<Key, Value> {
    /**
     * Initializes a new instance of the <code>Heap<></code> class that is empty and has the default initial capacity.
     */
    public Heap() {
        super();
    }

    /**
     * Initializes a new instance of the <code>Heap<></code> class that is empty and has the specified initial capacity.
     * @param capacity The initial number of elements that the <code>Heap<></code> can contain.
     */
    public Heap(int capacity) {
        super(capacity);
    }

    /**
     * Adds an element with the provided key and value to the <code>Heap<></code>.
     * If an element with the provided key exists, its value will be updated.
     * @param key The object to use as the key of the element to add.
     * @param item The object to add to the <code>Heap<></code> elements collection.
     */
    @Override
    public void add(Key key, Value value) {
        //container.put(key, value);
        super.add(key, value);
    }

    /**
     * Determines whether the <code>Heap<></code> contains the specified key.
     * @param key The key to locate in the <code>Heap<></code>.
     * @return A boolean value which indicates whether the specified key exists or not.
     */
    @Override
    public boolean contains(Key key) {
        return super.contains(key);
    }

    /**
     * Gets the value with the specified key from the <code>Heap<></code>.
     * @param key The key of the value to get.
     * @return The value of the provided key.
     * @throws InvalidArgumentException if the key could not be found inside the <code>Heap<></code>.
     */
    @Override
    public Value get(Key key) throws InvalidArgumentException {
        return super.get(key);
    }

    /**
     * Removes the value with the specified key from the <code>Heap<></code>.
     * @param key They of the element to remove.
     * @throws InvalidArgumentException if the key could not be found inside the <code>Heap<></code>.
     */
    @Override
    public void remove(Key key) throws InvalidArgumentException {
        super.remove(key);
    }

    /**
     * Gets an iterable <code>ArrayList<></code> of all the existing keys.
     * @return An iterable <code>ArrayList<></code> of all the existing keys.
     */
    @Override
    public ArrayList<Key> allKeys() {
        return super.allKeys();
    }

    /**
     * Gets an iterable <code>ArrayList<></code> of all the existing values.
     * @return An iterable <code>ArrayList<></code> of all the existing values.
     */
    @Override
    public ArrayList<Value> allValues() {
        return super.allValues();
    }

    /**
     * Gets a <code>ListIterator<></code> for the keys.
     * @return A <code>ListIterator<></code> for the keys.
     */
    @Override
    public ListIterator<Key> keysIterator() {
        return super.keysIterator();
    }

    /**
     * Gets a <code>ListIterator<></code> for the values.
     * @return A <code>ListIterator<></code> for the values.
     */
    @Override
    public ListIterator<Value> valuesIterator() {
        return super.valuesIterator();
    }

    /**
     * Gets the number of elements inside the <code>Heap<></code>.
     * @return The number of elements inside the <code>Heap<></code>.
     */
    @Override
    public int size() {
        return super.size();
    }

    /**
     * Gets a string representation of the <code>Heap<></code>.
     * @return The string representation of the <code>Heap<></code>.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
