package edu.interpreter.view.commands;

import java.util.Scanner;

/**
 * Represents an exit command.
 * @author David Perisanu
 */
public class ExitCommand extends Command {
    /**
     * Initializes a new instance of the <code>ExitCommand</code> class with the specified values.
     * @param key Command key.
     * @param description Command description.
     */
    public ExitCommand(String key, String description) {
        super(key, description);
    }

    /**
     * Executes the <code>ExitCommand</code>.
     */
    @Override
    public void execute(Scanner scanner) {
        System.out.println("Program execution has ended. Have a nice day!");
        scanner.close(); // Close the scanner to avoid memory leaks.
        System.exit(0);
    }
}
