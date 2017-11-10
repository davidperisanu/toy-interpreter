package edu.interpreter.model.expressions;

import edu.interpreter.model.utilities.interfaces.IDictionary;
import edu.interpreter.model.utilities.interfaces.IHeap;

/**
 * Represents a constant expression.
 * @author David Perisanu
 */
public final class ConstantExpression extends Expression {
    private final int value;

    /**
     * Initializes a new instance of the <code>ConstantExpression</code> class with the specified value.
     * @param value The constant value of the <code>ConstantExpression</code>.
     */
    public ConstantExpression(final int value) {
        this.value = value;
    }

    /**
     * Computes the value of the <code>ConstantExpression</code>.
     * @param symbolTable Symbol table of the <code>ProgramState</code>.
     * @param heap Heap of the <code>ProgramState</code>.
     * @return The value of the <code>ConstantExpression</code>.
     */
    @Override
    public int evaluate(IDictionary<String, Integer> symbolTable, IHeap<Integer, Integer> heap) {
        return value;
    }

    /**
     * Gets a string representation of the <code>ConstantExpression</code>.
     * @return The string representation of the <code>ConstantExpression</code>.
     */
    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
