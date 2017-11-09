package edu.interpreter.examples;

import java.io.BufferedReader;

import edu.interpreter.controller.Controller;
import edu.interpreter.model.ProgramState;
import edu.interpreter.model.expressions.ArithmeticExpression;
import edu.interpreter.model.expressions.ConstantExpression;
import edu.interpreter.model.expressions.VariableExpression;
import edu.interpreter.model.expressions.ArithmeticExpression.Operator;
import edu.interpreter.model.statements.CloseReadFileStatement;
import edu.interpreter.model.statements.CompoundStatement;
import edu.interpreter.model.statements.ConditionalStatement;
import edu.interpreter.model.statements.OpenReadFileStatement;
import edu.interpreter.model.statements.PrintStatement;
import edu.interpreter.model.statements.ReadFileStatement;
import edu.interpreter.model.statements.Statement;
import edu.interpreter.model.utilities.Deque;
import edu.interpreter.model.utilities.Dictionary;
import edu.interpreter.model.utilities.FileTable;
import edu.interpreter.model.utilities.List;
import edu.interpreter.model.utilities.Pair;
import edu.interpreter.model.utilities.interfaces.IDeque;
import edu.interpreter.model.utilities.interfaces.IDictionary;
import edu.interpreter.model.utilities.interfaces.IFileTable;
import edu.interpreter.model.utilities.interfaces.IList;
import edu.interpreter.repository.IRepository;
import edu.interpreter.repository.Repository;

/**
 * Some examples for fourth laboratory.
 * @author David Perisanu
 */
public final class Laboratory4 {
    /**
     * <code>openReadFile(var_f, "test.in");</code>
     * <code>readFile(var_f, var_c);</code>
     * <code>print(var_c);</code>
     * <code>if (var_c) then { readFile(var_f, var_c); print(var_c) } else { print(0); }</code>
     * <code>closeReadFile(var_f);</code>
     */
    public static void example1() {
        String logFilePath;

        System.out.print("Repository log file: ");
        logFilePath = System.console().readLine();

        // openReadFile(var_f, "test.in");
        OpenReadFileStatement openFileStmt = new OpenReadFileStatement("var_f", "test.in");
        // readFile(var_f, var_c);
        ReadFileStatement readFileStmt1 = new ReadFileStatement(new VariableExpression("var_f"), "var_c");
        // print(var_c);
        PrintStatement printStmt1 = new PrintStatement(new VariableExpression("var_c"));
        // if (var_c) then { readFile(var_f, var_c); print(var_c) } else { print(0); }
        VariableExpression variableExpr = new VariableExpression("var_c");
        ReadFileStatement readFileStmt2 = new ReadFileStatement(new VariableExpression("var_f"), "var_c");
        PrintStatement printStmt2 = new PrintStatement(new VariableExpression("var_c"));
        CompoundStatement compoundStmt = new CompoundStatement(readFileStmt2, printStmt2);
        PrintStatement printStmt3 = new PrintStatement(new ConstantExpression(0));
        ConditionalStatement conditionalStmt = new ConditionalStatement(variableExpr, compoundStmt, printStmt3);
        // closeReadFile(var_f);
        CloseReadFileStatement closeFileStmt = new CloseReadFileStatement(new VariableExpression("var_f"));

        IDeque<Statement> executionStack = new Deque<>();
        IDictionary<String, Integer> symbolTable = new Dictionary<>();
        IList<String> outputMessages = new List<>();
        IFileTable<Integer, Pair<String, BufferedReader>> fileTable = new FileTable<>();
        ProgramState programState = new ProgramState(executionStack, symbolTable, outputMessages, fileTable);
        IRepository repository = new Repository(logFilePath);
        Controller controller = new Controller(repository);

        executionStack.pushFront(openFileStmt);
        executionStack.pushFront(readFileStmt1);
        executionStack.pushFront(printStmt1);
        executionStack.pushFront(conditionalStmt);
        executionStack.pushFront(closeFileStmt);
        
        repository.add(programState);

        try {
            controller.executeAllSteps();
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * <code>openReadFile(var_f, "test.in");</code>
     * <code>readFile(var_f + 2, var_c);</code>
     * <code>print(var_c);</code>
     * <code>if (var_c) then { readFile(var_f, var_c); print(var_c) } else { print(0); }</code>
     * <code>closeReadFile(var_f)</code>
     */
    public static void example2() {
        String logFilePath;

        System.out.print("Repository log file: ");
        logFilePath = System.console().readLine();

        // openReadFile(var_f, "test.in");
        OpenReadFileStatement openFileStmt = new OpenReadFileStatement("var_f", "test.in");
        // readFile(var_f + 2, var_c);
        ReadFileStatement readFileStmt1 = new ReadFileStatement(new ArithmeticExpression(new VariableExpression("var_f"), Operator.Addition, new ConstantExpression(2)), "var_c");
        // print(var_c);
        PrintStatement printStmt1 = new PrintStatement(new VariableExpression("var_c"));
        // if (var_c) then { readFile(var_f, var_c); print(var_c) } else { print(0); }
        VariableExpression variableExpr = new VariableExpression("var_c");
        ReadFileStatement readFileStmt2 = new ReadFileStatement(new VariableExpression("var_f"), "var_c");
        PrintStatement printStmt2 = new PrintStatement(new VariableExpression("var_c"));
        CompoundStatement compoundStmt = new CompoundStatement(readFileStmt2, printStmt2);
        PrintStatement printStmt3 = new PrintStatement(new ConstantExpression(0));
        ConditionalStatement conditionalStmt = new ConditionalStatement(variableExpr, compoundStmt, printStmt3);
        // closeReadFile(var_f)
        CloseReadFileStatement closeFileStmt = new CloseReadFileStatement(new VariableExpression("var_f"));

        IDeque<Statement> executionStack = new Deque<>();
        IDictionary<String, Integer> symbolTable = new Dictionary<>();
        IList<String> outputMessages = new List<>();
        IFileTable<Integer, Pair<String, BufferedReader>> fileTable = new FileTable<>();
        ProgramState programState = new ProgramState(executionStack, symbolTable, outputMessages, fileTable);
        IRepository repository = new Repository(logFilePath);
        Controller controller = new Controller(repository);

        executionStack.pushFront(openFileStmt);
        executionStack.pushFront(readFileStmt1);
        executionStack.pushFront(printStmt1);
        executionStack.pushFront(conditionalStmt);
        executionStack.pushFront(closeFileStmt);

        repository.add(programState);

        try {
            controller.executeAllSteps();
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
