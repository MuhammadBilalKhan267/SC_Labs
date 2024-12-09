package lab12;

public class RecursiveParser {

    /**
     * Evaluates a mathematical expression.
     * @param expression The mathematical expression as a string.
     * @return The result of the evaluated expression.
     */
    public static double evaluateExpression(String expression) {
        if (expression == null || expression.trim().isEmpty()) {
            throw new IllegalArgumentException("Expression cannot be null or empty.");
        }

        int[] index = {0}; // Use an array to pass by reference
        return evaluateExpressionHelper(expression.replaceAll("\\s+", ""), index);
    }

    /**
     * Recursive helper to evaluate a sub-expression.
     * @param expression The mathematical expression as a string.
     * @param index The current index in the expression.
     * @return The result of the sub-expression.
     */
    private static double evaluateExpressionHelper(String expression, int[] index) {
        double result = parseTerm(expression, index);

        while (index[0] < expression.length()) {
            char operator = expression.charAt(index[0]);

            if (operator == '+' || operator == '-') {
                index[0]++;
                double nextTerm = parseTerm(expression, index);
                if (operator == '+') {
                    result += nextTerm;
                } else {
                    result -= nextTerm;
                }
            } else {
                break; // Stop if the operator is not '+' or '-'
            }
        }
        return result;
    }

    /**
     * Parses and evaluates terms (multiplication/division).
     * @param expression The mathematical expression as a string.
     * @param index The current index in the expression.
     * @return The result of the term.
     */
    private static double parseTerm(String expression, int[] index) {
        double result = parseFactor(expression, index);

        while (index[0] < expression.length()) {
            char operator = expression.charAt(index[0]);

            if (operator == '*' || operator == '/') {
                index[0]++;
                double nextFactor = parseFactor(expression, index);
                if (operator == '*') {
                    result *= nextFactor;
                } else {
                    if (nextFactor == 0) {
                        throw new ArithmeticException("Division by zero.");
                    }
                    result /= nextFactor;
                }
            } else {
                break; // Stop if the operator is not '*' or '/'
            }
        }
        return result;
    }

    /**
     * Parses factors, which could be numbers or parenthesized sub-expressions.
     * @param expression The mathematical expression as a string.
     * @param index The current index in the expression.
     * @return The result of the factor.
     */
    private static double parseFactor(String expression, int[] index) {
        if (expression.charAt(index[0]) == '(') {
            index[0]++;
            double subExpressionResult = evaluateExpressionHelper(expression, index);
            if (index[0] >= expression.length() || expression.charAt(index[0]) != ')') {
                throw new IllegalArgumentException("Mismatched parentheses.");
            }
            index[0]++;
            return subExpressionResult;
        } else {
            return parseNumber(expression, index);
        }
    }

    /**
     * Parses a number from the expression string.
     * @param expression The mathematical expression as a string.
     * @param index The current index in the expression.
     * @return The parsed number.
     */
    private static double parseNumber(String expression, int[] index) {
        int start = index[0];
        while (index[0] < expression.length() &&
                (Character.isDigit(expression.charAt(index[0])) || expression.charAt(index[0]) == '.')) {
            index[0]++;
        }

        if (start == index[0]) {
            throw new IllegalArgumentException("Invalid number at position " + start);
        }

        return Double.parseDouble(expression.substring(start, index[0]));
    }
}
