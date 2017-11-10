package edu.interpreter.model.statements;

import edu.interpreter.model.ProgramState;
import edu.interpreter.model.expressions.Expression;
import edu.interpreter.model.utilities.interfaces.IDictionary;
import edu.interpreter.model.utilities.interfaces.IList;

/**
 * Represents a print statement.
 * @author David Perisanu
 */
public final class PrintStatement extends Statement {
    private Expression expression;

    /**
     * Initializes a new instance of the <code>PrintStatement</code> class with the specified value.
     * @param expression The <code>Expression</code> to print.
     */
    public PrintStatement(Expression expression) {
        this.expression = expression;
    }

    /**
     * Executes the <code>PrintStatement</code>.
     * @param programState The program state before the execution of the <code>PrintStatement</code>.
     * @return The program state after the execution of the <code>PrintStatement</code>.
     */
    @Override
    public ProgramState execute(ProgramState programState) {
        IDictionary<String, Integer> symbolTable;
        IList<String> outputMessages;

        symbolTable = programState.symbolTable();
        outputMessages = programState.outputMessages();

        outputMessages.add(Integer.toString(expression.evaluate(symbolTable, programState.heap())));

        return programState;
    }

    /**
     * Gets a string representation of the <code>PrintStatement</code>.
     * @return The string representation of the <code>PrintStatement</code>.
     */
    @Override
    public String toString() {
        return "print(" + expression.toString() + ");";
    }
}
