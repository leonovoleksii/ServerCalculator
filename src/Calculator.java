import java.util.Scanner;
import java.util.Stack;

public class Calculator {
    private static InfixToPostfixConverter converter;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String expression;
        Stack<Operand> operands = new Stack<>();
        while (true) {
            System.out.println("Print the expression you want to calculate (print exit or ^C to exit):");
            expression = sc.next();
            if (expression.equals("exit")) break;
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
