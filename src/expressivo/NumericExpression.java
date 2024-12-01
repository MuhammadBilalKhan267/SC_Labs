package expressivo;

/**
 * Represents a numeric constant in the expression.
 */
public class NumericExpression implements Expression {
    private final double value;

    public NumericExpression(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object thatObject) {
        if (!(thatObject instanceof NumericExpression)) return false;
        NumericExpression that = (NumericExpression) thatObject;
        return Double.compare(this.value, that.value) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(value);
    }
}
