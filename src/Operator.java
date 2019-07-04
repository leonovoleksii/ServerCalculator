import java.lang.Comparable;
import java.lang.IllegalArgumentException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Operator implements Comparable<Operator> {
    private char value;
    private byte priority;

    public Operator(char value) {
        this.value = value;
        if (value == '+' || value == '-') priority = 0;
        else if (value == '*' || value == '/' || value == '%') priority = 1;
        else if (value == '(' || value == ')') priority = 2;
        else throw new IllegalArgumentException("Wrong operator");
    }

    public Operator(String value) {
        if (value.length() != 1) throw new IllegalArgumentException("Wrong operator");
        this.value = value.charAt(0);
        if (this.value == '+' || this.value == '-') priority = 0;
        else if (this.value == '*' || this.value == '/' || this.value == '%') priority = 1;
        else if (this.value == '(' || this.value == ')') priority = 2;
        this.value = value.charAt(0);
    }

    @Override
    public int compareTo(Operator op) {
        if (this.priority < op.priority) return -1;
        else if (this.priority > op.priority) return 1;
        else return 0;
    }

    public char getValue() {
        return value;
    }

    public byte getPriority() {
        return priority;
    }

    // returns true iff String s consists of one operator
    public static boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("(") || s.equals(")") || s.equals("*") || s.equals("/");
    }

    public Operand apply(Operand op1, Operand op2) {
        BigDecimal newOperandValue = BigDecimal.valueOf(0);
        switch (value) {
            case '+':
                newOperandValue = op1.getValue().add(op2.getValue());
                break;
            case '-':
                newOperandValue = op1.getValue().subtract(op2.getValue());
                break;
            case '*':
                newOperandValue = op1.getValue().multiply(op2.getValue());
                break;
            case '/':
                newOperandValue = op1.getValue().divide(op2.getValue(), RoundingMode.DOWN);
                break;
        }
        return new Operand(newOperandValue);
    }

    // unit testing
    public static void main(String[] args) {
        Operator op1 = new Operator('+');
        Operator op2 = new Operator('*');
        System.out.println(op1.compareTo(op2));
    }
}
