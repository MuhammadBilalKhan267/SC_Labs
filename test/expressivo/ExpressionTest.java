package expressivo;

import static org.junit.Assert.*;
import org.junit.Test;

public class ExpressionTest {

    @Test
    public void testNumericExpression() {
        Expression num1 = new NumericExpression(3);
        Expression num2 = new NumericExpression(3.0);
        Expression num3 = new NumericExpression(4.5);

        assertEquals("3.0", num1.toString());
        assertEquals("4.5", num3.toString());
        assertEquals(num1, num2);
        assertNotEquals(num1, num3);
        assertEquals(num1.hashCode(), num2.hashCode());
        assertNotEquals(num1.hashCode(), num3.hashCode());
    }

    @Test
    public void testVariableExpression() {
        Expression var1 = new VariableExpression("x");
        Expression var2 = new VariableExpression("x");
        Expression var3 = new VariableExpression("y");

        assertEquals("x", var1.toString());
        assertEquals("y", var3.toString());
        assertEquals(var1, var2);
        assertNotEquals(var1, var3);
        assertEquals(var1.hashCode(), var2.hashCode());
        assertNotEquals(var1.hashCode(), var3.hashCode());
    }

    @Test
    public void testAdditionExpression() {
        Expression expr1 = new AdditionExpression(new VariableExpression("x"), new NumericExpression(1));
        Expression expr2 = new AdditionExpression(new VariableExpression("x"), new NumericExpression(1.0));
        Expression expr3 = new AdditionExpression(new NumericExpression(1), new VariableExpression("x"));

        assertEquals("x+1.0", expr1.toString());
        assertNotEquals(expr1, expr3);
        assertEquals(expr1, expr2);
    }

    @Test
    public void testNestedExpressions() {
        Expression inner = new AdditionExpression(new VariableExpression("x"), new NumericExpression(1));
        Expression outer = new MultiplicationExpression(inner, new VariableExpression("y"));
        assertEquals("x+1.0*y", outer.toString());
    }
}
