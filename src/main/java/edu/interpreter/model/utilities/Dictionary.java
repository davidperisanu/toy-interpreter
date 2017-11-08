package edu.interpreter.model.utilities;

import java.util.HashMap;

import edu.interpreter.model.utilities.exceptions.InvalidArgumentException;

/**
 * Represents a collection of keys and values.
 * @author David Perisanu
 */
public class Dictionary<Key, Value> implements IDictionary<Key, Value> {
    private HashMap<Key, Value> container;

    /**
     * Initializes a new instance of the <code>Dictionary<></code> class that is empty and has the default initial capacity.
     */
    public Dictionary() {
        container = new HashMap<>();
    }

    /**
     * Initializes a new instance of the <code>Dictionary<></code> class that is empty and has the specified initial capacity.
     * @param capacity The initial number of elements that the <code>Dictionary<></code> can contain.
     */
    public Dictionary(int capacity) {
        container = new HashMap<>(capacity);
    }

    /**
     * Adds an element with the provided key and value to the <code>Dictionary<></code>.
     * If an element with the provided key exists, its value will be updated.
     * @param key The object to use as the key of the element to add.
     * @param item The object to add to the <code>Dictionary<></code> elements collection.
     */
    @Override
    public void add(Key key, Value value) {
        container.put(key, value);
    }

    /**
     * Determines whether the <code>Dictionary<></code> contains the specified key.
     * @param key The key to locate in the <code>Dictionary<></code>.
     * @return A boolean value which indicates whether the specified key exists or not.
     */
    @Override
    public boolean contains(Key key) {
        return container.containsKey(key);
    }

    /**
     * Gets the value with the specified key from the <code>Dictionary<></code>.
     * @param key The key of the value to get.
     * @return The value of the provided key.
     * @throws InvalidArgumentException if the key could not be found inside the <code>Dictionary<></code>.
     */
    @Override
    public Value get(Key key) throws InvalidArgumentException {
        if (!container.containsKey(key))
            throw new InvalidArgumentException("No key was found.");

        return container.get(key);
    }

    /**
     * Removes the value with the specified key from the <code>Dictionary<></code>.
     * @param key They of the element to remove.
     * @throws InvalidArgumentException if the key could not be found inside the <code>Dictionary<></code>.
     */
    @Override
    public void remove(Key key) throws InvalidArgumentException {
        if (!container.containsKey(key))
            throw new InvalidArgumentException("No key was found.");

        container.remove(key);
    }

    /**
     * Gets a string representation of the <code>Dictionary<></code>.
     * @return The string representation of the <code>Dictionary<></code>.
     */
    @Override
    public String toString() {
        if (container.size() == 0)
            return "{ }";

        StringBuilder stringBuilder = new StringBuilder();

        for (Key key : container.keySet())
            stringBuilder.append(key + " -> " + container.get(key) + ", ");

        return "{ " + stringBuilder.substring(0, stringBuilder.length() - 2) + " }";
    }
}
