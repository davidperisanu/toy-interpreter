package edu.interpreter.model.statements;

import edu.interpreter.model.ProgramState;

/**
 * Represents a generic statement.
 * @author David Perisanu
 */
public abstract class Statement {
    /**
     * Executes the <code>Statement</code>.
     * @param programState The program state before the execution of the <code>Statement</code>.
     * @return The program state after the execution of the <code>Statement</code>.
     */
    public abstract ProgramState execute(ProgramState programState);

    /**
     * Gets a string representation of the <code>Statement</code>.
     * @return The string representation of the <code>Statement</code>.
     */
    @Override
    public abstract String toString();
}
