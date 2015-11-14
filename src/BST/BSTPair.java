package src.BST;

import src.TreeNode;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
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
        //int[] ret = bstPair.findPair(root, 17);
        //System.out.println(bstPair.predecessor(root, 12).val);
        int[] preorder = {10, 5, 1, 0, 7, 2, 40, 50};
        //print(bstPair.contructBST(preorder));
        System.out.println(bstPair.isValidBST(preorder));
        TreeNode root1 = bstPair.contructBST(preorder);
        TreeNode root2 = bstPair.buildeBST(preorder);
    }

    private int preIndex = 0;
    public boolean isValidBST(int[] preorder) {
        int len = preorder.length;
        if (len == 0) return false;
        if (len == 1) return true;
        int lower = -1;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            if (lower > -1 && lower > preorder[i]) {
                return false;
            }
            while (!stack.empty() && stack.peek() < preorder[i]) {
                lower = stack.pop();
            }
            stack.push(preorder[i]);
        }
        return true;
    }

    public TreeNode buildeBST(int[] preorder) {
        int len = preorder.length;
        if (len == 0) return null;
        TreeNode p = null;
        boolean isLeft = true;
        TreeNode root = new TreeNode(preorder[0]);
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        for (int i = 1; i < len; i++) {
            if (p != null && p.val > preorder[i]) {
                throw new RuntimeException();
            }
            while (!stack.empty() && stack.peek().val < preorder[i]) {
                p = stack.pop();
                isLeft = false;
            }
            TreeNode cur = new TreeNode(preorder[i]);
            if (isLeft) {
                stack.peek().left = cur;
            } else {
                p.right = cur;
                isLeft = true;
            }
            stack.push(cur);
        }
        return root;
    }


    public TreeNode contructBST(int[] preorder) {
        int len = preorder.length;
        if (len == 0) return null;
        return constructHelper(Integer.MIN_VALUE, Integer.MAX_VALUE, preorder);
    }

    public TreeNode constructHelper(int min, int max, int[] preorder) {
        if (preIndex == preorder.length) return null;
        if (preorder[preIndex] > min && preorder[preIndex] < max) {
            TreeNode root = new TreeNode(preorder[preIndex++]);
            TreeNode left = constructHelper(min, root.val, preorder);
            TreeNode right = constructHelper(root.val, max, preorder);
            root.left = left;
            root.right = right;
            return root;
        }
        return null;
    }

    public TreeNode predecessor(TreeNode root, int value) {
        TreeNode cur = root;
        while (cur != null) {
            if (value < cur.val) {
                cur = cur.left;
            } else if (value > cur.val) {
                cur = cur.right;
            } else {
                break;
            }
        }
        if (cur == null) throw new NoSuchElementException();
        if (cur.left != null) {
            return findMax(cur.left); // the max element in the left sub-tree
        } else {
            // the first left ancestor (value is in its right subtree)
            TreeNode ancestor = root;
            TreeNode pre = null;
            while (ancestor != cur) {
                if (value > ancestor.val) {
                    pre = ancestor;
                    ancestor = ancestor.right;
                }
                else {
                    ancestor = ancestor.left;
                }
            }
            return pre;
        }
    }

    private TreeNode findMax(TreeNode root) {
        while (root.right != null) {
            root = root.right;
        }
        return root;
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

    public static void print(TreeNode root) {
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
                }
                else {
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
