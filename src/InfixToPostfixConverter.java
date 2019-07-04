public class InfixToPostfixConverter {
    private String expression;
    private int curInx; // pointer on the character in the expression

    public InfixToPostfixConverter(String expression) {
        this.expression = expression;
        curInx = 0;
    }

    private Operand parseOperand(int begin) {
        char currChar;
        do {
            currChar = expression.charAt(curInx);
            curInx++;
        } while (curInx < expression.length() && (currChar >= '0' && currChar <= '9' || currChar == '.'));
        curInx = curInx == expression.length() ? curInx : curInx - 1;

        return new Operand(expression.substring(begin, curInx));
    }

    private Operator parseOperator() {
        return new Operator(expression.charAt(curInx++));
    }

    public void convert() {
        String postfix = "";
        while (curInx < expression.length()) {
            if (expression.charAt(curInx) >= '0' && expression.charAt(curInx) <= '9' || expression.charAt(curInx) == '.') {
                Operand operand = parseOperand(curInx);
                postfix = postfix.concat(operand.getValue().toString());
            } else {
                Operator operator = parseOperator();
                postfix = postfix.concat(Character.toString(operator.getValue()));
            }
        }
        expression = postfix;
    }

    public String getExpression() {
        return expression;
    }

    public static void main(String[] args) {
        InfixToPostfixConverter converter = new InfixToPostfixConverter("123+234");
        System.out.println(converter.getExpression());
        converter.convert();
        System.out.println(converter.getExpression());
    }
}
