package edu.interpreter.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

import edu.interpreter.model.ProgramState;
import edu.interpreter.model.utilities.Heap;
import edu.interpreter.model.utilities.Pair;
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
     * @throws IOException if the repository log file exists but is a directory rather than a regular file, does not exist but cannot be created, cannot be opened for any other reason, or file could not be closed.
     */
    public void executeAllSteps() throws FileNotFoundException, IOException {
        ProgramState programState = repository.getProgramState();

        System.out.println(programState);

        // If the repository has a log file.
        if (repository.logFilePath().length() > 0) {
            // Add program state header.
            repository.logProgramStateExecutionHeader();
        
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
            repository.logProgramStateExecutionFooter();

            closeFiles(programState);
    }

    /**
     * Constructs an <code>IHeap</code> that contains only the refered values.
     * @param @symbolTableValues A collection of all the values from symbol table.
     * @param heap Heap of the <code>ProgramState</code>.
     * @return The constructed <code>IHeap</code>.
     */
    private IHeap<Integer, Integer> garbageCollector(Collection<Integer> symbolTableValues, IHeap<Integer, Integer> heap) {
        IHeap<Integer, Integer> newHeap = new Heap<>(heap.size());

        // for (Integer key : heap.allKeys())
        //     if (symbolTableValues.contains(key))
        //         newHeap.add(key, heap.get(key));
        heap.entries().stream().filter(pair -> symbolTableValues.contains(pair.right())).forEach(pair -> newHeap.add(pair.left(), pair.right()));

        return newHeap;
    }

    /**
     * Closes all open files of a <code>ProgramState</code> instance.
     * @param programState The <code>ProgramState</code> instance.
     * @throws IOException if any file could not be closed;
     */
    private void closeFiles(ProgramState programState) throws IOException {
        long count = programState.fileTable().entries().stream().filter(
            pair -> {
                try {
                    pair.right().right().close();
                    programState.fileTable().remove(pair.left());
                    return false;
                }
                catch(IOException e) {
                    programState.fileTable().remove(pair.left());
                    return true;
                }
            }).count();

        if (count == 1)
            throw new IOException("One file could not be closed.");
        else if (count > 1)
            throw new IOException(count + " files could not closed.");
    }
}
