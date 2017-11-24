package edu.interpreter.model.expressions;

import edu.interpreter.model.utilities.exceptions.InvalidOperatorException;
import edu.interpreter.model.utilities.interfaces.IDictionary;
import edu.interpreter.model.utilities.interfaces.IHeap;

/**
 * Represents an boolean expression composed of two <code>Expression</code> instances and one relational operator.
 * @author David Perisanu
 */
public class BooleanExpression extends Expression {
    /**
     * Represents an relational operator.
     */
    public enum RelationalOperator {
        /**
         * Represents the relational equal-to operator.
         */
        Equal("=="),
        /**
         * Represents the relational not-equal-to operator.
         */
        NotEqual("!="),
        /**
         * Represents the relational greater-than operator.
         */
        Greater(">"),
        /**
         * Represents the relational less-than operator.
         */
        Less("<"),
        /**
         * Represents the relational greater-than-or-equal-to operator.
         */
        GreaterOrEqual(">="),
        /**
         * Represents the relational less-than-or-equal-to operator.
         */
        LessOrEqual("<=");

        private final String relationalOperator;

        /**
         * Initializes a new instance of the <code>RelationalOperator</code> enum with the specified value.
         * @param relationalOperator A string representing an relational operator.
         */
        private RelationalOperator(final String relationalOperator) {
            this.relationalOperator = relationalOperator;
        }

        /**
         * Gets the relational operator.
         * @return A string representing an relational operator. 
         */
        public final String getOperator() {
            return relationalOperator;
        }
    }

    private Expression leftExpression;
    private RelationalOperator relationalOperator;
    private Expression rightExpression;

    /**
     * Initializes a new instance of the <code>BooleanExpression</code> class with the specified values.
     * @param leftExpression Expression on the left of the relational operator.
     * @param relationalOperator Relational operator.
     * @param rightExpression Expression on the right of the relational operator.
     */
    public BooleanExpression(Expression leftExpression, RelationalOperator relationalOperator, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.relationalOperator = relationalOperator;
        this.rightExpression = rightExpression;
    }

    /**
     * Computes the value of the <code>BooleanExpression</code>.
     * @param symbolTable Symbol table of the <code>ProgramState</code>.
     * @param heap Heap of the <code>ProgramState</code>.
     * @return The value of the <code>BooleanExpression</code>.
     * @throws InvalidOperatorException if the operation of the <code>RelationalOperator</code> was not defined.
     */
    @Override
    public int evaluate(IDictionary<String, Integer> symbolTable, IHeap<Integer, Integer> heap) {
        int leftResult, rightResult;

        leftResult = leftExpression.evaluate(symbolTable, heap);
        rightResult = rightExpression.evaluate(symbolTable, heap);

        switch (relationalOperator) {
            case Equal:
                return leftResult == rightResult ? 1 : 0;

            case NotEqual:
                return leftResult != rightResult ? 1 : 0;

            case Greater:
                return leftResult > rightResult ? 1 : 0;

            case Less:
                return leftResult < rightResult ? 1 : 0;

            case GreaterOrEqual:
                return leftResult >= rightResult ? 1 : 0;

            case LessOrEqual:
                return leftResult <= rightResult ? 1 : 0;

            default:
            throw new InvalidOperatorException("Relational operator's operation has not been defined.");
        }
    }

    /**
     * Gets a string representation of the <code>BooleanExpression</code>.
     * @return The string representation of the <code>BooleanExpression</code>.
     */
    @Override
    public String toString() {
        return "(" + leftExpression + " " + relationalOperator.getOperator() + " " + rightExpression + ")";
    }
}
