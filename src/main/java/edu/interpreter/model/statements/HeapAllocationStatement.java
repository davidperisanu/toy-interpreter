package edu.interpreter.model.statements;

import edu.interpreter.model.ProgramState;
import edu.interpreter.model.expressions.Expression;
import edu.interpreter.model.utilities.IdGenerator;
import edu.interpreter.model.utilities.interfaces.IDictionary;
import edu.interpreter.model.utilities.interfaces.IHeap;

/**
 * Represents a heap allocation statement.
 * @author David Perisanu
 */
public class HeapAllocationStatement extends Statement {
    private String variableName;
    private Expression expression;

    /**
     * Initializes a new instance of the <code>HeapAllocationStatement</code> class with the specified values.
     * @param variableName Variable name to refer the heap alocated space.
     * @param expression <code>Expression</code> to evaluate.
     */
    public HeapAllocationStatement(String variableName, Expression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    /**
     * Executes the <code>HeapAllocationStatement</code>.
     * @param programState The program state before the execution of the <code>HeapAllocationStatement</code>.
     * @return The program state after the execution of the <code>HeapAllocationStatement</code>.
     */
    @Override
    public ProgramState execute(ProgramState programState) {
        IDictionary<String, Integer> symbolTable;
        IHeap<Integer, Integer> heap;
        int memoryAddress;

        memoryAddress = IdGenerator.generateId();
        symbolTable = programState.symbolTable();
        heap = programState.heap();

        heap.add(memoryAddress, expression.evaluate(symbolTable, heap));
        symbolTable.add(variableName, memoryAddress);

        return null;
    }

    /**
     * Gets a string representation of the <code>HeapAllocationStatement</code>.
     * @return The string representation of the <code>HeapAllocationStatement</code>.
     */
    @Override
    public String toString() {
        return "new(" + variableName + ", " + expression + ");";
    }
}
