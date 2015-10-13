package src.ExpressionTree;

import java.util.Stack;

/**
 * Created by yellowstar on 10/9/15.
 */
public class ExpressionEval2 {
    private int base = 0;
    public static void main(String[] args) {
        ExpressionEval2 expressionEval2 = new ExpressionEval2();
        int ret = expressionEval2.eval("1+2*(4-(3-2))/2+2");
        System.out.println(ret);
    }
    private class Operator {
        char op;
        int priority;
        Operator(char op, int priority) {
            this.op = op;
            this.priority = priority;
        }
    }
    public int eval(String exp) {
        exp += '#';
        int len = exp.length();
        Stack<Integer> operand = new Stack<>();
        Stack<Operator> operator = new Stack<>();
        for (int i = 0; i < len; i++) {
            int j = i;
            while (!isOperator(exp.charAt(i))) i++;
            String snum = exp.substring(j, i);
            if (!snum.isEmpty()) {
                int num = Integer.valueOf(snum);
                operand.push(num);
            }
            char op = exp.charAt(i);
            if (op == '(') {
                base += 10;
            } else if (op == ')') {
                base -= 10;
            } else {
                while (!operator.empty() && operator.peek().priority >= getPriority(op)) {
                    int b = operand.pop();
                    int a = operand.pop();
                    char opr = operator.pop().op;
                    operand.push(calculate(a, b, opr));
                }
                operator.push(new Operator(op, getPriority(op)));
            }
        }
        return operand.pop();
    }

    private boolean isOperator(char c) {
        if (c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')' || c == '#') return true;
        return false;
    }

    private int calculate(int a, int b, char op) {
        int ret = 0;
        switch(op) {
            case '+': ret = a + b; break;
            case '-': ret = a - b; break;
            case '*': ret = a * b; break;
            case '/': ret = a / b; break;
        }
        return ret;
    }

    private int getPriority(char op) {
        int p = 0;
        switch(op) {
            case '+':
            case '-': p = 1; break;
            case '*':
            case '/': p = 2; break;
            case '#': p = 0;
        }
        return base + p;
    }
}
