package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by yellowstar on 8/13/15.
 */
public class Tree {
    public List<Integer> preorder(TreeNode root) {
        List<Integer> preorder = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                preorder.add(cur.val);
                cur = cur.left;
            }
            else {
                cur = stack.pop().right;
            }
        }
        return preorder;
    }

    public List<Integer> inorder(TreeNode root) {
        List<Integer> inorder = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            else {
                cur = stack.pop();
                inorder.add(cur.val);
                cur = cur.right;
            }
        }
        return inorder;
    }

    public List<Integer> postorder(TreeNode root) {
        List<Integer> postorder = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode lastVisited = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            else {
                TreeNode node = stack.peek();
                if (node.right != null && lastVisited != node.right) {
                    cur = node.right;
                }
                else {
                    postorder.add(node.val);
                    stack.pop();
                }
            }
        }
        return postorder;
    }

    // you can finish preorder and inorder traversal at one time
    public void preinorder(TreeNode root) {
        List<Integer> preorder = new ArrayList<>();
        List<Integer> inorder = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                preorder.add(cur.val);
                cur = cur.left;
            }
            else {
                cur = stack.pop();
                inorder.add(cur.val);
                cur = cur.right;
            }
        }
    }
}
