package edu.interpreter.model.statements;

import edu.interpreter.model.ProgramState;
import edu.interpreter.model.expressions.Expression;
import edu.interpreter.model.utilities.IDeque;

/**
 * Represents a conditional statement.
 * @author David Perisanu
 */
public final class ConditionalStatement extends Statement {
    private Expression expression;
    private Statement ifStatement;
    private Statement elseStatement;

    /**
     * Initializes a new instance of the <code>ConditionalStatement</code> class that has the default values.
     */
    public ConditionalStatement() {
        expression = null;
        ifStatement = null;
        elseStatement = null;
    }

    /**
     * Initializes a new instance of the <code>ConditionalStatement</code> class with the specified values.
     * @param expression <code>Expression</code> to evaluate.
     * @param ifStatement <code>Statement</code> to be executed if the expression is true.
     * @param elseStatement <code>Statement</code> to be executed if the expression is false.
     */
    public ConditionalStatement(Expression expression, Statement ifStatement, Statement elseStatement) {
        this.expression = expression;
        this.ifStatement = ifStatement;
        this.elseStatement = elseStatement;
    }

    /**
     * Executes the <code>ConditionalStatement</code>.
     * @param programState The program state before the execution of the <code>ConditionalStatement</code>.
     * @return The program state after the execution of the <code>ConditionalStatement</code>.
     */
    @Override
    public ProgramState execute(ProgramState programState) {
        int condition;
        IDeque<Statement> executionStack;

        condition = expression.evaluate(programState.symbolTable());
        executionStack = programState.executionStack();

        if (condition != 0) // true
            executionStack.pushBack(ifStatement);
        else                // false
            executionStack.pushBack(elseStatement);

        return programState;
    }

    /**
     * Gets a string representation of the <code>ConditionalStatement</code>.
     * @return The string representation of the <code>ConditionalStatement</code>.
     */
    @Override
    public String toString() {
        if (elseStatement == null)
            return "if (" + expression.toString() + ") then { " + ifStatement.toString() + " }";

        return "if (" + expression.toString() + ") then { " + ifStatement.toString() + " } else { " + elseStatement.toString() + " }";
    }
}
