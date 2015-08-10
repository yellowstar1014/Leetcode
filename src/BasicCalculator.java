package src;

import java.util.Stack;

/**
 * Created by yellowstar on 8/9/15.
 */
public class BasicCalculator {
    public static void main(String[] args) {
        BasicCalculator calculator = new BasicCalculator();
        System.out.println(calculator.calculate("1 - (1 + 1 - (1 - 1) - 1)"));
    }

    // a - (b + c) -> a + -{b + c}
    // a - (b + c - (d - e) - f) -> a + -{b + c - (d -e) - f} -> a + -{b + c + -{d - e} - f}
    // a + b + c + d - e - f
    //{+ {-       {-    }   }}
    public int calculate(String s) {
        char[] cs = s.toCharArray();
        int op = 1;
        int result = 0;
        Stack<Integer> stack = new Stack();
        stack.push(1);
        for (int i = 0; i < cs.length; i++) {
            switch (cs[i]) {
                case ' ':
                    break;
                case '+':
                    op = 1;
                    break;
                case '-':
                    op = -1;
                    break;
                case '(':
                    stack.push(op * stack.peek());
                    op = 1;
                    break;
                case ')':
                    stack.pop();
                    break;
                default:
                    int num = cs[i] - '0';
                    while (i + 1 < cs.length) {
                        if (Character.isDigit(cs[i + 1]))
                            num = num * 10 + cs[++i] - '0';
                        else if (cs[i + 1] == ' ') {
                            ++i;
                            continue;
                        } else break;
                    }
                    result += num * op * stack.peek();
            }
        }
        return result;
    }
}
