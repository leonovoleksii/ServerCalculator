import java.util.Stack;
import java.util.LinkedList;

public class InfixToPostfixConverter {
    private String expression;
    private int curInx; // pointer on the character in the expression
    private LinkedList<String> postfix; // linked list with operand and operators in postfix form

    public InfixToPostfixConverter(String expression) {
        this.expression = expression;
        postfix = new LinkedList<>();
        curInx = 0;
    }

    private Operand parseOperand(int begin) {
        char currChar;
        do {
            currChar = expression.charAt(curInx);
            curInx++;
        } while (curInx < expression.length() && (currChar >= '0' && currChar <= '9' || currChar == '.'));
        curInx = curInx == expression.length() && currChar >= '0' && currChar <= '9' ? curInx : curInx - 1;

        return new Operand(expression.substring(begin, curInx));
    }

    private Operator parseOperator() {
        return new Operator(expression.charAt(curInx++));
    }

    // converts the expression from infix to postfix form
    public void convert() {
        Stack<Operator> operators = new Stack<>();
        curInx = 0;
        while (curInx < expression.length()) {
            if (expression.charAt(curInx) >= '0' && expression.charAt(curInx) <= '9' || expression.charAt(curInx) == '.') {
                Operand operand = parseOperand(curInx);
                postfix.add(operand.getValue().toString());
            } else {
                Operator operator = parseOperator();
                if (operator.getValue() == '(') {
                    operators.push(operator);
                } else if (operator.getValue() == ')') {
                    while (!operators.empty() && operators.peek().getValue() != '(') {
                        postfix.add(Character.toString(operators.pop().getValue()));
                    }
                    if (operators.peek().getValue() == '(') {
                        operators.pop();
                    }
                } else {
                    while (!operators.empty() && operator.compareTo(operators.peek()) <= 0) {
                        postfix.add(Character.toString(operators.pop().getValue()));
                    }
                    operators.push(operator);
                }
            }
        }
        while (!operators.empty()) {
            postfix.add(Character.toString(operators.pop().getValue()));
        }
    }

    public String getInfixExpression() {
        return expression;
    }

    public LinkedList<String> getPostfixExpression() {
        return postfix;
    }

    // unit testing
    public static void main(String[] args) {
        InfixToPostfixConverter converter = new InfixToPostfixConverter("2+(2*2)");
        System.out.println(converter.getInfixExpression());
        converter.convert();
        for (String s : converter.getPostfixExpression()) {
            System.out.print(s + " ");
        }
    }
}
