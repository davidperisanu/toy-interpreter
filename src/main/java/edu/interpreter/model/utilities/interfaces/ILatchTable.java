package edu.interpreter.model.utilities.interfaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Set;

import edu.interpreter.model.utilities.Pair;
import edu.interpreter.model.utilities.exceptions.InvalidArgumentException;

/**
 * Represents a generic latch table of key/value pairs.
 * @author David Perisanu
 */
public interface ILatchTable<Key, Value> extends Serializable {
    /**
     * Adds an element with the provided key and value to the <code>ILatchTable<></code>.
     * If an element with the provided key exists, its value will be updated.
     * @param key The object to use as the key of the element to add.
     * @param item The object to add to the <code>ILatchTable<></code> elements collection.
     */
    public void add(Key key, Value value);
    
    /**
    * Determines whether the <code>ILatchTable<></code> contains the specified key.
    * @param key The key to locate in the <code>ILatchTable<></code>.
    * @return A boolean value which indicates whether the specified key exists or not.
    */
    public boolean contains(Key key);
    
    /**
    * Gets the value with the specified key from the <code>ILatchTable<></code>.
    * @param key The key of the value to get.
    * @return The value of the provided key.
    * @throws InvalidArgumentException if the key could not be found inside the <code>ILatchTable<></code>.
    */
    public Value get(Key key) throws InvalidArgumentException;
    
    /**
    * Removes the value with the specified key from the <code>ILatchTable<></code>.
    * @param key They of the element to remove.
    * @throws InvalidArgumentException if the key could not be found inside the <code>ILatchTable<></code>.
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

    /**
     * Gets the number of elements inside the <code>ILatchTable<></code>.
     * @return The number of elements inside the <code>ILatchTable<></code>.
     */
    public int size();

    /**
     * Gets a set containing all key/value pairs of the <code>ILatchTable<></code>.
     * @return A set containing all key/value pairs of the <code>ILatchTable<></code>.
     */
    public Set<Pair<Key, Value>> entries();
}
