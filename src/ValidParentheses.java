package src;

import java.util.Stack;

/**
 * Created by yellowstar on 9/5/15.
 */
public class ValidParentheses {
    public boolean isValidParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            switch (c) {
                case '(':
                case '{':
                case '[': stack.push(c);
                    break;
                case ')': if (!stack.isEmpty() && stack.pop() == '(') break;
                          return false;
                case ']': if (!stack.isEmpty() && stack.pop() == '(') break;
                          return false;
                case '}': if (!stack.isEmpty() && stack.pop() == '(') break;
                          return false;
            }
        }
        return stack.isEmpty();
    }
}
