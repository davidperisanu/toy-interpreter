package edu.interpreter;

import edu.interpreter.view.ConsoleMenu;
import edu.interpreter.view.commands.ExitCommand;

/**
 * The main entry point for the application.
 * @author David Perisanu
 */
public class Program {
    public static void main(String[] args) {
        ConsoleMenu menu;

        menu = new ConsoleMenu();

        menu.add(new ExitCommand("1", "Exit"));

        menu.show();
    }
}
