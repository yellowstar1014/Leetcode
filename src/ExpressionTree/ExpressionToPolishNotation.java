package src.ExpressionTree;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by yellowstar on 9/22/15.
 */
public class ExpressionToPolishNotation {
    // () -> highest Priority
    // * / -> second
    // - + -> lowest
    // deal with higher priority first
    // save the low priority for future use
    // -> stack
    // -> number -> print out
    // -> stack is empty or stack top is ( -> push onto stack
    // -> ( -> push onto stack
    // -> ) -> pop the stack and print the operators until you see a (
    // -> - or + -> if stack top is not ( -> print out stack top -> push - or +
    // -> * or / -> if stack top is * or / -> print out stack top -> push * or /
    // corner case
    // expression is empty or null -> return empty
    // expression is invalid -> won't happen
    public class ExpressionTreeNode {
        public String symbol;
        public ExpressionTreeNode left, right;
        public ExpressionTreeNode(String symbol) {
            this.symbol = symbol;
            this.left = this.right = null;
        }
    }

    public static void main(String[] args) {
        ExpressionToPolishNotation ex = new ExpressionToPolishNotation();
        String[] expression = {"5","-","6", "*","(","7","-","2",")","/","3"};
        ExpressionTreeNode root = ex.build(expression);
        //ArrayList<String> list1 = ex.convertToRPN(expression);
        //ArrayList<String> list2 = ex.convertToPN(expression);
    }

    public ExpressionTreeNode build(String[] expression) {
        int len = expression.length;
        if (len == 0) return null;
        Stack<String> stack = new Stack<>();
        Stack<ExpressionTreeNode> nodeStack = new Stack<>();
        for (String sub : expression) {
            if (isOperator(sub)) {
                if (sub.equals("(")) {
                    stack.push(sub);
                } else if (sub.equals(")")) {
                    while (stack.peek() != "(") {
                        ExpressionTreeNode node = new ExpressionTreeNode(stack.pop());
                        node.right = nodeStack.pop();
                        node.left = nodeStack.pop();
                        nodeStack.push(node);
                    }
                    stack.pop();
                } else {
                    int priority = getPriority(sub);
                    while (!stack.isEmpty() && priority <= getPriority(stack.peek())) {
                        ExpressionTreeNode node = new ExpressionTreeNode(stack.pop());
                        node.right = nodeStack.pop();
                        node.left = nodeStack.pop();
                        nodeStack.push(node);
                    }
                    stack.push(sub);
                }
            }
            else {
                nodeStack.push(new ExpressionTreeNode(sub));
            }
        }
        while (!stack.isEmpty()) {
            ExpressionTreeNode node = new ExpressionTreeNode(stack.pop());
            node.right = nodeStack.pop();
            node.left = nodeStack.pop();
            nodeStack.push(node);
        }
        return nodeStack.pop();
    }

    public ArrayList<String> convertToRPN(String[] expression) {
        ArrayList<String> ret = new ArrayList<>();
        int len = expression.length;
        if (len == 0) return ret;
        Stack<String> stack = new Stack<>();
        for (String sub : expression) {
            if (isOperator(sub)) {
                if (sub.equals("(")) {
                    stack.push(sub);
                } else if (sub.equals(")")) {
                    while (stack.peek() != "(") {
                        ret.add(stack.pop());
                    }
                    stack.pop();
                } else {
                    int priority = getPriority(sub);
                    while (!stack.isEmpty() && priority <= getPriority(stack.peek())) {
                        ret.add(stack.pop());
                    }
                    stack.push(sub);
                }
            }
            else {
                ret.add(sub);
            }
        }
        while (!stack.isEmpty()) {
            ret.add(stack.pop());
        }
        return ret;
    }

    public ArrayList<String> convertToPN(String[] expression) {
        ArrayList<String> ret = new ArrayList<>();
        int len = expression.length;
        if (len == 0) return ret;
        Stack<String> stack = new Stack<>();
        reverse(expression);
        for (String sub : expression) {
            if (isOperator(sub)) {
                if (sub.equals(")")) {
                    stack.push(sub);
                } else if (sub.equals("(")) {
                    while (stack.peek() != ")") {
                        ret.add(0, stack.pop());
                    }
                    stack.pop();
                } else {
                    int priority = getPriority(sub);
                    while (!stack.isEmpty() && priority < getPriority(stack.peek())) {
                        ret.add(0, stack.pop());
                    }
                    stack.push(sub);
                }
            }
            else {
                ret.add(0, sub);
            }
        }
        while (!stack.isEmpty()) {
            ret.add(0, stack.pop());
        }
        return ret;
    }

    private void reverse(String[] expression) {
        int l = 0;
        int r = expression.length - 1;
        while (l < r) {
            String tmp = expression[l];
            expression[l] = expression[r];
            expression[r] = tmp;
            l++;
            r--;
        }
    }

    private boolean isOperator(String sub) {
        return sub.equals("*") || sub.equals("/") || sub.equals("+") || sub.equals("-") || sub.equals("(") || sub.equals(")");
    }

    private int getPriority(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 0;
            case "*":
            case "/":
                return 1;
        }
        return -1;
    }
}
