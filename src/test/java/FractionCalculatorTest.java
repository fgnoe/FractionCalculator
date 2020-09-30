import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FractionCalculatorTest {
    @Test
    public void testCalculate() {
        //Provided examples
        assertEquals("1_7/8", FractionCalculator.calculate("1/2 * 3_3/4"));
        assertEquals("3_1/2", FractionCalculator.calculate("2_3/8 + 9/8"));

        //Subtraction and division
        assertEquals("1/2", FractionCalculator.calculate("1_7/8 / 3_3/4"));
        assertEquals("1_1/8", FractionCalculator.calculate("3_1/2 - 2_3/8"));

        //Just simplfication
        assertEquals("15_15/31", FractionCalculator.calculate("12_432/124"));

        //Complex expressions with multiple operations
        assertEquals("1_1/2", FractionCalculator.calculate("1/2 * 3_3/4 - 1/2 * 3_3/4 + 1_7/8 / 3_3/4 + 1"));
        assertEquals("-2", FractionCalculator.calculate("1/2 * 1/2 * 1/4 / 1/16 + 5 - 4 * 2"));
    }
}
