package edu.interpreter.repository;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.interpreter.model.ProgramState;
import edu.interpreter.model.utilities.interfaces.IList;

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
     * Logs the header for a <code>ProgramState</code>.
     * @param programState The <code>ProgramState</code>.
     * @throws FileNotFoundException if the file path is not valid.
     * @throws IOException if the named file exists but is a directory rather than a regular file, does not exist but cannot be created, or cannot be opened for any other reason.
     */
    public void logProgramStateExecutionHeader(ProgramState programState) throws FileNotFoundException, IOException;

    /**
     * Logs the execution state of a <code>ProgramState</code>.
     * @param programState The <code>ProgramState</code>.
     * @throws FileNotFoundException if the file path is not valid.
     * @throws IOException if the named file exists but is a directory rather than a regular file, does not exist but cannot be created, or cannot be opened for any other reason.
     */
    public void logProgramStateExecution(ProgramState programState) throws FileNotFoundException, IOException;

    /**
     * Logs the footer for a <code>ProgramState</code>.
     * @param programState The <code>ProgramState</code>.
     * @throws FileNotFoundException if the file path is not valid.
     * @throws IOException if the named file exists but is a directory rather than a regular file, does not exist but cannot be created, or cannot be opened for any other reason.
     */
    public void logProgramStateExecutionFooter(ProgramState programState) throws FileNotFoundException, IOException;

    /**
     * Gets a list of all the <code>ProgramState</code> instances contained.
     * @return A list of all the <code>ProgramState</code> instances contained.
     */
    public IList<ProgramState> programStates();

    /**
     * Sets the list of the <code>ProgramState</code> instances with a given one.
     * @param states List of <code>ProgramState</code> instances.
     */
    public void programStates(IList<ProgramState> states);
}
