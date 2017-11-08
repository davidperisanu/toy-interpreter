package edu.interpreter.model;

import edu.interpreter.model.statements.Statement;
import edu.interpreter.model.utilities.Deque;
import edu.interpreter.model.utilities.Dictionary;
import edu.interpreter.model.utilities.IDeque;
import edu.interpreter.model.utilities.IDictionary;
import edu.interpreter.model.utilities.IList;
import edu.interpreter.model.utilities.List;

/**
 * Represents a state of the program at some point.
 * @author David Perisanu
 */
public class ProgramState {
    private IDeque<Statement> executionStack;
    private IDictionary<String, Integer> symbolTable;
    private IList<String> outputMessages;

    /**
     * Initializes a new instance of the <code>ProgramState</code> class that has the default values.
     */
    public ProgramState() {
        executionStack = new Deque<>();
        symbolTable = new Dictionary<>();
        outputMessages = new List<>();
    }

    /**
     * Initializes a new instance of the <code>AssignmentStatement</code> class with the specified values.
     * @param executionStack Execution stack of the <code>ProgramState</code>.
     * @param symbolTable Symbol table of the <code>ProgramState</code>.
     * @param outputMessages List of outputed messages of the <code>ProgramState</code>.
     */
    public ProgramState(IDeque<Statement> executionStack, IDictionary<String, Integer> symbolTable, IList<String> outputMessages) {
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.outputMessages = outputMessages;
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
     * Gets a string representation of the <code>ProgramState</code>.
     * @return The string representation of the <code>ProgramState</code>.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Execution stack: " + executionStack.toString().replace(",", "") + "\n");
        stringBuilder.append("Symbol table: " + symbolTable + "\n");
        stringBuilder.append("Output messages: " + outputMessages + "\n");

        return stringBuilder.toString();
    }
}
