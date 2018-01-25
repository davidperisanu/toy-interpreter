package edu.interpreter.examples;

import edu.interpreter.controller.Controller;
import edu.interpreter.model.ProgramState;
import edu.interpreter.model.expressions.ArithmeticExpression;
import edu.interpreter.model.expressions.ConstantExpression;
import edu.interpreter.model.expressions.HeapReadingExpression;
import edu.interpreter.model.expressions.VariableExpression;
import edu.interpreter.model.expressions.ArithmeticExpression.ArithmeticOperator;
import edu.interpreter.model.statements.AssignmentStatement;
import edu.interpreter.model.statements.HeapAllocationStatement;
import edu.interpreter.model.statements.HeapWritingStatement;
import edu.interpreter.model.statements.PrintStatement;
import edu.interpreter.model.statements.Statement;
import edu.interpreter.model.utilities.Deque;
import edu.interpreter.model.utilities.Dictionary;
import edu.interpreter.model.utilities.FileTable;
import edu.interpreter.model.utilities.Heap;
import edu.interpreter.model.utilities.List;
import edu.interpreter.model.utilities.interfaces.IDeque;
import edu.interpreter.repository.IRepository;
import edu.interpreter.repository.Repository;

/**
 * Some examples for fifth laboratory.
 * @author David Perisanu
 */
public class Laboratory5 {
    /**
     * <code>v = 10;</code>
     * <code>new(v, 20);</code>
     * <code>new(a, 22);</code>
     * <code>print(v);</code>
     */
    public static Controller example1() {
        AssignmentStatement assignStmt;
        HeapAllocationStatement heapAllocStmt1, heapAllocStmt2;
        PrintStatement printStmt;
        IDeque<Statement> executionStack;
        IRepository repository;

        // v = 10;
        assignStmt = new AssignmentStatement("v", new ConstantExpression(10));
        // new(v, 20);
        heapAllocStmt1 = new HeapAllocationStatement("v", new ConstantExpression(20));
        // new(a, 22);
        heapAllocStmt2 = new HeapAllocationStatement("a", new ConstantExpression(22));
        // print(v);
        printStmt = new PrintStatement(new VariableExpression("v"));

        executionStack = new Deque<>();
        repository = new Repository();

        executionStack.pushFront(assignStmt);
        executionStack.pushFront(heapAllocStmt1);
        executionStack.pushFront(heapAllocStmt2);
        executionStack.pushFront(printStmt);

        repository.add(new ProgramState(executionStack, new Dictionary<>(), new List<>(), new FileTable<>(), new Heap<>(), null));

        return new Controller(repository);
    }

    /**
     * <code>v = 10;</code>
     * <code>new(v, 20);</code>
     * <code>new(a, 22);</code>
     * <code>print(100 + readHeap(v));</code>
     * <code>print(100 + readHeap(a));</code>
     */
    public static Controller example2() {
        AssignmentStatement assignStmt;
        HeapAllocationStatement heapAllocStmt1, heapAllocStmt2;
        PrintStatement printStmt1, printStmt2;
        IDeque<Statement> executionStack;
        IRepository repository;

        // v = 10;
        assignStmt = new AssignmentStatement("v", new ConstantExpression(10));
        // new(v, 20);
        heapAllocStmt1 = new HeapAllocationStatement("v", new ConstantExpression(20));
        // new(a, 22);
        heapAllocStmt2 = new HeapAllocationStatement("a", new ConstantExpression(22));
        // print(100 + readHeap(v));
        printStmt1 = new PrintStatement(new ArithmeticExpression(new ConstantExpression(100), ArithmeticOperator.Addition, new HeapReadingExpression("v")));
        // print(100 + readHeap(a));
        printStmt2 = new PrintStatement(new ArithmeticExpression(new ConstantExpression(100), ArithmeticOperator.Addition, new HeapReadingExpression("a")));

        executionStack = new Deque<>();
        repository = new Repository();

        executionStack.pushFront(assignStmt);
        executionStack.pushFront(heapAllocStmt1);
        executionStack.pushFront(heapAllocStmt2);
        executionStack.pushFront(printStmt1);
        executionStack.pushFront(printStmt2);

        repository.add(new ProgramState(executionStack, new Dictionary<>(), new List<>(), new FileTable<>(), new Heap<>(), null));

        return new Controller(repository);
    }

    /**
     * <code>v = 10;</code>
     * <code>new(v, 20);</code>
     * <code>new(a, 22);</code>
     * <code>writeHeap(a, 30);</code>
     * <code>print(a);</code>
     * <code>print(readHeap(a));</code>
     */
    public static Controller example3() {
        AssignmentStatement assignStmt;
        HeapAllocationStatement heapAllocStmt1, heapAllocStmt2;
        HeapWritingStatement heapWriteStmt;
        PrintStatement printStmt1, printStmt2;
        IDeque<Statement> executionStack;
        IRepository repository;
        
        // v = 10;
        assignStmt = new AssignmentStatement("v", new ConstantExpression(10));
        // new(v, 20);
        heapAllocStmt1 = new HeapAllocationStatement("a", new ConstantExpression(20));
        // new(a, 22);
        heapAllocStmt2 = new HeapAllocationStatement("a", new ConstantExpression(22));
        // writeHeap(a, 30);
        heapWriteStmt = new HeapWritingStatement("a", new ConstantExpression(30));
        // print(a);
        printStmt1 = new PrintStatement(new VariableExpression("a"));
        // print(readHeap(a));
        printStmt2 = new PrintStatement(new HeapReadingExpression("a"));

        executionStack = new Deque<>();
        repository = new Repository();

        executionStack.pushFront(assignStmt);
        executionStack.pushFront(heapAllocStmt1);
        executionStack.pushFront(heapAllocStmt2);
        executionStack.pushFront(heapWriteStmt);
        executionStack.pushFront(printStmt1);
        executionStack.pushFront(printStmt2);

        repository.add(new ProgramState(executionStack, new Dictionary<>(), new List<>(), new FileTable<>(), new Heap<>(), null));

        return new Controller(repository);
    }

    /**
     * <code>v = 10;</code>
     * <code>new(v, 20);</code>
     * <code>new(a, 22);</code>
     * <code>writeHeap(a, 30);</code>
     * <code>print(a);</code>
     * <code>print(readHeap(a));</code>
     * <code>a = 0;</code>
     */
    public static Controller example4() {
        AssignmentStatement assignStmt1, assignStmt2;
        HeapAllocationStatement heapAllocStmt1, heapAllocStmt2;
        HeapWritingStatement heapWriteStmt;
        PrintStatement printStmt1, printStmt2;
        IDeque<Statement> executionStack;
        IRepository repository;

        // v = 10;
        assignStmt1 = new AssignmentStatement("v", new ConstantExpression(10));
        // new(v, 20);
        heapAllocStmt1 = new HeapAllocationStatement("v", new ConstantExpression(20));
        // new(a, 22);
        heapAllocStmt2 = new HeapAllocationStatement("a", new ConstantExpression(22));
        // writeHeap(a, 30);
        heapWriteStmt = new HeapWritingStatement("a", new ConstantExpression(30));
        // print(a);
        printStmt1 = new PrintStatement(new VariableExpression("a"));
        // print(readHeap(a));
        printStmt2 = new PrintStatement(new HeapReadingExpression("a"));
        // a = 0;
        assignStmt2 = new AssignmentStatement("a", new ConstantExpression(0));

        executionStack = new Deque<>();
        repository = new Repository();

        executionStack.pushFront(assignStmt1);
        executionStack.pushFront(heapAllocStmt1);
        executionStack.pushFront(heapAllocStmt2);
        executionStack.pushFront(heapWriteStmt);
        executionStack.pushFront(printStmt1);
        executionStack.pushFront(printStmt2);
        executionStack.pushFront(assignStmt2);

        repository.add(new ProgramState(executionStack, new Dictionary<>(), new List<>(), new FileTable<>(), new Heap<>(), null));

        return new Controller(repository);
    }
}
