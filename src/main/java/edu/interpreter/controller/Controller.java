package edu.interpreter.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import edu.interpreter.model.ProgramState;
import edu.interpreter.model.utilities.Heap;
import edu.interpreter.model.utilities.interfaces.IHeap;
import edu.interpreter.repository.IRepository;
import edu.interpreter.repository.Repository;

/**
 * Represents a <code>ProgramState</code> controller.
 * @author David Perisanu
 */
public class Controller {
    IRepository repository;

    /**
     * Initializes a new instance of the <code>Controller</code> class that has the default value.
     */
    public Controller() {
        repository = new Repository();
    }

    /**
     * Initializes a new instance of the <code>Controller</code> class with the specified value.
     * @param repository A <code>ProgramState</code> repository.
     */
    public Controller(IRepository repository) {
        this.repository = repository;
    }

    /**
     * Gets the <code>IRepository</code> of the controller.
     * @return The <code>IRepository</code> of the controller.
     */
    public IRepository repository() {
        return repository;
    }

    /**
     * Executes the top-most <code>Statement</code> of the current <code>ProgramState</code>.
     */
    public void executeOneStep() {
        ProgramState programState = repository.getProgramState();

        programState.executionStack().popBack().execute(programState);
    }

    /**
     * Executes every <code>Statement</code> of the current <code>ProgramState</code>.
     * @throws FileNotFoundException if the logging file path is not valid.
     * @throws IOException if the repository log file exists but is a directory rather than a regular file, does not exist but cannot be created, or cannot be opened for any other reason.
     */
    public void executeAllSteps() throws FileNotFoundException, IOException {
        ProgramState programState = repository.getProgramState();

        System.out.println(programState);

        // If the repository has a log file.
        if (repository.logFilePath().length() > 0) {
            try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(repository.logFilePath(), true)))) {
                // If the repository log file has content => add two empty lines to split program states (for the visual effect).
                if (new File(repository.logFilePath()).length() > 0) {
                        printWriter.println();
                        printWriter.println();
                }

                // Add program state header.
                printWriter.println("-----------------------------------------------");
                printWriter.println("                 Program state");
                printWriter.println("             [" + (new SimpleDateFormat("MM/dd/yyyy hh:mm:a")).format(new Date()) + "]");
                printWriter.println("-----------------------------------------------");
            }
        
            // Log initial program state representation.
            repository.logProgramStateExecution();
        }
        
        while (!programState.executionStack().isEmpty()) {
            System.out.println("Executed statement: " + programState.executionStack().back());

            executeOneStep();

            // Get rid of the garbage (heap addresses that are not refered by any symbol table pair).
            programState.heap(garbageCollector(programState.symbolTable().allValues(), programState.heap()));

            System.out.println(programState);
            if (repository.logFilePath().length() > 0)
                // Log current program state representation.
                repository.logProgramStateExecution();
        }

        if (repository.logFilePath().length() > 0)
            // Add program state footer.
            try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(repository.logFilePath(), true)))) {
                printWriter.println("           End of the program state");
                printWriter.println("-----------------------------------------------");
            }
    }

    /**
     * Constructs an <code>IHeap</code> that contains only the refered values.
     * @return The constructed <code>IHeap</code>.
     */
    private IHeap<Integer, Integer> garbageCollector(Collection<Integer> symbolTableValues, IHeap<Integer, Integer> heap) {
        IHeap<Integer, Integer> newHeap = new Heap<>(heap.size());

        for (Integer key : heap.allKeys())
            if (symbolTableValues.contains(key))
                newHeap.add(key, heap.get(key));

        return newHeap;
    }
}
