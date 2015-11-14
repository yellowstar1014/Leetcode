package src;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by yellowstar on 11/2/15.
 */
public class BT2DLL {
    public static void main(String[] args) {
        TreeNode a = new TreeNode(0);
        TreeNode b = new TreeNode(1);
        TreeNode c = new TreeNode(2);
        TreeNode d = new TreeNode(3);
        TreeNode e = new TreeNode(4);
        TreeNode f = new TreeNode(5);
        TreeNode g = new TreeNode(6);
        TreeNode h = new TreeNode(7);
        TreeNode i = new TreeNode(8);
        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        c.left = f;
        c.right = g;
        e.left = h;
        e.right = i;

        TreeNode ret = convert(a);
        ret.right = null;
        TreeNode tail = null;
        while (ret != null) {
            if (ret.left == null) tail = ret;
            System.out.print(ret.val + " ");
            ret = ret.left;
        }
        System.out.println();
        while (tail != null) {
            System.out.print(tail.val + " ");
            tail = tail.right;
        }
        System.out.println();
    }
    private static TreeNode tail = new TreeNode(0);
    public static TreeNode convert2DLL(TreeNode root) {
        if (root == null) return null;
        TreeNode right = root.right;
        TreeNode lhead = convert2DLL(root.left);
        tail.left = root;
        root.right = tail;
        tail = root;
        TreeNode rhead = convert2DLL(right);
        return lhead != null ? lhead : root;
    }

    public static TreeNode convert(TreeNode root) {
        TreeNode dummy = new TreeNode(0);
        TreeNode tail = dummy;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                TreeNode next = cur.right;
                tail.left = cur;
                cur.right = tail;
                tail = cur;
                cur = next;
            }
        }
        tail.left = null;
        dummy.left.right = null;
        return dummy.left;
    }
}
