package edu.interpreter.model.statements;

import edu.interpreter.model.ProgramState;
import edu.interpreter.model.expressions.Expression;
import edu.interpreter.model.utilities.IdGenerator;

/**
 * Represents a latch allocation statement.
 * @author David Perisanu
 */
public class LatchAllocationStatement extends Statement {
    private String variableName;
    private Expression expression;

    /**
     * Initializes a new instance of the <code>LatchAllocationStatement</code> class with the specified values.
     * @param variableName Variable name to refer the latch alocated space.
     * @param expression <code>Expression</code> to evaluate.
     */
    public LatchAllocationStatement(String variableName, Expression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    /**
     * Executes the <code>LatchAllocationStatement</code>.
     * @param programState The program state before the execution of the <code>LatchAllocationStatement</code>.
     * @return The program state after the execution of the <code>LatchAllocationStatement</code>.
     */
    @Override
    public ProgramState execute(ProgramState programState) {
        int result = expression.evaluate(programState.symbolTable(), programState.heap());

        synchronized (programState.latchTable()) {
            int location = IdGenerator.generateId();

            programState.latchTable().add(location, result);

            programState.symbolTable().add(variableName, location);
        }

        return null;
    }

    /**
     * Gets a string representation of the <code>LatchAllocationStatement</code>.
     * @return The string representation of the <code>LatchAllocationStatement</code>.
     */
    @Override
    public String toString() {
        return "newLatch(" + variableName + ", " + expression + ");";
    }
}
