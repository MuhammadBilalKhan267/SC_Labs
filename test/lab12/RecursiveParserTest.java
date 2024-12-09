package lab12;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RecursiveParserTest {
	/**
	 * Testing Strategy for RecursiveExpressionParser:
	 *
	 * 1. **Simple Expressions**:
	 *    - Test expressions with a single operator to ensure basic functionality.
	 *      Example: "2 + 3", "8 / 2".
	 *
	 * 2. **Operator Precedence**:
	 *    - Verify that multiplication and division are evaluated before addition and subtraction.
	 *      Example: "2 + 3 * 4" should evaluate to 14, not 20.
	 *
	 * 3. **Parentheses Handling**:
	 *    - Test expressions with parentheses to ensure correct sub-expression evaluation.
	 *      Example: "(2 + 3) * 4" should evaluate to 20.
	 *
	 * 4. **Floating-Point Arithmetic**:
	 *    - Test expressions with floating-point numbers to ensure accuracy.
	 *      Example: "3.3 + 3.3" should evaluate to 6.6.
	 *
	 * 5. **Whitespace Handling**:
	 *    - Test expressions with extra spaces to ensure whitespace is ignored.
	 *      Example: " 3 + 4 " should evaluate to 7.
	 *
	 * 6. **Edge Cases**:
	 *    - Division by zero should throw an appropriate exception.
	 *      Example: "5 / 0" should throw ArithmeticException.
	 *    - Empty or null input should throw an appropriate exception.
	 *      Example: "" or null should throw IllegalArgumentException.
	 *    - Unbalanced parentheses should throw an appropriate exception.
	 *      Example: "(5 + 2" should throw IllegalArgumentException.
	 *
	 * 7. **Invalid Expressions**:
	 *    - Test expressions with invalid syntax or unsupported characters.
	 *      Example: "5 +" or "2 + a" should throw IllegalArgumentException.
	 *
	 * 8. **Boundary Tests**:
	 *    - Test with very large or very small numbers to ensure no overflow or underflow occurs.
	 *      Example: "1e10 + 1e-10" should evaluate accurately.
	 *
	 * 9. **Comprehensive Mixed Cases**:
	 *    - Test expressions with a mix of operators and parentheses to verify full functionality.
	 *      Example: "2 + (3 * 4 - (5 / 2))" should evaluate correctly.
	 *
	 * This strategy ensures that the implementation is robust, handles edge cases,
	 * and adheres to mathematical rules and operator precedence.
	 */

    @Test
    public void testSimpleAddition() {
        assertEquals(5.0, RecursiveParser.evaluateExpression("2 + 3"), 0.0001);
    }

    @Test
    public void testMixedOperators() {
        assertEquals(11.0, RecursiveParser.evaluateExpression("2 + 3 * 3"), 0.0001);
    }

    @Test
    public void testParentheses() {
        assertEquals(25.0, RecursiveParser.evaluateExpression("(2 + 3) * 5"), 0.0001);
    }

    @Test
    public void testDivision() {
        assertEquals(4.0, RecursiveParser.evaluateExpression("8 / 2"), 0.0001);
    }

    @Test
    public void testFloatingPointNumbers() {
        assertEquals(6.6, RecursiveParser.evaluateExpression("3.3 + 3.3"), 0.0001);
    }

    @Test
    public void testWhitespaceHandling() {
        assertEquals(7.0, RecursiveParser.evaluateExpression(" 3 + 4 "), 0.0001);
    }

    @Test
    public void testDivisionByZero() {
        Exception exception = assertThrows(ArithmeticException.class, () ->
                RecursiveParser.evaluateExpression("5 / 0"));
        assertEquals("Division by zero.", exception.getMessage());
    }

    @Test
    public void testMismatchedParentheses() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                RecursiveParser.evaluateExpression("(5 + 2"));
        assertEquals("Mismatched parentheses.", exception.getMessage());
    }

    @Test
    public void testEmptyExpression() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                RecursiveParser.evaluateExpression(""));
        assertEquals("Expression cannot be null or empty.", exception.getMessage());
    }
}
