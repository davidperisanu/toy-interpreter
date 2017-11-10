package edu.interpreter.view.commands;

import java.util.Scanner;

/**
 * Represents a console menu command.
 * @author David Perisanu
 */
public abstract class Command {
    private String key, description;

    /**
     * Initializes a new instance of the <code>Command</code> class with the default values.
     */
    public Command(String key, String description) {
        this.key = key;
        this.description = description;
    }

    /**
     * Gets command key value.
     * @return Command key value.
     */
    public String key() {
        return key;
    }

    /**
     * Gets command description value.
     * @return Command description value.
     */
    public String description() {
        return description;
    }

    /**
     * Executes the <code>Command</code>.
     * @param scanner Console input scanner.
     */
    public abstract void execute(Scanner scanner);
}
