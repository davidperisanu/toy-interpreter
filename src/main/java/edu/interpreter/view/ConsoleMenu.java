package edu.interpreter.view;

import java.util.Scanner;

import edu.interpreter.model.utilities.Dictionary;
import edu.interpreter.model.utilities.exceptions.InvalidArgumentException;
import edu.interpreter.model.utilities.interfaces.IDictionary;
import edu.interpreter.view.commands.Command;

/**
 * Represents a console-based menu.
 * @author David Perisanu
 */
public class ConsoleMenu {
    IDictionary<String, Command> commands;

    /**
     * Initializes a new instance of the <code>ConsoleMenu<></code> class that is empty. 
     */
    public ConsoleMenu() {
        commands = new Dictionary<>();
    }

    /**
     * Adds a <code>Command</code> to the <code>ConsoleMenu</code>.
     */
    public void add(Command command) {
        commands.add(command.key(), command);
    }

    /**
     * Starts the console-based application.
     */
    public void show() {
        Scanner scanner;
        String key;

        scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            System.out.print(">> ");

            key = scanner.nextLine();
            try {
                commands.get(key).execute();
            }
            catch (InvalidArgumentException e) {
                System.out.println();
                System.out.println("Invalid option.");
            }
        }
    }

    /**
     * Prints on the console menu options.
     */
    private void printMenu() {
        for (Command command : commands.allValues())
            System.out.println(String.format("  %s. %s", command.key(),command.description()));
    }
}
