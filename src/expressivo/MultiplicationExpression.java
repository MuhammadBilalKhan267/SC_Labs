package expressivo;

/**
 * Represents the multiplication of two expressions.
 */
public class MultiplicationExpression implements Expression {
    private final Expression left;
    private final Expression right;

    public MultiplicationExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return left.toString() + "*" + right.toString();
    }

    @Override
    public boolean equals(Object thatObject) {
        if (!(thatObject instanceof MultiplicationExpression)) return false;
        MultiplicationExpression that = (MultiplicationExpression) thatObject;
        return this.left.equals(that.left) && this.right.equals(that.right);
    }

    @Override
    public int hashCode() {
        return left.hashCode() * 31 + right.hashCode();
    }
}
