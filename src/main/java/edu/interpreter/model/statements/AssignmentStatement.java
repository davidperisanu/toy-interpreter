package edu.interpreter.model.statements;

import edu.interpreter.model.ProgramState;
import edu.interpreter.model.expressions.ArithmeticExpression;
import edu.interpreter.model.expressions.Expression;
import edu.interpreter.model.utilities.interfaces.IDictionary;

/**
 * Represents an assignment statement.
 * @author David Perisanu
 */
public final class AssignmentStatement extends Statement {
    private String variableName;
    private Expression expression;

    /**
     * Initializes a new instance of the <code>AssignmentStatement</code> class with the specified values.
     * @param variableName The name of the variable.
     * @param expression The <code>Expression</code> to assign to variable.
     */
    public AssignmentStatement(String variableName, Expression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    /**
     * Executes the <code>AssignmentStatement</code>.
     * @param programState The program state before the execution of the <code>AssignmentStatement</code>.
     * @return The program state after the execution of the <code>AssignmentStatement</code>.
     */
    @Override
    public ProgramState execute(ProgramState programState) {
        IDictionary<String, Integer> symbolTable = programState.symbolTable();
        
        symbolTable.add(variableName, expression.evaluate(symbolTable, programState.heap()));

        return null;
    }

    /**
     * Gets a string representation of the <code>AssignmentStatement</code>.
     * @return The string representation of the <code>AssignmentStatement</code>.
     */
    @Override
    public String toString() {
        if (expression instanceof ArithmeticExpression)
            return variableName + " = " + removeParenthesis(expression.toString()) + ";";

        return variableName + " = " + expression + ";";
    }

    /**
     * Removes the unnecessary parenthesis of an <code>ArithmeticExpression</code>.
     * @param arithmeticExpression String representation of the <code>ArithmeticExpression</code>.
     * @return String representation of the <code>ArithmeticExpression</code> with the unnecessary parenthesis removed.
     */
    private String removeParenthesis(String arithmeticExpression) {
        // Obs: Function under development.

        // Remove first and last parenthesis.
        return arithmeticExpression.substring(1, arithmeticExpression.length() - 1);
    }
}
