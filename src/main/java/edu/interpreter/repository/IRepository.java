package edu.interpreter.repository;

import edu.interpreter.model.ProgramState;
import edu.interpreter.model.utilities.exceptions.InvalidOperationException;

/**
 * Represents a generic <code>ProgramState</code> repository.
 * @author David Perisanu
 */
public interface IRepository {
    /**
     * Adds a <code>ProgramState</code> to the <code>IRepository</code>.
     * @param programState The <code>ProgramState</code> to add to the <code>IRepository</code>.
     */
    public void add(ProgramState programState);

    /**
     * Gets the current <code>ProgramState</code>.
     * @return The current <code>ProgramState</code>.
     * @throws InvalidOperationException if the <code>IRepository</code> is empty.
     */
    public ProgramState getProgramState() throws InvalidOperationException;
}
