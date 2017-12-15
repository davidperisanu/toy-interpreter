package edu.interpreter.model.statements;

import java.io.BufferedReader;
import java.io.IOException;

import edu.interpreter.model.ProgramState;
import edu.interpreter.model.expressions.Expression;
import edu.interpreter.model.utilities.Pair;
import edu.interpreter.model.utilities.exceptions.InvalidOperationException;
import edu.interpreter.model.utilities.exceptions.NotFoundException;
import edu.interpreter.model.utilities.interfaces.IFileTable;

public class CloseReadFileStatement extends Statement {
    private Expression expression;

    /**
     * Initializes a new instance of the <code>CloseReadFileStatement</code> class with the specified value.
     * @param expression File table descriptor.
     */
    public CloseReadFileStatement(Expression expression) {
        this.expression = expression;
    }

    /**
     * Executes the <code>CloseReadFileStatement</code>.
     * @param programState The program state before the execution of the <code>CloseReadFileStatement</code>.
     * @return The program state after the execution of the <code>CloseReadFileStatement</code>.
     * @throws NotFoundException if the file could not be found inside the <code>IFileTable<></code>.
     * @throws InvalidOperationException if the file could not be closed.
     */
    @Override
    public ProgramState execute(ProgramState programState) throws NotFoundException, InvalidOperationException {
        IFileTable<Integer, Pair<String, BufferedReader>> fileTable;
        int fileDescriptor;

        fileTable = programState.fileTable();
        fileDescriptor = expression.evaluate(programState.symbolTable(), programState.heap());
        
        if (!fileTable.contains(fileDescriptor))
            throw new NotFoundException("File not found.");

        try {
            // Close the BufferedReader instance.
            fileTable.get(fileDescriptor).right().close();
        }
        catch (IOException e) {
            throw new InvalidOperationException("Could not close the file.");
        }
        fileTable.remove(fileDescriptor);

        return null;
    }

    /**
     * Gets a string representation of the <code>CloseReadFileStatement</code>.
     * @return The string representation of the <code>CloseReadFileStatement</code>.
     */
    @Override
    public String toString() {
        return "closeReadFile(" + expression + ");";
    }
}
