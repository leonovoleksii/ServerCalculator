import java.lang.Comparable;
import java.lang.IllegalArgumentException;
import java.math.BigDecimal;

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
                newOperandValue = op1.getValue().divide(op2.getValue());
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
