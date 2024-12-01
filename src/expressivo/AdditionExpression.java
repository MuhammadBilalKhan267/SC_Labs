package expressivo;

/**
 * Represents the addition of two expressions.
 */
public class AdditionExpression implements Expression {
    private final Expression left;
    private final Expression right;

    public AdditionExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return left.toString() + "+" + right.toString();
    }

    @Override
    public boolean equals(Object thatObject) {
        if (!(thatObject instanceof AdditionExpression)) return false;
        AdditionExpression that = (AdditionExpression) thatObject;
        return this.left.equals(that.left) && this.right.equals(that.right);
    }

    @Override
    public int hashCode() {
        return left.hashCode() + right.hashCode();
    }
}
