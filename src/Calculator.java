import java.io.*;
import java.util.Stack;

public class Calculator {
    private BufferedWriter writer;
    private InfixToPostfixConverter converter;

    public Calculator(OutputStreamWriter out) {
        writer = new BufferedWriter(out);
    }

    public void calculate(String expression) throws IOException {
        Stack<Operand> operands = new Stack<>();
        expression = removeSpaces(expression);
        if (!checkValidity(expression)) {
            return;
        }
        converter = new InfixToPostfixConverter(expression);
        converter.convert();
        for (String s : converter.getPostfixExpression()) {
            if (Operator.isOperator(s)) {
                Operand second = operands.pop();
                Operand first = operands.pop();
                operands.push((new Operator(s)).apply(first, second));
            } else {
                operands.push(new Operand(s));
            }
        }
        if (operands.empty()) {
            writer.write(0 + "\n");
        } else {
            writer.write(operands.pop().getValue().toString() + "\n");
        }
        writer.flush();
    }

    // returns true iff char c can be used in operand notation
    private static boolean isOperandSym(char c) {
        return c >= '0' && c <= '9' || c == '.';
    }

    // returns true iff operands and operators in the expression are valid
    private boolean checkOps(String expr) throws IOException {
        // build a string without parentheses
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < expr.length(); i++) {
            if (!(expr.charAt(i) == '(' || expr.charAt(i) ==')')) {
                sb.append(expr.charAt(i));
            }
        }
        String exprWithoutParen = sb.toString();

        // check for validity operands and operators
        int inx = 0;
        final boolean OPERAND = true;
        final boolean OPERATOR = false;
        boolean turn = OPERAND;
        while (inx < exprWithoutParen.length()) {
            if (turn) {
                int begin = inx, end = inx;
                while (end < exprWithoutParen.length() && isOperandSym(exprWithoutParen.charAt(end))) {
                    end++;
                }
                if (!Operand.isOperand(exprWithoutParen.substring(begin, end))) {
                    writer.write("Wrong operand at symbol " + inx + "\n");
                    writer.flush();
                    return false;
                }
                inx = end;
                turn = OPERATOR;
            } else {
                if (!Operator.isOperator(exprWithoutParen.charAt(inx))) {
                    writer.write("Wrong operator at symbol " + inx + "\n");
                    writer.flush();
                    return false;
                }
                inx++;
                turn = OPERAND;
            }
        }
        return true;
    }

    // returns true iff the sequence of parentheses in the expression is right
    private boolean checkParen(String expr) throws IOException {
        int cnt = 0;
        for (int i = 0; i < expr.length(); i++) {
            if (expr.charAt(i) == '(') cnt++;
            if (expr.charAt(i) == ')') cnt--;
            if (cnt < 0) return false;
        }
        return cnt == 0;
    }

    // returns true iff the expression is valid
    private boolean checkValidity(String expr) throws IOException {
        return checkOps(expr) && checkParen(expr);
    }

    // removes spaces from expression
    private String removeSpaces(String expression) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            if (!Character.isSpaceChar(expression.charAt(i)))
                sb.append(expression.charAt(i));
        }
        return sb.toString();
    }

    // unit testing
    public static void main(String[] args) throws IOException {
        Calculator calculator = new Calculator(new OutputStreamWriter(System.out));
        calculator.calculate("(2+2)*2");
    }
}
