import java.util.ArrayList;
import java.util.List;

public class FractionCalculator {
    private static final String MULTIPLY_EXPRESSION = " * ";
    private static final String DIVIDE_EXPRESSION = " / ";
    private static final String ADD_EXPRESSION = " + ";
    private static final String SUBTRACT_EXPRESSION = " - ";

    /**
     * Takes an algebraic expression, containing either integers, fractions or mixed numbers
     * and returns a result of the expression in its most simplified representation.
     * Valid operators: *, /, +, -
     * Operators are separated from operands by one or more spaces.
     * The expression can contain multiple operations.
     * In case of multiple operations, the precedence of operators is as follows:
     *
     *  | Precedence | Operators |
     *  |          1 | *,/       |
     *  |          2 | +,-       |
     *
     * For operators within the same level, precedence is taken from left to right.
     * Parenthesis are not allowed in expressions.
     * @param expression
     * @return
     */
    public static String calculate(String expression) {
        if (hasNoOperations(expression)) {
            return MixedNumber.parse(expression).toString();
        }

        expression = cleanupExpression(expression);
        List<String> pendingTokens = new ArrayList<String>();
        String[] tokens = expression.split(" ");
        MixedNumber lastResult = null;
        for(int i = 1; i < tokens.length; i+=2) {
            MixedNumber leftOperand = lastResult == null
                                      ? MixedNumber.parse(tokens[i-1])
                                      : lastResult;
            MixedNumber rightOperand = MixedNumber.parse(tokens[i+1]);
            switch (tokens[i]) {
                case "*":
                    leftOperand.multiply(rightOperand);
                    lastResult = leftOperand;
                    break;
                case "/":
                    leftOperand.divide(rightOperand);
                    lastResult = leftOperand;
                    break;
                default:
                    pendingTokens.add(leftOperand.toString());
                    pendingTokens.add(tokens[i]);
                    lastResult = null;
                    break;
            }
        }
        if (lastResult != null) {
            pendingTokens.add(lastResult.toString());
        } else {
            pendingTokens.add(tokens[tokens.length-1]);
        }
        MixedNumber result = MixedNumber.parse(pendingTokens.get(0));
        for(int i = 1; i < pendingTokens.size(); i+=2) {
            MixedNumber rightOperand = MixedNumber.parse(pendingTokens.get(i+1));
            switch (pendingTokens.get(i)) {
                case "+":
                    result.add(rightOperand);
                    break;
                case "-":
                    result.subtract(rightOperand);
                    break;
                default:
                    throw new NumberFormatException();
            }
        }
        return result.toString();
    }

    private static boolean hasNoOperations(String expression) {
        return !expression.contains(MULTIPLY_EXPRESSION) &&
                !expression.contains(DIVIDE_EXPRESSION) &&
                !expression.contains(ADD_EXPRESSION) &&
                !expression.contains(SUBTRACT_EXPRESSION);
    }

    private static String cleanupExpression(String expression) {
        return expression.replaceAll("\\s+", " ");
    }

}
