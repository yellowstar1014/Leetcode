package src;

import java.util.Stack;

/**
 * Created by yellowstar on 10/2/15.
 */
public class BSTPair {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        TreeNode n1 = new TreeNode(5);
        TreeNode n2 = new TreeNode(15);
        TreeNode n11 = new TreeNode(3);
        TreeNode n12 = new TreeNode(8);
        TreeNode n21 = new TreeNode(12);
        TreeNode n22 = new TreeNode(18);
        root.left = n1;
        root.right = n2;
        n1.left = n11;
        n1.right = n12;
        n2.left = n21;
        n2.right = n22;
        BSTPair bstPair = new BSTPair();
        int[] ret = bstPair.findPair(root, 17);
    }
    public int[] findPair(TreeNode root, int k) {
        int[] ret = new int[2];
        if (root == null) return ret;
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        TreeNode cur1 = root;
        TreeNode cur2 = root;
        boolean moveLeft = true;
        boolean moveRright = true;
        while (true) {
            while (cur1 != null && moveLeft) {
                stack1.push(cur1);
                cur1 = cur1.left;
            }

            while (cur2 != null && moveRright) {
                stack2.push(cur2);
                cur2 = cur2.right;
            }

            if (cur1 == null) cur1 = stack1.pop();
            if (cur2 == null) cur2 = stack2.pop();
            if (cur1 == cur2) return ret;
            if (cur1.val + cur2.val < k) {
                cur1 = cur1.right;
                moveLeft = true;
                moveRright = false;
            } else if (cur1.val + cur2.val > k) {
                cur2 = cur2.left;
                moveRright = true;
                moveLeft = false;
            } else {
                ret[0] = cur1.val;
                ret[1] = cur2.val;
                return ret;
            }
        }
    }
}
