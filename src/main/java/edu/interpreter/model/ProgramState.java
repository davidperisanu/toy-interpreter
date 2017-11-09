package edu.interpreter.model;

import java.io.BufferedReader;

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

/**
 * Represents a state of the program at some point.
 * @author David Perisanu
 */
public class ProgramState {
    private IDeque<Statement> executionStack;
    private IDictionary<String, Integer> symbolTable;
    private IList<String> outputMessages;
    private IFileTable<Integer, Pair<String, BufferedReader>> fileTable;

    /**
     * Initializes a new instance of the <code>ProgramState</code> class that has the default values.
     */
    public ProgramState() {
        executionStack = new Deque<>();
        symbolTable = new Dictionary<>();
        outputMessages = new List<>();
        fileTable = new FileTable<>();
    }

    /**
     * Initializes a new instance of the <code>AssignmentStatement</code> class with the specified values.
     * @param executionStack Execution stack of the <code>ProgramState</code>.
     * @param symbolTable Symbol table of the <code>ProgramState</code>.
     * @param outputMessages List of outputed messages of the <code>ProgramState</code>.
     * @param fileTable File table of the <code>ProgramState</code>.
     */
    public ProgramState(IDeque<Statement> executionStack, IDictionary<String, Integer> symbolTable, IList<String> outputMessages, IFileTable<Integer, Pair<String, BufferedReader>> fileTable) {
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.outputMessages = outputMessages;
        this.fileTable = fileTable;
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
     * Gets a string representation of the <code>ProgramState</code>.
     * @return The string representation of the <code>ProgramState</code>.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Execution stack: " + executionStack.toString().replace(",", "") + "\n");
        stringBuilder.append("Symbol table: " + symbolTable + "\n");
        stringBuilder.append("Output messages: " + outputMessages + "\n");
        stringBuilder.append("File table: " + fileTable.toString().replace("(", "(\"").replace(", ", "\", ") + "\n");

        return stringBuilder.toString();
    }
}
