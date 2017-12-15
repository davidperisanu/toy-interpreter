package edu.interpreter.examples;

import edu.interpreter.controller.Controller;
import edu.interpreter.model.ProgramState;
import edu.interpreter.model.expressions.ConstantExpression;
import edu.interpreter.model.expressions.HeapReadingExpression;
import edu.interpreter.model.expressions.VariableExpression;
import edu.interpreter.model.statements.AssignmentStatement;
import edu.interpreter.model.statements.CompoundStatement;
import edu.interpreter.model.statements.ForkStatement;
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

public class Laboratory8 {
    public static Controller example1() {
        AssignmentStatement assignStmt, forkAssignStmt;
        HeapAllocationStatement heapAssignStmt;
        ForkStatement forkStmt;
        HeapWritingStatement heapWriteStmt;
        PrintStatement printStmt1, printStmt2, printStmt3, printStmt4;
        IDeque<Statement> executionStack;
        IRepository repository;

        // v = 10;
        assignStmt = new AssignmentStatement("v", new ConstantExpression(10));
        // new(a, 22);
        heapAssignStmt = new HeapAllocationStatement("a", new ConstantExpression(22));
        // fork(writeHeap(a, 30); v = 32; print(v); print(readHeap(a)););
        //  writeHeap(a, 30);
        heapWriteStmt = new HeapWritingStatement("a", new ConstantExpression(30));
        //  v = 32;
        forkAssignStmt = new AssignmentStatement("v", new ConstantExpression(32));
        //  print(v);
        printStmt1 = new PrintStatement(new VariableExpression("v"));
        //  print(readHeap(a));
        printStmt2 = new PrintStatement(new HeapReadingExpression("a"));
        //
        forkStmt = new ForkStatement(new CompoundStatement(new CompoundStatement(new CompoundStatement(heapWriteStmt, forkAssignStmt), printStmt1), printStmt2));
        // print(v);
        printStmt3 = new PrintStatement(new VariableExpression("v"));
        // print(readHeap(a))
        printStmt4 = new PrintStatement(new HeapReadingExpression("a"));

        executionStack = new Deque<>();
        repository = new Repository();

        executionStack.pushFront(assignStmt);
        executionStack.pushFront(heapAssignStmt);
        executionStack.pushFront(forkStmt);
        executionStack.pushFront(printStmt3);
        executionStack.pushFront(printStmt4);

        repository.add(new ProgramState(executionStack, new Dictionary<>(), new List<>(), new FileTable<>(), new Heap<>()));
        
        return new Controller(repository);
    }
}
