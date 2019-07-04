import java.math.BigDecimal;
import java.lang.IllegalArgumentException;

public class Operand {
    // the value of the Operand
    private BigDecimal value;

    // initializes the Operand with the value that is stored in String value
    public Operand(String value) {
        if (value == null) throw new IllegalArgumentException("String with a value can't be empty");
        this.value = new BigDecimal(value);
    }

    // returns the value of the Operand
    public BigDecimal getValue() {
        return value;
    }

    public static boolean isOperand(String s) {
        int cntDot = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '.') cntDot++;
            else if (!(s.charAt(i) >= '0' && s.charAt(i) <= '9')) return false;
        }
        if (cntDot > 1 || cntDot == 1 && s.length() == 1 || s.length() == 0) return false;
        return true;
    }

    // unit tests
    public static void main(String[] args) {
        String expr = new String(".3");
        Operand op = new Operand(expr);
        System.out.println(op.getValue());
        System.out.println(isOperand("123"));
        System.out.println(isOperand("12.3"));
        System.out.println(isOperand("123."));
        System.out.println(isOperand(".123"));
        System.out.println(isOperand("123,"));
        System.out.println(isOperand("."));
        System.out.println(isOperand(""));
    }
}
