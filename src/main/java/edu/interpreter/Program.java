package edu.interpreter;

import edu.interpreter.controller.Controller;
import edu.interpreter.examples.Laboratory8;
import edu.interpreter.view.ConsoleMenu;
import edu.interpreter.view.commands.ExitCommand;
import edu.interpreter.view.commands.RunExampleCommand;

/**
 * The main entry point for the application.
 * @author David Perisanu
 */
public class Program {
    public static void main(String[] args) {
        ConsoleMenu menu;
        Controller ex1Ctrl;

        menu = new ConsoleMenu();
        ex1Ctrl = Laboratory8.example1();

        menu.add(new RunExampleCommand("1", "fork example", ex1Ctrl));
        menu.add(new ExitCommand("8", "Exit"));

        menu.show();
    }
}
