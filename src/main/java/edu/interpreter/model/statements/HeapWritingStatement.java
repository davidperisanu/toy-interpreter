package edu.interpreter.model.statements;

import edu.interpreter.model.ProgramState;
import edu.interpreter.model.expressions.Expression;
import edu.interpreter.model.utilities.IdGenerator;
import edu.interpreter.model.utilities.interfaces.IDictionary;
import edu.interpreter.model.utilities.interfaces.IHeap;

/**
 * Represents a heap writing statement.
 * @author David Perisanu
 */
public class HeapWritingStatement extends Statement {
    private String variableName;
    private Expression expression;

    /**
     * Initializes a new instance of the <code>HeapWritingStatement</code> class with the specified values.
     * @param varableName Variable name that refers the heap alocated space.
     * @param expression <code>Expression</code> to evaluate.
     */
    public HeapWritingStatement(String variableName, Expression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    /**
     * Executes the <code>HeapWritingStatement</code>.
     * @param programState The program state before the execution of the <code>HeapWritingStatement</code>.
     * @return The program state after the execution of the <code>HeapWritingStatement</code>.
     */
    @Override
    public ProgramState execute(ProgramState programState) {
        IDictionary<String, Integer> symbolTable;
        IHeap<Integer, Integer> heap;

        symbolTable  = programState.symbolTable();
        heap = programState.heap();

        if (!symbolTable.contains(variableName))
            symbolTable.add(variableName, IdGenerator.generateId());

        heap.add(symbolTable.get(variableName), expression.evaluate(symbolTable, heap));
        
        return null;
    }

    /**
     * Gets a string representation of the <code>HeapWritingStatement</code>.
     * @return The string representation of the <code>HeapWritingStatement</code>.
     */
    @Override
    public String toString() {
        return "writeHeap(" + variableName + ", " + expression + ");";
    }
}
