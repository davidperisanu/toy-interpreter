package edu.interpreter.repository;

import java.util.ArrayList;

import edu.interpreter.model.ProgramState;
import edu.interpreter.model.utilities.exceptions.IndexOutOfRangeException;
import edu.interpreter.model.utilities.exceptions.InvalidOperationException;

/**
 * Represents a <code>ProgramState</code> repository.
 * @author David Perisanu
 */
public class Repository implements IRepository {
    private ArrayList<ProgramState> container;

    /**
     * Initializes a new instance of the <code>Repository</code> class that is empty and has the default initial capacity.
     */
    public Repository() {
        container = new ArrayList<>();
    }

    /**
     * Initializes a new instance of the <code>Repository</code> class that is empty and has the specified inital capacity.
     * @param capacity The number of elements that the new repository can initially store.
     */
    public Repository(int capacity) {
        container = new ArrayList<>(capacity);
    }

    /**
     * Adds a <code>ProgramState</code> to the <code>Repository</code>.
     * @param programState The <code>ProgramState</code> to add to the <code>Repository</code>.
     */
    @Override
    public void add(ProgramState programState) {
        container.add(programState);
    }

    /**
     * Gets the current <code>ProgramState</code>.
     * @return The current <code>ProgramState</code>.
     * @throws IndexOutOfRangeException if the <code>Repository</code> is empty.
     */
    @Override
    public ProgramState getProgramState() throws InvalidOperationException {
        if (container.isEmpty())
            throw new InvalidOperationException("Repository is empty.");
        
        return container.get(0);
    }
}
