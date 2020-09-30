public class MixedNumber {
    private int whole;
    private int numerator;
    private int denominator;

    public MixedNumber(int whole, int numerator, int denominator) {
        this.whole = whole;
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public static MixedNumber parse(String mixedNumberString) {
        int whole = 0;
        int numerator = 0;
        int denominator = 1;
        if (hasInvalidFormat(mixedNumberString)) {
            throw new NumberFormatException();
        }
        if (mixedNumberString.contains("_")) {
            String[] wholeAndFraction = mixedNumberString.split("_");
            whole = Integer.parseInt(wholeAndFraction[0]);
            mixedNumberString = wholeAndFraction[1];
        }

        if (mixedNumberString.contains("/")) {
            String[] numeratorAndDenominator = mixedNumberString.split("/");
            numerator = Integer.parseInt(numeratorAndDenominator[0]);
            denominator = Integer.parseInt(numeratorAndDenominator[1]);
        } else {
            whole = Integer.parseInt(mixedNumberString);
        }

        return new MixedNumber(whole, numerator, denominator);
    }

    static boolean hasInvalidFormat(String expression) {
        return !expression.matches("([0-9]+\\/{1}[0-9]+)|([0-9]+)|([0-9]+\\_[0-9]+\\/{1}[0-9]+)");
    }

    public int getWhole() {
        return whole;
    }

    public void setWhole(int whole) {
        this.whole = whole;
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    public void add(MixedNumber n) {
        int oldDenominator = denominator;
        whole += n.whole;
        denominator *= n.denominator;
        numerator *= n.denominator;
        numerator += oldDenominator * n.numerator;
        simplify();
    }

    public void subtract(MixedNumber n) {
        int oldDenominator = denominator;
        whole -= n.whole;
        denominator *= n.denominator;
        numerator *= n.denominator;
        numerator -= oldDenominator * n.numerator;
        simplify();
    }

    public void multiply(MixedNumber n) {
        numerator += whole * denominator;
        whole = 0;
        numerator *= n.numerator + n.whole * n.denominator;
        denominator *= n.denominator;
        simplify();
    }

    public void divide(MixedNumber n) {
        numerator += whole * denominator;
        whole = 0;
        numerator *= n.denominator;
        denominator *= n.numerator + n.whole * n.denominator;
        simplify();
    }

    private void simplify() {
        whole += numerator / denominator;
        numerator %= denominator;
        int gdc = getGcd();
        numerator /= gdc;
        denominator /= gdc;
    }

    private int getGcd() {
        return getEuclidGcd(numerator, denominator);
    }

    private static int getEuclidGcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return getEuclidGcd(b, a % b);
    }

    @Override
    public String toString() {
        simplify();
        if (numerator == 0) {
            // In case of a integer.
            return Integer.toString(whole);
        }
        return String.format("%s%s/%s",
                (whole != 0 ? whole + "_" : ""),
                numerator,
                denominator);
    }
}
