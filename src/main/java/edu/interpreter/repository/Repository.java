package edu.interpreter.repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.ListIterator;

import edu.interpreter.model.ProgramState;
import edu.interpreter.model.statements.Statement;
import edu.interpreter.model.utilities.List;
import edu.interpreter.model.utilities.interfaces.IList;

/**
 * Represents a <code>ProgramState</code> repository.
 * @author David Perisanu
 */
public class Repository implements IRepository {
    private IList<ProgramState> container;
    private String logFilePath;
    private static String logFileSplitter = "-----------------------------------------------";

    /**
     * Initializes a new instance of the <code>Repository</code> class that is empty and has the default initial capacity.
     */
    public Repository() {
        container = new List<>();
        logFilePath = "";
    }

    /**
     * Initializes a new instance of the <code>Repository</code> class that is empty and has the specified logging file path.
     * @param logFilePath The path for the logging file.
     */
    public Repository(String logFilePath) {
        container = new List<>();
        this.logFilePath = logFilePath;
    }

    /**
     * Initializes a new instance of the <code>Repository</code> class that is empty and has the specified inital capacity.
     * @param capacity The number of elements that the new repository can initially store.
     */
    public Repository(int capacity) {
        container = new List<>(capacity);
        logFilePath = "";
    }

    /**
     * Gets the path of the logging file.
     * @return The path of the logging file.
     */
    @Override
    public String logFilePath() {
        return logFilePath;
    }

    /**
     * Sets the path for the logging file.
     * @param logFilePath The path for the logging file.
     */
    @Override
    public void logFilePath(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    /**
     * Adds a <code>ProgramState</code> to the <code>Repository</code>.
     * @param programState The <code>ProgramState</code> to add to the <code>Repository</code>.
     */
    @Override
    public void add(ProgramState programState) {
        container.add(programState);
    }

    /**
     * Logs a header for the <code>ProgramState</code>.
     * @param programState The <code>ProgramState</code>.
     * @throws FileNotFoundException if the file path is not valid.
     * @throws IOException if the named file exists but is a directory rather than a regular file, does not exist but cannot be created, or cannot be opened for any other reason.
     */
    @Override
    public void logProgramStateExecutionHeader(ProgramState programState) throws FileNotFoundException, IOException {
        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)))) {
            String str;
            
            // If the repository log file has content => add two empty lines to split program states (for the visual effect).
            if (new File(logFilePath).length() > 0) {
                printWriter.println();
                printWriter.println();
            }

            // Add ProgramState header.
            str = "Program state (" + programState.id() + ")";
            printWriter.println(logFileSplitter);
            printWriter.println(String.join("", Collections.nCopies((logFileSplitter.length() - str.length()) / 2, " ")) + str);   // Make sure 'Program state (<ID>)' will be centered.
            printWriter.println("             [" + (new SimpleDateFormat("MM/dd/yyyy hh:mm:a")).format(new Date()) + "]");
            printWriter.println(logFileSplitter);
        }
        catch (FileNotFoundException e) {
            throw new FileNotFoundException("Logging file path is not valid.");
        }
    }

    /**
     * Logs the execution state of the current <code>ProgramState</code>.
     * @throws FileNotFoundException if the file path is not valid.
     * @throws IOException if the logging file exists but is a directory rather than a regular file, does not exist but cannot be created, or cannot be opened for any other reason.
     */
    @Override
    public void logProgramStateExecution(ProgramState programState) throws FileNotFoundException, IOException {
        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)))) {
            ListIterator<Statement> execStackIterator;
            ListIterator<String> symTableIterator, outputMsgsIterator;
            ListIterator<Integer> fileTableIterator, heapIterator;
            String empty, categorySpace, categoryChildSpace;

            execStackIterator = programState.executionStack().iteratorBack();
            symTableIterator = programState.symbolTable().keysIterator();
            outputMsgsIterator = programState.outputMessages().iterator();
            fileTableIterator = programState.fileTable().keysIterator();
            heapIterator = programState.heap().keysIterator();
            empty = "= Empty =";
            categorySpace = "  ";
            categoryChildSpace = "    ";

            // Print execution stack.
            printWriter.println(categorySpace + "Execution stack:");
            if (!execStackIterator.hasPrevious())
                printWriter.println(categoryChildSpace + empty);
            else
                do
                    printWriter.println(categoryChildSpace + execStackIterator.previous());
                while (execStackIterator.hasPrevious());

            // Empty line for the visual effect.
            printWriter.println();

            // Print symbol table.
            printWriter.println(categorySpace + "Symbol table:");
            if (!symTableIterator.hasNext())
                printWriter.println(categoryChildSpace + empty);
            else {
                String key;

                do {
                    key = symTableIterator.next();
                    printWriter.println(categoryChildSpace + key + " -> " + programState.symbolTable().get(key));
                }
                while (symTableIterator.hasNext());
            }

            // Empty line for the visual effect.
            printWriter.println();

            // Print output messages list.
            printWriter.println(categorySpace + "Output messages:");
            if (!outputMsgsIterator.hasNext())
                printWriter.println(categoryChildSpace + empty);
            else
                do
                    printWriter.println(categoryChildSpace + outputMsgsIterator.next());
                while (outputMsgsIterator.hasNext());

            // Empty line for the visual effect.
            printWriter.println();

            // Print file table.
            printWriter.println(categorySpace + "File table:");
            if (!fileTableIterator.hasNext())
                printWriter.println(categoryChildSpace + empty);
            else {
                Integer key;

                do {
                    key = fileTableIterator.next();
                    printWriter.println(categoryChildSpace + key + " -> " + programState.fileTable().get(key).toString().replace("(", "(\"").replace(", ", "\", "));
                }
                while (fileTableIterator.hasNext());
            }

            // Empty line for the visual effect.
            printWriter.println();

            // Print heap.
            printWriter.println(categorySpace + "Heap:");
            if (!heapIterator.hasNext())
                printWriter.println(categoryChildSpace + empty);
            else {
                Integer key;

                do {
                    key = heapIterator.next();
                    printWriter.println(categoryChildSpace + key + " -> " + programState.heap().get(key));
                }
                while (heapIterator.hasNext());
            }
            
            // Separator for the visual effect.
            printWriter.println(logFileSplitter);
        }
        catch (FileNotFoundException e) {
            throw new FileNotFoundException("Logging file path is not valid.");
        }
    }

    /**
     * Logs a header for the <code>ProgramState</code>.
     * @param programState The <code>ProgramState</code>.
     * @throws FileNotFoundException if the file path is not valid.
     * @throws IOException if the named file exists but is a directory rather than a regular file, does not exist but cannot be created, or cannot be opened for any other reason.
     */
    @Override
    public void logProgramStateExecutionFooter(ProgramState programState) throws FileNotFoundException, IOException {
        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)))) {
            String str = "End of the program state (" + programState.id() + ")";

            printWriter.println(String.join("", Collections.nCopies((logFileSplitter.length() - str.length()) / 2, " ")) + str);    // Make sure 'End of the program state (<ID>)' is centered.
            printWriter.println(logFileSplitter);
        }
        catch (FileNotFoundException e) {
            throw new FileNotFoundException("Logging file path is not valid.");
        }
    }

    /**
     * Gets a list of all the <code>ProgramState</code> instances contained.
     * @return A list of all the <code>ProgramState</code> instances contained.
     */
    @Override
    public IList<ProgramState> programStates() {
        return container;
    }

    /**
     * Sets the list of the <code>ProgramState</code> instances with a given one.
     * @param states List of <code>ProgramState</code> instances.
     */
    @Override
    public void programStates(IList<ProgramState> states) {
        container = states;
    }
}
