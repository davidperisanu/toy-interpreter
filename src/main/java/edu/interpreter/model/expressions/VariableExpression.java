package edu.interpreter.model.expressions;

import edu.interpreter.model.utilities.exceptions.InvalidArgumentException;
import edu.interpreter.model.utilities.interfaces.IDictionary;

/**
 * Represents a variable expression.
 * @author David Perisanu
 */
public final class VariableExpression extends Expression {
    private String variableName;

    /**
     * Initializes a new instance of the <code>VariableExpression</code> class with the specified value.
     * @param variableName The variable name of the <code>VariableExpression</code>.
     */
    public VariableExpression(String variableName) {
        this.variableName = variableName;
    }

    /**
     * Computes the value of the <code>VariableExpression</code>.
     * @param symbolTable Symbol table of the <code>ProgramState</code>.
     * @return The value of the <code>VariableExpression</code>.
     * @throws InvalidArgumentException if the key could not be found inside the <code>IDictionary<></code>.
     */
    @Override
    public int evaluate(IDictionary<String, Integer> symbolTable) throws InvalidArgumentException {
        return symbolTable.get(variableName);
    }

    /**
     * Gets a string representation of the <code>VariableExpression</code>.
     * @return The string representation of the <code>VariableExpression</code>.
     */
    @Override
    public String toString() {
        return variableName;
    }
}
