package edu.interpreter.model.statements;

import java.io.BufferedReader;
import java.io.IOException;

import edu.interpreter.model.ProgramState;
import edu.interpreter.model.expressions.Expression;
import edu.interpreter.model.utilities.Pair;
import edu.interpreter.model.utilities.exceptions.InvalidOperationException;
import edu.interpreter.model.utilities.exceptions.NotFoundException;
import edu.interpreter.model.utilities.interfaces.IDictionary;
import edu.interpreter.model.utilities.interfaces.IFileTable;

/**
 * Represents a statement that reads from a file.
 * @author David Perisanu
 */
public class ReadFileStatement extends Statement {
    private Expression expression;
    private String variableName;

    /**
     * Initializes a new instance of the <code>ReadFileStatement</code> class with the specified values.
     * @param expression File table descriptor.
     * @param variableName Variable name for the reading output.
     */
    public ReadFileStatement(Expression expression, String variableName) {
        this.expression = expression;
        this.variableName = variableName;
    }

    /**
     * Executes the <code>ReadFileStatement</code>.
     * @param programState The program state before the execution of the <code>ReadFileStatement</code>.
     * @return The program state after the execution of the <code>ReadFileStatement</code>.
     * @throws NotFoundException if the file is not opened.
     * @throws InvalidOperationException if any IOException occurs during the reading.
     */
    @Override
    public ProgramState execute(ProgramState programState) throws NotFoundException, InvalidOperationException {
        IDictionary<String, Integer> symbolTable;
        IFileTable<Integer, Pair<String, BufferedReader>> fileTable;
        int fileDescriptor;
        String line;
        int readInt;

        symbolTable = programState.symbolTable();
        fileTable = programState.fileTable();
        fileDescriptor = expression.evaluate(symbolTable, programState.heap());
        line = null;
        readInt = 0;

        if (!fileTable.contains(fileDescriptor))
            throw new NotFoundException("File not opened.");

        try {
            line = fileTable.get(fileDescriptor).right().readLine();
        }
        catch (IOException e) {
            throw new InvalidOperationException(e.getMessage());
        }

        if (line != null)
            readInt = Integer.parseInt(line);

        symbolTable.add(variableName, readInt);

        return null;
    }

    /**
     * Gets a string representation of the <code>ReadFileStatement</code>.
     * @return The string representation of the <code>ReadFileStatement</code>.
     */
    @Override
    public String toString() {
        return "readFile(" + expression + ", " + variableName + ");";
    }
}
