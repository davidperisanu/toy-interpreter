package edu.interpreter.model;

import java.io.BufferedReader;

import edu.interpreter.model.statements.Statement;
import edu.interpreter.model.utilities.Deque;
import edu.interpreter.model.utilities.Dictionary;
import edu.interpreter.model.utilities.FileTable;
import edu.interpreter.model.utilities.Heap;
import edu.interpreter.model.utilities.IdGenerator;
import edu.interpreter.model.utilities.LatchTable;
import edu.interpreter.model.utilities.List;
import edu.interpreter.model.utilities.Pair;
import edu.interpreter.model.utilities.interfaces.IDeque;
import edu.interpreter.model.utilities.interfaces.IDictionary;
import edu.interpreter.model.utilities.interfaces.IFileTable;
import edu.interpreter.model.utilities.interfaces.IHeap;
import edu.interpreter.model.utilities.interfaces.ILatchTable;
import edu.interpreter.model.utilities.interfaces.IList;

/**
 * Represents a state of the program at some point.
 * @author David Perisanu
 */
public class ProgramState {
    private int id;
    private IDeque<Statement> executionStack;
    private IDictionary<String, Integer> symbolTable;
    private IList<String> outputMessages;
    private IFileTable<Integer, Pair<String, BufferedReader>> fileTable;
    private IHeap<Integer, Integer> heap;
    private ILatchTable<Integer, Integer> latchTable;

    /**
     * Initializes a new instance of the <code>ProgramState</code> class that has the default values.
     */
    public ProgramState() {
        id = IdGenerator.generateId();
        executionStack = new Deque<>();
        symbolTable = new Dictionary<>();
        outputMessages = new List<>();
        fileTable = new FileTable<>();
        heap = new Heap<>();
        latchTable = new LatchTable<>();
    }

    /**
     * Initializes a new instance of the <code>AssignmentStatement</code> class with the specified values.
     * @param executionStack Execution stack of the <code>ProgramState</code>.
     * @param symbolTable Symbol table of the <code>ProgramState</code>.
     * @param outputMessages List of outputed messages of the <code>ProgramState</code>.
     * @param fileTable File table of the <code>ProgramState</code>.
     * @param latchTable Latch table of the <code>ProgramState</code>.
     */
    public ProgramState(IDeque<Statement> executionStack, IDictionary<String, Integer> symbolTable, IList<String> outputMessages, IFileTable<Integer, Pair<String, BufferedReader>> fileTable, IHeap<Integer, Integer> heap, ILatchTable<Integer, Integer> latchTable) {
        id = IdGenerator.generateId();
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.outputMessages = outputMessages;
        this.fileTable = fileTable;
        this.heap = heap;
        this.latchTable = latchTable;
    }

    /**
     * Gets the unique <code>ProgramState</code> identificator.
     * @return The unique <code>ProgramState</code> identificator.
     */
    public int id() {
        return id;
    }

    /**
     * Gets the execution stack of the <code>ProgramState</code>.
     * @return The execution stack of the <code>ProgramState</code>.
     */
    public IDeque<Statement> executionStack() {
        return executionStack;
    }

    /**
     * Gets the symbol table of the <code>ProgramState</code>.
     * @return The symbol table of the <code>ProgramState</code>.
     */
    public IDictionary<String, Integer> symbolTable() {
        return symbolTable;
    }

    /**
     * Gets the list of outputed messages of the <code>ProgramState</code>.
     * @return The list of outputed messages of the <code>ProgramState</code>.
     */
    public IList<String> outputMessages() {
        return outputMessages;
    }

    /**
     * Gets the file table of the <code>ProgramState</code>.
     * @return The file table of the <code>ProgramState</code>.
     */
    public IFileTable<Integer, Pair<String, BufferedReader>> fileTable() {
        return fileTable;
    }

    /**
     * Gets the heap of the <code>ProgramState</code>.
     * @return The heap of the <code>ProgramState</code>.
     */
    public IHeap<Integer, Integer> heap() {
        return heap;
    }

    /**
     * Gets the latch table of the <code>ProgramState</code>.
     * @return The latch table of the <code>ProgramState</code>.
     */
    public ILatchTable<Integer, Integer> latchTable() {
        return latchTable;
    }

    /**
     * Sets the execution stack of the <code>ProgramState</code>.
     */
    public void executionStack(IDeque<Statement> executionStack) {
        this.executionStack = executionStack;
    }

    /**
     * Sets the symbol table of the <code>ProgramState</code>.
     */
    public void symbolTable(IDictionary<String, Integer> symbolTable) {
        this.symbolTable = symbolTable;
    }

    /**
     * Sets the list of outputed messages of the <code>ProgramState</code>.
     */
    public void outputMessages(IList<String> outputMessages) {
        this.outputMessages = outputMessages;
    }

    /**
     * Sets the file table of the <code>ProgramState</code>.
     */
    public void fileTable(IFileTable<Integer, Pair<String, BufferedReader>> fileTable) {
        this.fileTable = fileTable;
    }

    /**
     * Sets the heap of the <code>ProgramState</code>.
     */
    public void heap(IHeap<Integer, Integer> heap) {
        this.heap = heap;
    }
    
    /**
     * Sets the latch table of the <code>ProgramState</code>.
     */
    public void latchTable(ILatchTable<Integer, Integer> latchTable) {
        this.latchTable = latchTable;
    }

    /**
     * Checks if the execution stack is empty or not.
     * @return True if the execution stack is not empty, false otherwise.
     */
    public boolean notCompleted() {
        return !executionStack.isEmpty();
    }

    /**
     * Executes the top-most <code>Statement</code> of the <code>ProgramState</code>.
     */
    public ProgramState executeOneStep() {
        return executionStack().popBack().execute(this);
    }

    /**
     * Gets a string representation of the <code>ProgramState</code>.
     * @return The string representation of the <code>ProgramState</code>.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Program state identificator: " + id + "\n");
        stringBuilder.append("Execution stack: " + executionStack.toString().replace(",", "") + "\n");
        stringBuilder.append("Symbol table: " + symbolTable + "\n");
        stringBuilder.append("Output messages: " + outputMessages + "\n");
        stringBuilder.append("File table: " + fileTable.toString().replace("(", "(\"").replace(", ", "\", ") + "\n");   // Adds "" to filenames.
        stringBuilder.append("Heap: " + heap.toString() + "\n");
        stringBuilder.append("Latch table: " + latchTable.toString() + "\n");

        return stringBuilder.toString();
    }
}
