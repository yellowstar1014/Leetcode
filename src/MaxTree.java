package src;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yellowstar on 9/19/15.
 */
public class MaxTree {
    public static void main(String[] args) {
        int[] A = new int[] {2,5,6,0,3,1};
        MaxTree maxTree = new MaxTree();
        maxTree.print(maxTree.maxTree(A));
    }

    public TreeNode maxTree(int[] A) {
        // write your code here
        int len = A.length;
        TreeNode[] stack = new TreeNode[len];
        int count = 0;
        for (int i = 0; i < len; i++) {
            TreeNode cur = new TreeNode(A[i]);
            if (count == 0) {
                stack[count++] = cur;
                break;
            }
            while (count > 0 && stack[count - 1].val < A[i]) {
                count--;
            }
            if (count == 0) {
                cur.left = stack[0];
            }
            else {
                cur.left = stack[count];
                stack[count - 1].right = cur;
            }
            stack[count++] = cur;
        }
        return stack[0];
    }

    private void print(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean flag = false;
            while (size > 0) {
                TreeNode cur = queue.poll();
                if (cur != null)
                    System.out.print(cur.val + " ");
                else
                    System.out.print("# ");
                if (cur == null) {
                    queue.add(null);
                    queue.add(null);
                } else {
                    if (cur.left != null || cur.right != null) flag = true;
                    queue.add(cur.left);
                    queue.add(cur.right);
                }
                size--;
            }
            System.out.println();
            if (!flag) break;
        }
    }
}
