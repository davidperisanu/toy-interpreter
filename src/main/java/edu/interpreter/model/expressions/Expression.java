package edu.interpreter.model.expressions;

import edu.interpreter.model.utilities.interfaces.IDictionary;
import edu.interpreter.model.utilities.interfaces.IHeap;

/**
 * Represents a generic expression.
 * @author David Perisanu
 */
public abstract class Expression {
    /**
     * Computes the value of the <code>Expression</code>.
     * @param symbolTable Symbol table of the <code>ProgramState</code>.
     * @param heap Heap of the <code>ProgramState</code>.
     * @return The value of the <code>Expression</code>.
     */
    public abstract int evaluate(IDictionary<String, Integer> symbolTable, IHeap<Integer, Integer> heap);

    /**
     * Gets a string representation of the <code>Expression</code>.
     * @return The string representation of the <code>Expression</code>.
     */
    @Override
    public abstract String toString();
}
