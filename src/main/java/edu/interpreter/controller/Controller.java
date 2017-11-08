package edu.interpreter.controller;

import edu.interpreter.model.ProgramState;
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
     * Executes the top-most <code>Statement</code> of the current <code>ProgramState</code>.
     */
    public void executeOneStep() {
        ProgramState programState = repository.getProgramState();

        programState.executionStack().popBack().execute(programState);
    }

    /**
     * Executes every <code>Statement</code> of the current <code>ProgramState</code>.
     */
    public void executeAllSteps() {
        ProgramState programState = repository.getProgramState();

        System.out.println(programState);
        
        while (!programState.executionStack().isEmpty()) {
            System.out.println("Executed statement: " + programState.executionStack().back());

            executeOneStep();

            System.out.println(programState);
        }
    }
}
