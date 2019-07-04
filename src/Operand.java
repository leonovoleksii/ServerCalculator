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

    // unit tests
    public static void main(String[] args) {
        String expr = new String(".3");
        Operand op = new Operand(expr);
        System.out.println(op.getValue());
    }
}
