package edu.interpreter.examples;

import edu.interpreter.controller.Controller;
import edu.interpreter.model.ProgramState;
import edu.interpreter.model.expressions.ArithmeticExpression;
import edu.interpreter.model.expressions.ArithmeticExpression.ArithmeticOperator;
import edu.interpreter.model.expressions.BooleanExpression;
import edu.interpreter.model.expressions.BooleanExpression.RelationalOperator;
import edu.interpreter.model.expressions.ConstantExpression;
import edu.interpreter.model.expressions.HeapReadingExpression;
import edu.interpreter.model.expressions.VariableExpression;
import edu.interpreter.model.statements.AssignmentStatement;
import edu.interpreter.model.statements.CompoundStatement;
import edu.interpreter.model.statements.DoUntilStatement;
import edu.interpreter.model.statements.ForkStatement;
import edu.interpreter.model.statements.HeapAllocationStatement;
import edu.interpreter.model.statements.HeapWritingStatement;
import edu.interpreter.model.statements.LatchAllocationStatement;
import edu.interpreter.model.statements.LatchAwaitStatement;
import edu.interpreter.model.statements.LatchCountDownStatement;
import edu.interpreter.model.statements.PrintStatement;
import edu.interpreter.model.statements.Statement;
import edu.interpreter.model.utilities.Deque;
import edu.interpreter.model.utilities.Dictionary;
import edu.interpreter.model.utilities.FileTable;
import edu.interpreter.model.utilities.Heap;
import edu.interpreter.model.utilities.LatchTable;
import edu.interpreter.model.utilities.List;
import edu.interpreter.model.utilities.interfaces.IDeque;
import edu.interpreter.repository.IRepository;
import edu.interpreter.repository.Repository;

/**
 * Some examples for exam practical stage.
 * @author David Perisanu
 */
public class Exam {
    /**
     * <code>new(v1,2);</code>
     * <code>new(v2,3);</code>
     * <code>new(v3,4);</code>
     * <code>newLatch(cnt,readHeap(v2));</code>
     * <code>fork(writeHeap(v1, readHeap(v1) * 10); print(readHeap(v1)); countDown(cnt); fork(writeHeap(v2, readHeap(v2) * 10); print(readHeap(v2)); countDown(cnt); fork(writeHeap(v3, readHeap(v3) * 10); print(readHeap(v3)); countDown(cnt))));</code>
     * <code>await(cnt);</code>
     * <code>print(100);</code>
     * <code>countDown(cnt);</code>
     * <code>print(100);</code>
     */
    public static Controller example1() {
        HeapAllocationStatement new1, new2, new3;
        LatchAllocationStatement newLatch;
        ForkStatement fork1, fork2, fork3;
        HeapWritingStatement wh1, wh2, wh3;
        PrintStatement print1, print2, print3, print4, print5;
        LatchCountDownStatement countDown1, countDown2, countDown3, countDown4;
        LatchAwaitStatement await1;
        IDeque<Statement> executionStack;
        IRepository repository;

        // new(v1,2);
        new1 = new HeapAllocationStatement("v1", new ConstantExpression(2));
        // new(v2,3);
        new2 = new HeapAllocationStatement("v2", new ConstantExpression(3));
        // new(v3,4);
        new3 = new HeapAllocationStatement("v3", new ConstantExpression(4));
        // newLatch(cnt,readHeap(v2));
        newLatch = new LatchAllocationStatement("cnt", new HeapReadingExpression("v2"));
        // fork(writeHeap(v1, readHeap(v1) * 10); print(readHeap(v1)); countDown(cnt);
        //   fork(writeHeap(v2, readHeap(v2) * 10); print(readHeap(v2)); countDown(cnt); 
        //      fork(writeHeap(v3, readHeap(v3) * 10); print(readHeap(v3)); countDown(cnt))));
        //  fork(writeHeap(v3, readHeap(v3) * 10); print(readHeap(v3)); countDown(cnt));
        wh3 = new HeapWritingStatement("v3", new ArithmeticExpression(new HeapReadingExpression("v3"), ArithmeticOperator.Multiplication, new ConstantExpression(10)));
        print3 = new PrintStatement(new HeapReadingExpression("v3"));
        countDown3 = new LatchCountDownStatement("cnt");
        fork3 = new ForkStatement(new CompoundStatement(new CompoundStatement(wh3, print3), countDown3));
        //  fork(writeHeap(v2, readHeap(v2) * 10); print(readHeap(v2)); countDown(cnt); fork(...));
        wh2 = new HeapWritingStatement("v2", new ArithmeticExpression(new HeapReadingExpression("v2"), ArithmeticOperator.Multiplication, new ConstantExpression(10)));
        print2 = new PrintStatement(new HeapReadingExpression("v2"));
        countDown2 = new LatchCountDownStatement("cnt");
        fork2 = new ForkStatement(new CompoundStatement(new CompoundStatement(new CompoundStatement(wh2, print2), countDown2), fork3));
        //  fork(writeHeap(v1, readHeap(v1) * 10); print(readHeap(v1)); countDown(cnt); fork(...));
        wh1 = new HeapWritingStatement("v1", new ArithmeticExpression(new HeapReadingExpression("v1"), ArithmeticOperator.Multiplication, new ConstantExpression(10)));
        print1 = new PrintStatement(new HeapReadingExpression("v1"));
        countDown1 = new LatchCountDownStatement("cnt");
        fork1 = new ForkStatement(new CompoundStatement(new CompoundStatement(new CompoundStatement(wh1, print1), countDown1), fork2));
        // await(cnt);
        await1 = new LatchAwaitStatement("cnt");
        // print(100);
        print4 = new PrintStatement(new ConstantExpression(100));
        // countDown(cnt);
        countDown4 = new LatchCountDownStatement("cnt");
        // print(100);
        print5 = new PrintStatement(new ConstantExpression(100));
        
        executionStack = new Deque<>();
        repository = new Repository();

        executionStack.pushFront(new1);
        executionStack.pushFront(new2);
        executionStack.pushFront(new3);
        executionStack.pushFront(newLatch);
        executionStack.pushFront(fork1);
        executionStack.pushFront(await1);
        executionStack.pushFront(print4);
        executionStack.pushFront(countDown4);
        executionStack.pushFront(print5);

        repository.add(new ProgramState(executionStack, new Dictionary<>(), new List<>(), new FileTable<>(), new Heap<>(), new LatchTable<>()));
        repository.logFilePath("ex1.txt");

        return new Controller(repository);
    }

    /**
     * <code>v = 0;</code>
     * <code>(do (fork(print(v); v = v - 1); v = v + 1) until v == 3);</code>
     * <code>x = 1;</code>
     * <code>y = 2;</code>
     * <code>z = 3;</code>
     * <code>w = 4;</code>
     * <code>print(v * 10);</code>
     */
    public static Controller example2() {
        AssignmentStatement assign1, assign2, assign3, assign4, assign5;
        DoUntilStatement doWhile;
        ForkStatement fork;
        PrintStatement print;
        IDeque<Statement> executionStack;
        IRepository repository;

        // v = 0;
        assign1 = new AssignmentStatement("v", new ConstantExpression(0));
        // (do (fork(print(v); v = v - 1); v = v + 1) until v == 3);
        fork = new ForkStatement(new CompoundStatement(new PrintStatement(new VariableExpression("v")), new AssignmentStatement("v", new ArithmeticExpression(new VariableExpression("v"), ArithmeticOperator.Substraction, new ConstantExpression(1)))));
        doWhile = new DoUntilStatement(new CompoundStatement(fork, new AssignmentStatement("v", new ArithmeticExpression(new VariableExpression("v"), ArithmeticOperator.Addition, new ConstantExpression(1)))), new BooleanExpression(new VariableExpression("v"), RelationalOperator.Equal, new ConstantExpression(3)));
        // x = 1; y = 2; z = 3; w = 4;
        assign2 = new AssignmentStatement("x", new ConstantExpression(1));
        assign3 = new AssignmentStatement("y", new ConstantExpression(2));
        assign4 = new AssignmentStatement("z", new ConstantExpression(3));
        assign5 = new AssignmentStatement("w", new ConstantExpression(4));
        // print(v * 10);
        print = new PrintStatement(new ArithmeticExpression(new VariableExpression("v"), ArithmeticOperator.Multiplication, new ConstantExpression(10)));

        executionStack = new Deque<>();
        repository = new Repository();

        executionStack.pushFront(assign1);
        executionStack.pushFront(doWhile);
        executionStack.pushFront(assign2);
        executionStack.pushFront(assign3);
        executionStack.pushFront(assign4);
        executionStack.pushFront(assign5);
        executionStack.pushFront(print);

        repository.add(new ProgramState(executionStack, new Dictionary<>(), new List<>(), new FileTable<>(), new Heap<>(), new LatchTable<>()));
        repository.logFilePath("ex2.txt");

        return new Controller(repository);
    }
}
