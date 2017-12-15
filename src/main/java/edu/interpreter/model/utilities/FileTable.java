package edu.interpreter.model.utilities;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Set;

import edu.interpreter.model.utilities.exceptions.InvalidArgumentException;
import edu.interpreter.model.utilities.interfaces.IFileTable;

/**
 * Represents a file table of keys and values.
 * @author David Perisanu
 */
public class FileTable<Key, Value> extends Dictionary<Key, Value> implements IFileTable<Key, Value> {
    private static final long serialVersionUID = IdGenerator.generateLongId();
    
    /**
     * Initializes a new instance of the <code>FileTable<></code> class that is empty and has the default initial capacity.
     */
    public FileTable() {
        super();
    }

    /**
     * Initializes a new instance of the <code>FileTable<></code> class that is empty and has the specified initial capacity.
     * @param capacity The initial number of elements that the <code>FileTable<></code> can contain.
     */
    public FileTable(int capacity) {
        super(capacity);
    }

    /**
     * Adds an element with the provided key and value to the <code>FileTable<></code>.
     * If an element with the provided key exists, its value will be updated.
     * @param key The object to use as the key of the element to add.
     * @param item The object to add to the <code>FileTable<></code> elements collection.
     */
    @Override
    public void add(Key key, Value value) {
        super.add(key, value);
    }

    /**
     * Determines whether the <code>FileTable<></code> contains the specified key.
     * @param key The key to locate in the <code>FileTable<></code>.
     * @return A boolean value which indicates whether the specified key exists or not.
     */
    @Override
    public boolean contains(Key key) {
        return super.contains(key);
    }

    /**
     * Gets the value with the specified key from the <code>FileTable<></code>.
     * @param key The key of the value to get.
     * @return The value of the provided key.
     * @throws InvalidArgumentException if the key could not be found inside the <code>FileTable<></code>.
     */
    @Override
    public Value get(Key key) throws InvalidArgumentException {
        try {
            return super.get(key);
        }
        catch (InvalidArgumentException e) {
            throw new InvalidArgumentException("No key was found inside the file table.");
        }
    }

    /**
     * Removes the value with the specified key from the <code>FileTable<></code>.
     * @param key They of the element to remove.
     * @throws InvalidArgumentException if the key could not be found inside the <code>FileTable<></code>.
     */
    @Override
    public void remove(Key key) throws InvalidArgumentException {
        try {
            super.remove(key);
        }
        catch (InvalidArgumentException e) {
            throw new InvalidArgumentException("No key was found inside the file table.");
        }
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
     * Gets the number of elements inside the <code>FileTable<></code>.
     * @return The number of elements inside the <code>FileTable<></code>.
     */
    @Override
    public int size() {
        return super.size();
    }

    /**
     * Gets a set containing all key/value pairs of the <code>FileTable<></code>.
     * @return A set containing all key/value pairs of the <code>FileTable<></code>.
     */
    @Override
    public Set<Pair<Key, Value>> entries() {
        return super.entries();
    }

    /**
     * Gets a string representation of the <code>FileTable<></code>.
     * @return The string representation of the <code>FileTable<></code>.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
