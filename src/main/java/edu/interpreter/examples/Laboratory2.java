package edu.interpreter.examples;

import edu.interpreter.controller.Controller;
import edu.interpreter.model.ProgramState;
import edu.interpreter.model.expressions.ArithmeticExpression;
import edu.interpreter.model.expressions.ArithmeticExpression.ArithmeticOperator;
import edu.interpreter.model.expressions.ConstantExpression;
import edu.interpreter.model.expressions.VariableExpression;
import edu.interpreter.model.statements.AssignmentStatement;
import edu.interpreter.model.statements.PrintStatement;
import edu.interpreter.model.statements.Statement;
import edu.interpreter.model.utilities.Deque;
import edu.interpreter.model.utilities.Dictionary;
import edu.interpreter.model.utilities.List;
import edu.interpreter.model.utilities.interfaces.IDeque;
import edu.interpreter.model.utilities.interfaces.IDictionary;
import edu.interpreter.model.utilities.interfaces.IList;
import edu.interpreter.repository.IRepository;
import edu.interpreter.repository.Repository;

/**
 * Some examples for second laboratory.
 * @author David Perisanu
 */
public final class Laboratory2 {
    /**
     * <code>v = 2;</code>
     * <code>print(v);</code>
     */
    public static void example1() {
        // v = 2;
        Statement assignStmt = new AssignmentStatement("v", new ConstantExpression(2));
        // print(v);
        Statement printStmt = new PrintStatement(new VariableExpression("v"));

        IDeque<Statement> executionStack = new Deque<>();
        IDictionary<String, Integer> symbolTable = new Dictionary<>();
        IList<String> outputMessages = new List<>();
        ProgramState programState = new ProgramState(executionStack, symbolTable, outputMessages, null, null, null);
        IRepository repository = new Repository();
        Controller controller = new Controller(repository);

        executionStack.pushFront(assignStmt);
        executionStack.pushFront(printStmt);
        
        repository.add(programState);

        try {
            controller.executeAllSteps();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * <code>a = 2 + 3 * 5;</code>
     * <code>b = a - 4 / 2 + 7;</code>
     * <code>print(b);</code>
     */
    public static void example2() {
        // a = 2 + 3 * 5;
        Statement assignStmt1 = new AssignmentStatement("a", new ArithmeticExpression(new ConstantExpression(2), ArithmeticOperator.Addition, new ArithmeticExpression(new ConstantExpression(3), ArithmeticOperator.Multiplication, new ConstantExpression(5))));
        // b = a - 4 / 2 + 7;
        Statement assignStmt2 = new AssignmentStatement("b", new ArithmeticExpression(new ArithmeticExpression(new VariableExpression("a"), ArithmeticOperator.Substraction, new ArithmeticExpression(new ConstantExpression(4), ArithmeticOperator.Division, new ConstantExpression(2))), ArithmeticOperator.Addition, new ConstantExpression(7)));
        // print(b);
        Statement printStmt = new PrintStatement(new VariableExpression("b"));

        IDeque<Statement> executionStack = new Deque<>();
        IDictionary<String, Integer> symbolTable = new Dictionary<>();
        IList<String> outputMessages = new List<>();
        ProgramState programState = new ProgramState(executionStack, symbolTable, outputMessages, null, null, null);
        IRepository repository = new Repository();
        Controller controller = new Controller(repository);

        executionStack.pushFront(assignStmt1);
        executionStack.pushFront(assignStmt2);
        executionStack.pushFront(printStmt);
        
        repository.add(programState);

        try {
            controller.executeAllSteps();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
