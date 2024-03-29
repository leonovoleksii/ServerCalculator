import java.lang.Comparable;
import java.lang.IllegalArgumentException;

class Operator implements Comparable<Operator> {
    private char value;
    private byte priority;

    public Operator(char value) {
        this.value = value;
        if (value == '+' || value == '-') priority = 0;
        else if (value == '*' || value == '/' || value == '%') priority = 1;
        else if (value == '(' || value == ')') priority = -1;
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
        return Byte.compare(this.priority, op.priority);
    }

    public char getValue() {
        return value;
    }

    public byte getPriority() {
        return priority;
    }

    // returns true iff String s consists of one operator
    public static boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("(") || s.equals(")") || s.equals("*") || s.equals("/") || s.equals("%");
    }

    // returns true iff char c is operator
    public static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '(' || c == ')' || c == '*' || c == '/' || c =='%';
    }

    public Operand apply(Operand op1, Operand op2) {
        double newOperandValue = 0;
        switch (value) {
            case '+':
                newOperandValue = op1.getValue() + op2.getValue();
                break;
            case '-':
                newOperandValue = op1.getValue() - op2.getValue();
                break;
            case '*':
                newOperandValue = op1.getValue() * op2.getValue();
                break;
            case '/':
                newOperandValue = op1.getValue() / op2.getValue();
                break;
            case '%':
                newOperandValue = op1.getValue() % op2.getValue();
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
