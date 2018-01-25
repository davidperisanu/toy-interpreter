package edu.interpreter.model.utilities;

/**
 * Represents a pair, or 2-tuple.
 * @author David Perisanu
 */
public final class Pair<Left, Right> {
    private Left left;
    private Right right;

    /**
     * Initializes a new instance of the <code>Pair</code> class with the specified values.
     * @param left Element on the left of the <code>Pair</code>.
     * @param right Element on the right of the <code>Pair</code>.
     */
    public Pair(Left left, Right right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Gets the element on the left of the <code>Pair</code>.
     * @return The element on the left of the <code>Pair</code>.
     */
    public Left left() {
        return left;
    }

    /**
     * Gets the element on the left of the <code>Pair</code>.
     * @return The element on the left of the <code>Pair</code>.
     */
    public Left getLeft() {
        return left;
    }

    /**
     * Gets the element on the right of the <code>Pair</code>.
     * @return The element on the right of the <code>Pair</code>.
     */
    public Right right() {
        return right;
    }

    /**
     * Gets the element on the right of the <code>Pair</code>.
     * @return The element on the right of the <code>Pair</code>.
     */
    public Right getRight() {
        return right;
    }

    /**
     * Gets a string representation of the <code>Pair<></code>.
     * @return The string representation of the <code>Pair<></code>.
     */
    @Override
    public String toString() {
        return "(" + left + ", " + right + ")";
    }

    /**
     * Checks if an object is equal to the <code>Pair<></code>.
     * @param obj Object instance to be compared.
     * @return True if the object is equal to the <code>Pair<></code>, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pair<?, ?>) {
            Pair<?, ?> objPair = (Pair<?, ?>)obj;

            return left.equals(objPair.left) && right.equals(objPair.right);
        }

        return false;
    }
}
