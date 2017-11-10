package edu.interpreter.repository;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.ListIterator;

import edu.interpreter.model.ProgramState;
import edu.interpreter.model.statements.Statement;
import edu.interpreter.model.utilities.exceptions.IndexOutOfRangeException;
import edu.interpreter.model.utilities.exceptions.InvalidOperationException;

/**
 * Represents a <code>ProgramState</code> repository.
 * @author David Perisanu
 */
public class Repository implements IRepository {
    private ArrayList<ProgramState> container;
    private String logFilePath;

    /**
     * Initializes a new instance of the <code>Repository</code> class that is empty and has the default initial capacity.
     */
    public Repository() {
        container = new ArrayList<>();
        logFilePath = "";
    }

    /**
     * Initializes a new instance of the <code>Repository</code> class that is empty and has the specified logging file path.
     * @param logFilePath The path for the logging file.
     */
    public Repository(String logFilePath) {
        container = new ArrayList<>();
        this.logFilePath = logFilePath;
    }

    /**
     * Initializes a new instance of the <code>Repository</code> class that is empty and has the specified inital capacity.
     * @param capacity The number of elements that the new repository can initially store.
     */
    public Repository(int capacity) {
        container = new ArrayList<>(capacity);
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
     * Logs the execution state of the current <code>ProgramState</code>.
     * @throws FileNotFoundException if the file path is not valid.
     * @throws IOException if the logging file exists but is a directory rather than a regular file, does not exist but cannot be created, or cannot be opened for any other reason.
     */
    @Override
    public void logProgramStateExecution() throws FileNotFoundException, IOException {
        try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)))) {
            ProgramState programState;
            ListIterator<Statement> execStackIterator;
            ListIterator<String> symTableIterator, outputMsgsIterator;
            ListIterator<Integer> fileTableIterator, heapIterator;
            String empty, categorySpace, categoryChildSpace;

            programState = container.get(0);
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
            printWriter.println("-----------------------------------------------");
        }
        catch (FileNotFoundException e) {
            throw new FileNotFoundException("Logging file path is not valid.");
        }
    }

    /**
     * Gets the current <code>ProgramState</code>.
     * @return The current <code>ProgramState</code>.
     * @throws IndexOutOfRangeException if the <code>Repository</code> is empty.
     */
    @Override
    public ProgramState getProgramState() throws InvalidOperationException {
        if (container.isEmpty())
            throw new InvalidOperationException("Repository is empty.");
        
        return container.get(0);
    }
}
