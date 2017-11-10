package edu.interpreter;

import edu.interpreter.controller.Controller;
import edu.interpreter.examples.Laboratory5;
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
        Controller ex1Ctrl, ex2Ctrl, ex3Ctrl, ex4Ctrl;

        menu = new ConsoleMenu();
        ex1Ctrl = Laboratory5.example1();
        ex2Ctrl = Laboratory5.example2();
        ex3Ctrl = Laboratory5.example3();
        ex4Ctrl = Laboratory5.example4();

        menu.add(new RunExampleCommand("1", ex1Ctrl.repository().getProgramState().executionStack().toString().replace(",", ""), ex1Ctrl));
        menu.add(new RunExampleCommand("2", ex2Ctrl.repository().getProgramState().executionStack().toString().replace(",", ""), ex2Ctrl));
        menu.add(new RunExampleCommand("3", ex3Ctrl.repository().getProgramState().executionStack().toString().replace(",", ""), ex3Ctrl));
        menu.add(new RunExampleCommand("4", ex4Ctrl.repository().getProgramState().executionStack().toString().replace(",", ""), ex4Ctrl));
        menu.add(new ExitCommand("5", "Exit"));

        menu.show();
    }
}
