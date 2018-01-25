package edu.interpreter.examples;

import edu.interpreter.controller.Controller;
import edu.interpreter.model.ProgramState;
import edu.interpreter.model.expressions.ArithmeticExpression;
import edu.interpreter.model.expressions.ArithmeticExpression.ArithmeticOperator;
import edu.interpreter.model.expressions.BooleanExpression;
import edu.interpreter.model.expressions.BooleanExpression.RelationalOperator;
import edu.interpreter.model.expressions.ConstantExpression;
import edu.interpreter.model.expressions.VariableExpression;
import edu.interpreter.model.statements.AssignmentStatement;
import edu.interpreter.model.statements.CompoundStatement;
import edu.interpreter.model.statements.ConditionalStatement;
import edu.interpreter.model.statements.OpenReadFileStatement;
import edu.interpreter.model.statements.PrintStatement;
import edu.interpreter.model.statements.ReadFileStatement;
import edu.interpreter.model.statements.Statement;
import edu.interpreter.model.statements.WhileLoopStatement;
import edu.interpreter.model.utilities.Deque;
import edu.interpreter.model.utilities.Dictionary;
import edu.interpreter.model.utilities.FileTable;
import edu.interpreter.model.utilities.Heap;
import edu.interpreter.model.utilities.List;
import edu.interpreter.model.utilities.interfaces.IDeque;
import edu.interpreter.repository.IRepository;
import edu.interpreter.repository.Repository;

/**
 * Some examples for seventh laboratory.
 * @author David Perisanu
 */
public class Laboratory7 {
    /**
     * <code>a = 10 + (2 < 6);</code>
     * <code>b = (10 + 2) < 6;</code>
     * <code>print(a);</code>
     * <code>print(b);</code>
     */
    public static Controller example1() {
        AssignmentStatement assignStmt1, assignStmt2;
        PrintStatement printStmt1, printStmt2;
        IDeque<Statement> executionStack;
        IRepository repository;

        // a = 10 + (2 < 6);
        assignStmt1 = new AssignmentStatement("a", new ArithmeticExpression(new ConstantExpression(10), ArithmeticOperator.Addition, new BooleanExpression(new ConstantExpression(2), RelationalOperator.Less, new ConstantExpression(6))));
        // b = (10 + 2) < 6;
        assignStmt2 = new AssignmentStatement("b", new BooleanExpression(new ArithmeticExpression(new ConstantExpression(10), ArithmeticOperator.Addition, new ConstantExpression(2)), RelationalOperator.Less, new ConstantExpression(6)));
        // print(a);
        printStmt1 = new PrintStatement(new VariableExpression("a"));
        // print(b);
        printStmt2 = new PrintStatement(new VariableExpression("b"));

        executionStack = new Deque<>();
        repository = new Repository();

        executionStack.pushFront(assignStmt1);
        executionStack.pushFront(assignStmt2);
        executionStack.pushFront(printStmt1);
        executionStack.pushFront(printStmt2);

        repository.add(new ProgramState(executionStack, new Dictionary<>(), new List<>(), new FileTable<>(), new Heap<>(), null));

        return new Controller(repository);
    }

    /**
     * <code>v = 6;</code>
     * <code>while (v - 4) { print(v); v = v - 1; }</code>
     * <code>print(v);</code>
     */
    public static Controller example2() {
        AssignmentStatement assignStmt;
        WhileLoopStatement whileStmt;
        PrintStatement printStmt;
        IDeque<Statement> executionStack;
        IRepository repository;

        // v = 6;
        assignStmt = new AssignmentStatement("v", new ConstantExpression(6));
        // while (v - 4) { print(v); v = v - 1; }
        whileStmt = new WhileLoopStatement(new ArithmeticExpression(new VariableExpression("v"), ArithmeticOperator.Substraction, new ConstantExpression(4)), new CompoundStatement(new PrintStatement(new VariableExpression("v")), new AssignmentStatement("v", new ArithmeticExpression(new VariableExpression("v"), ArithmeticOperator.Substraction, new ConstantExpression(1)))));
        // print(v);
        printStmt = new PrintStatement(new VariableExpression("v"));

        executionStack = new Deque<>();
        repository = new Repository();

        executionStack.pushFront(assignStmt);
        executionStack.pushFront(whileStmt);
        executionStack.pushFront(printStmt);

        repository.add(new ProgramState(executionStack, new Dictionary<>(), new List<>(), new FileTable<>(), new Heap<>(), null));

        return new Controller(repository);
    }

    /**
     * <code>openReadFile(var_f, "test.in");</code>
     * <code>readFile(var_f, var_c);</code>
     * <code>print(var_c);</code>
     * <code>if (var_c) then { readFile(var_f, var_c); print(var_c); } else { print(0); }</code>
     */
    public static Controller example3() {
        OpenReadFileStatement openFileStmt;
        ReadFileStatement readFileStmt;
        PrintStatement printStmt;
        ConditionalStatement conditionalStmt;
        IDeque<Statement> executionStack;
        IRepository repository;

        // openReadFile(var_f, "test.in");
        openFileStmt = new OpenReadFileStatement("var_f", "test.in");
        // readFile(var_f, var_c);
        readFileStmt = new ReadFileStatement(new VariableExpression("var_f"), "var_c");
        // print(var_c);
        printStmt = new PrintStatement(new VariableExpression("var_c"));
        // if (var_c) then { readFile(var_f, var_c); print(var_c); } else { print(0); }
        conditionalStmt = new ConditionalStatement(new VariableExpression("var_c"), new CompoundStatement(new ReadFileStatement(new VariableExpression("var_f"), "var_c"), new PrintStatement(new VariableExpression("var_c"))), new PrintStatement(new ConstantExpression(0)));

        executionStack = new Deque<>();
        repository = new Repository();

        executionStack.pushFront(openFileStmt);
        executionStack.pushFront(readFileStmt);
        executionStack.pushFront(printStmt);
        executionStack.pushFront(conditionalStmt);

        repository.add(new ProgramState(executionStack, new Dictionary<>(), new List<>(), new FileTable<>(), new Heap<>(), null));

        return new Controller(repository);
    }
}
