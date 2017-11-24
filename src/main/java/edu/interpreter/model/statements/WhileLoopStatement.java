package edu.interpreter.model.statements;

import edu.interpreter.model.ProgramState;
import edu.interpreter.model.expressions.Expression;

/**
 * Represents a while loop statement.
 * @author David Perisanu
 */
public class WhileLoopStatement extends Statement {
    private Expression expression;
    private Statement statement;

    /**
     * Initializes a new instance of the <code>ConditionalStatement</code> class with the specified values.
     * @param expression <code>Expression</code> to evaluate.
     * @param statement <code>Statement</code> to be executed if the expression is true.
     */
    public WhileLoopStatement(Expression expression, Statement statement) {
        this.expression = expression;
        this.statement = statement;
    }

    /**
     * Executes the <code>WhileLoopStatement</code>.
     * @param programState The program state before the execution of the <code>WhileLoopStatement</code>.
     * @return The program state after the execution of the <code>WhileLoopStatement</code>.
     */
    @Override
    public ProgramState execute(ProgramState programState) {
        if (expression.evaluate(programState.symbolTable(), programState.heap()) != 0) {
            programState.executionStack().pushBack(this);   // Push WhileLoopStatement back on the stack
            programState.executionStack().pushBack(statement);
        }

        return programState;
    }

    /**
     * Gets a string representation of the <code>WhileLoopStatement</code>.
     * @return The string representation of the <code>WhileLoopStatement</code>.
     */
    @Override
    public String toString() {
        return "while (" + expression + ") { " + statement + " }";
    }
}
