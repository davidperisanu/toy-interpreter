package edu.interpreter.model.statements;

import edu.interpreter.model.ProgramState;
import edu.interpreter.model.expressions.BooleanExpression;
import edu.interpreter.model.expressions.BooleanExpression.RelationalOperator;
import edu.interpreter.model.expressions.ConstantExpression;
import edu.interpreter.model.expressions.Expression;

/**
 * Represents a do until statement.
 * @author David Perisanu
 */
public class DoUntilStatement extends Statement {
    private Statement statement;
    private Expression expression;

    /**
     * Initializes a new instance of the <code>DoUntilStatement</code> class with the specified values.
     * @param statement The <code>Statement</code> to be executed.
     * @param expression The <code>Expression</code> of the loop.
     */
    public DoUntilStatement(Statement statement, Expression expression) {
        this.statement = statement;
        this.expression = expression;
    }

    /**
     * Executes the <code>DoUntilStatement</code>.
     * @param programState The program state before the execution of the <code>DoUntilStatement</code>.
     * @return The program state after the execution of the <code>DoUntilStatement</code>.
     */
    @Override
    public ProgramState execute(ProgramState programState) {
        programState.executionStack().pushBack(new CompoundStatement(statement, new WhileLoopStatement(new BooleanExpression(expression, RelationalOperator.Equal, new ConstantExpression(0)), statement)));

        return null;
    }

    /**
     * Gets a string representation of the <code>DoUntilStatement</code>.
     * @return The string representation of the <code>DoUntilStatement</code>.
     */
    @Override
    public String toString() {
        return "do { " + statement + " } until (" + expression + ");";
    }
}
