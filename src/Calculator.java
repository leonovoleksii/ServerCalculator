import java.util.Scanner;
import java.util.Stack;

public class Calculator {
    private static InfixToPostfixConverter converter;

    // returns true iff char c can be used in operand notation
    private static boolean isOperandSym(char c) {
        return c >= '0' && c <= '9' || c == '.';
    }

    // returns true iff operands and operators in the expression are valid
    private static boolean checkOps(String expr) {
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
                    System.err.println("Wrong operand at symbol " + begin);
                    System.out.println();
                    return false;
                }
                inx = end;
                turn = OPERATOR;
            } else {
                if (!Operator.isOperator(exprWithoutParen.charAt(inx))) {
                    System.err.println("Wrong operator at symbol " + inx);
                    System.out.println();
                    return false;
                }
                inx++;
                turn = OPERAND;
            }
        }
        return true;
    }

    // returns true iff the sequence of parentheses in the expression is right
    private static boolean checkParen(String expr) {
        Stack<Character> parentheses = new Stack<>();
        for (int i = 0; i < expr.length(); i++) {
            char cur = expr.charAt(i);
            if (cur == '(' || cur == '[' || cur == '{') {
                parentheses.push(cur);
            } else if (cur == ')' || cur == ']' || cur == '}') {
                if (parentheses.empty())  {
                    System.err.println("Wrong sequence of parentheses");
                    System.out.println();
                    return false;
                }
                char top = parentheses.pop();
                switch (cur) {
                    case ')':
                        if (top != '(') {
                            System.err.println("Wrong sequence of parentheses");
                            System.out.println();
                            return false;
                        }
                        break;
                    case ']':
                        if (top != '[') {
                            System.err.println("Wrong sequence of parentheses");
                            System.out.println();
                            return false;
                        }
                        break;
                    case '}':
                        if (top != '{') {
                            System.err.println("Wrong sequence of parentheses");
                            System.out.println();
                            return false;
                        }
                        break;
                }
            }
        }
        if (!parentheses.empty()) {
            System.err.println("Wrong sequence of parentheses");
            System.out.println();
        }
        return parentheses.empty();
    }

    // returns true iff the expression is valid
    private static boolean checkValidity(String expr) {
        return checkOps(expr) && checkParen(expr);
    }

    // removes spaces from expression
    private static String removeSpaces(String expression) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            if (!Character.isSpaceChar(expression.charAt(i)))
                sb.append(expression.charAt(i));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String expression;
        Stack<Operand> operands = new Stack<>();
        while (true) {
            System.out.println("Print the expression you want to calculate (print exit or ^C to exit):");
            expression = sc.next();
            if (expression.equals("exit")) break;
            if (!checkValidity(expression)) {
                continue;
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
            System.out.println(operands.pop().getValue());
        }
    }
}
