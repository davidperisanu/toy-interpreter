package edu.interpreter.model.statements;

import edu.interpreter.model.ProgramState;
import edu.interpreter.model.utilities.interfaces.ILatchTable;

public class LatchCountDownStatement extends Statement {
    private String variableName;

    /**
     * Initializes a new instance of the <code>LatchCountDownStatement</code> class with the specified value.
     * @param varableName Variable name that refers the latch alocated space.
     */
    public LatchCountDownStatement(String variableName) {
        this.variableName = variableName;
    }

    /**
     * Executes the <code>LatchCountDownStatement</code>.
     * @param programState The program state before the execution of the <code>LatchCountDownStatement</code>.
     * @return The program state after the execution of the <code>LatchCountDownStatement</code>.
     */
    @Override
    public ProgramState execute(ProgramState programState) {
        int foundIndex;
        ILatchTable<Integer, Integer> latchTable;

        foundIndex = programState.symbolTable().get(variableName);
        latchTable = programState.latchTable();

        synchronized (latchTable) {
            if (latchTable.get(foundIndex) > 0) {
                latchTable.add(foundIndex, latchTable.get(foundIndex) - 1);
                programState.outputMessages().add(programState.id() + "");
            }
        }

        return null;
    }

    /**
     * Gets a string representation of the <code>LatchCountDownStatement</code>.
     * @return The string representation of the <code>LatchCountDownStatement</code>.
     */
    @Override
    public String toString() {
        return "countDown(" + variableName + ");";
    }
}
