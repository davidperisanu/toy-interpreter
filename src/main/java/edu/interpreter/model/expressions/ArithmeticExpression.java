package edu.interpreter.model.expressions;

import edu.interpreter.model.utilities.exceptions.DivideByZeroException;
import edu.interpreter.model.utilities.exceptions.InvalidOperatorException;
import edu.interpreter.model.utilities.interfaces.IDictionary;
import edu.interpreter.model.utilities.interfaces.IHeap;

/**
 * Represents an arithmetic expression composed of two <code>Expression</code> instances and one arithmetical operator.
 * @author David Perisanu
 */
public final class ArithmeticExpression extends Expression {
    /**
     * Represents an arithmetical operator.
     */
    public enum ArithmeticOperator {
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

        private final char arithmeticOperator;

        /**
         * Initializes a new instance of the <code>ArithmeticOperator</code> enum with the specified value.
         * @param arithmeticOperator A character representing an arithmtical operator.
         */
        private ArithmeticOperator(final char arithmeticOperator) {
            this.arithmeticOperator = arithmeticOperator;
        }

        /**
         * Gets the arithmetical operator.
         * @return A character representing an arithmtical operator. 
         */
        public final char getOperator() {
            return arithmeticOperator;
        }
    }

    private Expression leftExpression;
    private ArithmeticOperator arithmeticOperator;
    private Expression rightExpression;

    /**
     * Initializes a new instance of the <code>ArithmeticExpression</code> class with the specified values.
     * @param leftExpression Expression on the left of the arithmetic operator.
     * @param arithmeticOperator Arithmeical operator.
     * @param rightExpression Expression on the right of the arithmetic operator.
     */
    public ArithmeticExpression(Expression leftExpression, ArithmeticOperator arithmeticOperator, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.arithmeticOperator = arithmeticOperator;
        this.rightExpression = rightExpression;
    }

    /**
     * Computes the value of the <code>ArithmeticExpression</code>.
     * @param symbolTable Symbol table of the <code>ProgramState</code>.
     * @param heap Heap of the <code>ProgramState</code>.
     * @return The value of the <code>ArithmeticExpression</code>.
     * @throws DivideByZeroException if an attempt of division by 0 is made.
     * @throws InvalidOperatorException if the operation of the <code>ArithmeticOperator</code> was not treated.
     */
    @Override
    public int evaluate(IDictionary<String, Integer> symbolTable, IHeap<Integer, Integer> heap) throws DivideByZeroException, InvalidOperatorException {
        int leftResult, rightResult;

        leftResult = leftExpression.evaluate(symbolTable, heap);
        rightResult = rightExpression.evaluate(symbolTable, heap);

        switch (arithmeticOperator) {
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
                throw new InvalidOperatorException("Arithmetical operator's operation has not been defined.");
        }
    }

    /**
     * Gets a string representation of the <code>ArithmeticExpression</code>.
     * @return The string representation of the <code>ArithmeticExpression</code>.
     */
    @Override
    public String toString() {
        return "(" + leftExpression + " " + arithmeticOperator.getOperator() + " " + rightExpression + ")";
    }
}
