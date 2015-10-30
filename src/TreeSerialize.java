package src;

import java.util.Stack;

/**
 * Created by yellowstar on 10/29/15.
 */
public class TreeSerialize {
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
        b.left = c;
        c.left = d;
        d.left = e;
        String s = serialize(a);
        TreeNode root = deserialize(s);
    }
    public static String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root == null) return "";
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                sb.append(cur.val);
                sb.append(',');
                cur = cur.left;
            } else {
                sb.append("#");
                sb.append(',');
                cur = stack.pop().right;
            }
        }
        sb.append("#");
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if (data.isEmpty()) return null;
        String[] vals = data.split(",");
        Stack<TreeNode> stack = new Stack<>();
        int i = 0;
        TreeNode dummy = new TreeNode(0);
        stack.push(dummy);
        TreeNode pre = dummy;
        boolean left = true;
        while (i < vals.length) {
            if (!vals[i].equals("#")) {
                TreeNode cur = new TreeNode(Integer.valueOf(vals[i++]));
                stack.push(cur);
                if (left) {
                    pre.left = cur;
                } else {
                    pre.right = cur;
                    left = true;
                }
                pre = cur;
            } else {
                if (left) {
                    i++;
                    left = false;
                    stack.pop();
                } else {
                    if (++i == vals.length) break;
                    pre = stack.pop();
                }
            }
        }
        return dummy.left;
    }
}
