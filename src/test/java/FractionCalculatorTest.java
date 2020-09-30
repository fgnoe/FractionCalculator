import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class FractionCalculatorTest {
    @Test
    public void testCalculateBasicOperations() {
        //Provided examples
        assertEquals("1_7/8", FractionCalculator.calculate("1/2 * 3_3/4"));
        assertEquals("3_1/2", FractionCalculator.calculate("2_3/8 + 9/8"));
        //also subtraction and division
        assertEquals("1/2", FractionCalculator.calculate("1_7/8 / 3_3/4"));
        assertEquals("1_1/8", FractionCalculator.calculate("3_1/2 - 2_3/8"));
    }

    @Test
    public void testCalculateWithoutOperations() {
        //Just simplification
        assertEquals("15_15/31", FractionCalculator.calculate("12_432/124"));
        assertEquals("15", FractionCalculator.calculate("30/2"));
        //Not simplifiable
        assertEquals("15", FractionCalculator.calculate("15"));
    }

    @Test
    public void testCalculateComplexOperationsWithMixedOperators() {
        //Complex expressions with multiple operations
        assertEquals("1_1/2", FractionCalculator.calculate("1/2 * 3_3/4 - 1/2 * 3_3/4 + 1_7/8 / 3_3/4 + 1"));
        assertEquals("-2", FractionCalculator.calculate("1/2 * 1/2 * 1/4 / 1/16 + 5 - 4 * 2"));
    }

    @Test
    public void testCalculateInvalidExpressions() {
        //Invalid expressions
        assertThrows(NumberFormatException.class, () -> FractionCalculator.calculate(""));
        assertThrows(NumberFormatException.class, () -> FractionCalculator.calculate("asdf"));
        assertThrows(NumberFormatException.class, () -> FractionCalculator.calculate("1 1"));
        assertThrows(NumberFormatException.class, () -> FractionCalculator.calculate("123 +"));
        assertThrows(NumberFormatException.class, () -> FractionCalculator.calculate("1_2_3"));
        assertThrows(NumberFormatException.class, () -> FractionCalculator.calculate("1/2 1/3"));
        assertThrows(NumberFormatException.class, () -> FractionCalculator.calculate("1/2/3"));
    }
}
