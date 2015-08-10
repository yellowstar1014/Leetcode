package src;

import java.util.Stack;

/**
 * Created by yellowstar on 8/9/15.
 */
public class BasicCalculator2 {
    public static void main(String[] args) {
        BasicCalculator2 calculator2 = new BasicCalculator2();
        System.out.println(calculator2.calculate("2 * 3 - 4 / 4"));
        System.out.println(calculator2.calculate2("2 * 3 - 4 / 4"));
    }

    // without stack space = O(1)
    public int calculate(String s) {
        char[] cs = s.toCharArray();
        int ret = 0;
        int num = 0;
        int sign = 1;
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == ' ') continue;
            if (Character.isDigit(cs[i])) {
                num = num * 10 + (cs[i] - '0');
            } else {
                switch (cs[i]) {
                    case '+':
                        ret += num * sign;
                        sign = 1;
                        num = 0;
                        break;
                    case '-':
                        ret += num * sign;
                        sign = -1;
                        num = 0;
                        break;
                    case '*':
                        // read num;
                        int n = 0;
                        while (i + 1 < cs.length && (cs[i + 1] == ' ' || Character.isDigit(cs[i + 1]))) {
                            if (cs[i + 1] != ' ')
                                n = n * 10 + (cs[i + 1] - '0');
                            i++;
                        }
                        num = num * n;
                        break;
                    case '/':
                        // read num
                        int m = 0;
                        while (i + 1 < cs.length && (cs[i + 1] == ' ' || Character.isDigit(cs[i + 1]))) {
                            if (cs[i + 1] != ' ')
                                m = m * 10 + cs[i + 1] - '0';
                            i++;
                        }
                        num = num / m;
                        break;
                }
            }
        }
        return ret + num * sign;
    }

    // with stack
    public int calculate2(String s) {
        char[] cs = s.toCharArray();
        char op = '+';
        int num = 0;
        Stack<Integer> stack = new Stack();
        for (int i = 0; i < cs.length; i++) {
            if (Character.isDigit(cs[i])) {
                num = num * 10 + (cs[i] - '0');
            }
            if (i == cs.length - 1 || !Character.isDigit(cs[i]) && cs[i] != ' ') {
                switch (op) {
                    case '-':
                        stack.push(-num);
                        break;
                    case '+':
                        stack.push(num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    case '/':
                        stack.push(stack.pop() / num);
                        break;
                }
                op = cs[i];
                num = 0;
            }
        }

        int ret = 0;
        for (Integer i : stack) {
            ret += i;
        }
        return ret;
    }
}
