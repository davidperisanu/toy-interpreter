package edu.interpreter.model.expressions;

import edu.interpreter.model.utilities.exceptions.InvalidArgumentException;
import edu.interpreter.model.utilities.interfaces.IDictionary;
import edu.interpreter.model.utilities.interfaces.IHeap;

/**
 * Represents a head reading expression.
 * @author David Perisanu
 */
public class HeapReadingExpression extends Expression {
    private String variableName;

    /**
     * Initializes a new instance of the <code>HeapReadingExpression</code> class with the specified value.
     * @param variableName Variable name that refers the heap alocated space.
     */
    public HeapReadingExpression(String variableName) {
        this.variableName = variableName;
    }

    /**
     * Computes the value of the <code>HeapReadingExpression</code>.
     * @param symbolTable Symbol table of the <code>ProgramState</code>.
     * @param heap Heap of the <code>ProgramState</code>.
     * @return The value of the <code>VariableExpression</code>.
     * @throws InvalidArgumentException if the key could not be found inside the <code>IDictionary<></code>.
     * @throws InvalidArgumentException if the key could not be found inside the <code>IHeap<></code>.
     */
    @Override
    public int evaluate(IDictionary<String, Integer> symbolTable, IHeap<Integer, Integer> heap) throws InvalidArgumentException {
        return heap.get(symbolTable.get(variableName));
    }

    /**
     * Gets a string representation of the <code>HeapReadingExpression</code>.
     * @return The string representation of the <code>HeapReadingExpression</code>.
     */
    @Override
    public String toString() {
        return "readHeap(" + variableName + ");";
    }
}
