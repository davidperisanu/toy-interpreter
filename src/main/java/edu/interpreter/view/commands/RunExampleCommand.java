package edu.interpreter.view.commands;

import edu.interpreter.controller.Controller;

/**
 * Represents an example command.
 * @author David Perisanu
 */
public class RunExampleCommand extends Command {
    private Controller controller;

    /**
     * Initializes a new instance of the <code>RunExampleCommand</code> class with the specified values.
     * @param key Command key.
     * @param description Command description.
     * @param controller Command controller.
     */
    public RunExampleCommand(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }

    /**
     * Executes the <code>RunExampleCommand</code>.
     */
    @Override
    public void execute() {
        try {
            controller.executeAllSteps();
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
