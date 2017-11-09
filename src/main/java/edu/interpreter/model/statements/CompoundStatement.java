package edu.interpreter.model.statements;

import edu.interpreter.model.ProgramState;
import edu.interpreter.model.utilities.interfaces.IDeque;

/**
 * Represents a compound statement.
 * @author David Perisanu
 */
public final class CompoundStatement extends Statement {
    private Statement firStatement;
    private Statement secondStatement;

    /**
     * Initializes a new instance of the <code>CompoundStatement</code> class with the specified values.
     * @param firStatement First <code>Statement</code> of the <code>CompoundStatement</code>.
     * @param secondStatement Second <code>Statement</code> of the <code>CompoundStatement</code>.
     */
    public CompoundStatement(Statement firStatement, Statement secondStatement) {
        this.firStatement = firStatement;
        this.secondStatement = secondStatement;
    }

    /**
     * Executes the <code>CompoundStatement</code>.
     * @param programState The program state before the execution of the <code>CompoundStatement</code>.
     * @return The program state after the execution of the <code>CompoundStatement</code>.
     */
    @Override
    public ProgramState execute(ProgramState programState) {
        IDeque<Statement> executionStack = programState.executionStack();

        if (secondStatement != null)
            executionStack.pushBack(secondStatement);
        if (firStatement != null)
            executionStack.pushBack(firStatement);

        return programState;
    }

    /**
     * Gets a string representation of the <code>CompoundStatement</code>.
     * @return The string representation of the <code>CompoundStatement</code>.
     */
    @Override
    public String toString() {
        return firStatement.toString() + " " + secondStatement.toString();
    }
}
