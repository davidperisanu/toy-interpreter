package edu.interpreter.model.expressions;

import edu.interpreter.model.utilities.exceptions.DivideByZeroException;
import edu.interpreter.model.utilities.exceptions.InvalidOperatorException;
import edu.interpreter.model.utilities.interfaces.IDictionary;
import edu.interpreter.model.utilities.interfaces.IHeap;

/**
 * Represents an arithmetic expression composed of two <code>Expression</code> instances and one operator.
 * @author David Perisanu
 */
public final class ArithmeticExpression extends Expression {
    /**
     * Represents an arithmetical operator.
     */
    public enum Operator {
        /**
         * Represents the arithmetical addition operator.
         */
        Addition('+'),
        /**
         * Represents the arithmetical substraction operator.
         */
        Substraction('-'),
        /**
         * Represents the arithmetical multiplication operator.
         */
        Multiplication('*'),
        /**
         * Represents the arithmetical division operator.
         */
        Division('/');

        private final char operator;

        /**
         * Initializes a new instance of the <code>Operator</code> enum with the specified value.
         * @param operator A character representing an arithmtical operator.
         */
        private Operator(final char operator) {
            this.operator = operator;
        }

        /**
         * Gets the arithmetical operator.
         * @return A character representing an arithmtical operator. 
         */
        public final char getOperator() {
            return operator;
        }
    }

    private Expression leftExpression;
    private Expression rightExpression;
    private Operator operator;

    /**
     * Initializes a new instance of the <code>ArithmeticExpression</code> class with the specified values.
     * @param value The constant value of the <code>ConstantExpression</code>.
     */
    public ArithmeticExpression(Expression leftExpression, Operator operator, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.operator = operator;
        this.rightExpression = rightExpression;
    }

    /**
     * Computes the value of the <code>ArithmeticExpression</code>.
     * @param symbolTable Symbol table of the <code>ProgramState</code>.
     * @param heap Heap of the <code>ProgramState</code>.
     * @return The value of the <code>ArithmeticExpression</code>.
     * @throws DivideByZeroException if an attempt of division by 0 is made.
     * @throws InvalidOperatorException if the operation of the <code>Operator</code> was not treated.
     */
    @Override
    public int evaluate(IDictionary<String, Integer> symbolTable, IHeap<Integer, Integer> heap) throws DivideByZeroException, InvalidOperatorException {
        int leftResult, rightResult;

        leftResult = leftExpression.evaluate(symbolTable, heap);
        rightResult = rightExpression.evaluate(symbolTable, heap);

        switch (operator) {
            case Addition:
                return leftResult + rightResult;

            case Substraction:
                return leftResult - rightResult;

            case Multiplication:
                return leftResult * rightResult;

            case Division:
                if (rightResult == 0)
                    throw new DivideByZeroException("Attempted to divide by zero.");
                
                return leftResult / rightResult;
            
            default:
                throw new InvalidOperatorException("Operator operation has not been treated.");
        }
    }

    /**
     * Gets a string representation of the <code>ArithmeticExpression</code>.
     * @return The string representation of the <code>ArithmeticExpression</code>.
     */
    @Override
    public String toString() {
        return "(" + leftExpression + " " + operator.getOperator() + " " + rightExpression + ")";
    }
}
