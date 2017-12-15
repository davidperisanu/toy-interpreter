package edu.interpreter.model.statements;

import java.io.IOException;

import edu.interpreter.model.ProgramState;
import edu.interpreter.model.utilities.DeepCopy;
import edu.interpreter.model.utilities.Deque;
import edu.interpreter.model.utilities.exceptions.InvalidOperationException;
import edu.interpreter.model.utilities.interfaces.IDictionary;

/**
 * Represents a fork statement.
 * @author David Perisanu
 */
public class ForkStatement extends Statement {
    private Statement statement;

    /**
     * Initializes a new instance of the <code>ForkStatement</code> class with the specified value.
     * @param statement <code>Statement</code> to be executed by the process.
     */
    public ForkStatement(Statement statement) {
        this.statement = statement;
    }

    /**
     * Executes the <code>ForkStatement</code>.
     * @param programState The program state before the execution of the <code>ForkStatement</code>.
     * @return The program state after the execution of the <code>ForkStatement</code>.
     */
    @SuppressWarnings("unchecked")
    @Override
    public ProgramState execute(ProgramState programState) {
        ProgramState forkProgramState;
        Deque<Statement> forkExecutionStack;

        forkExecutionStack = new Deque<>();
        forkExecutionStack.pushFront(statement);
        try {
            forkProgramState = new ProgramState(forkExecutionStack, (IDictionary<String, Integer>)DeepCopy.copy(programState.symbolTable()), programState.outputMessages(), programState.fileTable(), programState.heap());
        }
        catch (IOException e) {
            throw new InvalidOperationException("I/O exception. Object could not be serialized.");
        }
        catch (ClassNotFoundException e) {
            throw new InvalidOperationException("Class not found. Object could not be serialized.");
        }

        return forkProgramState;
    }

    /**
     * Gets a string representation of the <code>ForkStatement</code>.
     * @return The string representation of the <code>ForkStatement</code>.
     */
    @Override
    public String toString() {
        return "fork(" + statement + ");";
    }
}
