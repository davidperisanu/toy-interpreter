package edu.interpreter.repository;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.interpreter.model.ProgramState;
import edu.interpreter.model.utilities.exceptions.InvalidOperationException;

/**
 * Represents a generic <code>ProgramState</code> repository.
 * @author David Perisanu
 */
public interface IRepository {
    /**
     * Adds a <code>ProgramState</code> to the <code>IRepository</code>.
     * @param programState The <code>ProgramState</code> to add to the <code>IRepository</code>.
     */
    public void add(ProgramState programState);

    /**
     * Gets the current <code>ProgramState</code>.
     * @return The current <code>ProgramState</code>.
     * @throws InvalidOperationException if the <code>IRepository</code> is empty.
     */
    public ProgramState getProgramState() throws InvalidOperationException;

    /**
     * Gets the path of the logging file.
     * @return The path of the logging file.
     */
    public String logFilePath();

    /**
     * Sets the path for the logging file.
     * @param logFilePath The path for the logging file.
     */
    public void logFilePath(String logFilePath);

    /**
     * Logs a header for the <code>ProgramState</code>.
     * @throws FileNotFoundException if the file path is not valid.
     * @throws IOException if the named file exists but is a directory rather than a regular file, does not exist but cannot be created, or cannot be opened for any other reason.
     */
    public void logProgramStateExecutionHeader() throws FileNotFoundException, IOException;

    /**
     * Logs the execution state of the current <code>ProgramState</code>.
     * @throws FileNotFoundException if the file path is not valid.
     * @throws IOException if the named file exists but is a directory rather than a regular file, does not exist but cannot be created, or cannot be opened for any other reason.
     */
    public void logProgramStateExecution() throws FileNotFoundException, IOException;

    /**
     * Logs a header for the <code>ProgramState</code>.
     * @throws FileNotFoundException if the file path is not valid.
     * @throws IOException if the named file exists but is a directory rather than a regular file, does not exist but cannot be created, or cannot be opened for any other reason.
     */
    public void logProgramStateExecutionFooter() throws FileNotFoundException, IOException;
}
