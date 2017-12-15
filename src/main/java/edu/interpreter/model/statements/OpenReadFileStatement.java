package edu.interpreter.model.statements;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import edu.interpreter.model.ProgramState;
import edu.interpreter.model.utilities.IdGenerator;
import edu.interpreter.model.utilities.Pair;
import edu.interpreter.model.utilities.exceptions.FileOpenedException;
import edu.interpreter.model.utilities.exceptions.InvalidOperationException;
import edu.interpreter.model.utilities.exceptions.NotFoundException;
import edu.interpreter.model.utilities.interfaces.IDictionary;
import edu.interpreter.model.utilities.interfaces.IFileTable;

/**
 * Represents a statement that opens a file for reading.
 * @author David Perisanu
 */
public class OpenReadFileStatement extends Statement {
    private String variableName;
    private String filePath;

    /**
     * Initializes a new instance of the <code>OpenReadFileStatement</code> class with the specified values.
     * @param variableName The name of the variable.
     * @param filePath Path to the file.
     */
    public OpenReadFileStatement(String variableName, String filePath) {
        this.variableName = variableName;
        this.filePath = filePath;
    }

    /**
     * Executes the <code>OpenReadFileStatement</code>.
     * @param programState The program state before the execution of the <code>OpenReadFileStatement</code>.
     * @return The program state after the execution of the <code>OpenReadFileStatement</code>.
     * @throws FileOpenedException if the file is already opened.
     * @throws InvalidOperationException if the there is already a variable with the same name inside the symbol table.
     * @throws NotFoundException if the file path is not valid.
     */
    @Override
    public ProgramState execute(ProgramState programState) throws FileOpenedException, NotFoundException {
        IFileTable<Integer, Pair<String, BufferedReader>> fileTable;
        IDictionary<String, Integer> symbolTable;
        BufferedReader bufferedReader;
        int id;

        symbolTable = programState.symbolTable();
        fileTable = programState.fileTable();

        if (exists(fileTable, filePath))
            throw new FileOpenedException("File already opened.");
        // if (symbolTable.contains(variableName))
        //     throw new InvalidOperationException("There already exists a variable with the same name.");

        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
        }
        catch (FileNotFoundException e) {
            throw new NotFoundException("File path is invalid.");
        }
        id = IdGenerator.generateId();
        
        fileTable.add(id, new Pair<String, BufferedReader>(filePath, bufferedReader));
        symbolTable.add(variableName, id);

        return null;
    }

     /**
     * Gets a string representation of the <code>OpenReadFileStatement</code>.
     * @return The string representation of the <code>OpenReadFileStatement</code>.
     */
    @Override
    public String toString() {
        return "openReadFile(" + variableName + ", \"" + filePath + "\");";
    }

    /**
     * Checks if a file path exists inside a <code>IFileTable<></code>
     * @param fileTable <code>IFileTable</code> instance.
     * @param filePath Path to the file.
     * @return True if the path was found, false otherwise.
     */
    private boolean exists(IFileTable<Integer, Pair<String, BufferedReader>> fileTable, String filePath) {
        for (Pair<String, BufferedReader> value : fileTable.allValues())
            if (value.left().equals(filePath))
                return true;

        return false;
    }
}
