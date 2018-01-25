package edu.interpreter.model.statements;

import edu.interpreter.model.ProgramState;

public class LatchAwaitStatement extends Statement {
    private String variableName;

    /**
     * Initializes a new instance of the <code>LatchAwaitStatement</code> class with the specified value.
     * @param varableName Variable name that refers the latch alocated space.
     */
    public LatchAwaitStatement(String variableName) {
        this.variableName = variableName;
    }

    /**
     * Executes the <code>LatchAwaitStatement</code>.
     * @param programState The program state before the execution of the <code>LatchAwaitStatement</code>.
     * @return The program state after the execution of the <code>LatchAwaitStatement</code>.
     */
    @Override
    public ProgramState execute(ProgramState programState) {
        int foundIndex = programState.symbolTable().get(variableName);

        if (programState.latchTable().get(foundIndex) != 0)
            programState.executionStack().pushBack(this);

        return null;
    }

    /**
     * Gets a string representation of the <code>LatchAwaitStatement</code>.
     * @return The string representation of the <code>LatchAwaitStatement</code>.
     */
    @Override
    public String toString() {
        return "await(" + variableName + ");";
    }
}
