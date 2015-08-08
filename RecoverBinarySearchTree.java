
/**
 * Created by yellowstar on 7/27/15.
 */
public class RecoverBinarySearchTree {
    public static void main(String[] args) {

    }
    public void recoverTree(TreeNode root) {
        // inorder traversal
        // BTS tree -> inorder traversal -> sorted ascending array
        // If an element is less than its previous element, pre is first element, cur may be the seconds
        // regular inorder traversal need space O(n) in the worst case
        // with Morris traversal only O(1) space required
        TreeNode pre = null, first = null, second = null, cur = root;
        while (cur != null) {
            if (cur.left == null) {
                if (pre != null && cur.val < pre.val) {
                    if (first == null) {
                        first = pre;
                        second = cur;
                    }
                    else {
                        second = cur;
                    }
                }
                pre = cur;
                cur = cur.right;
            }
            else {
                TreeNode tmp = cur.left;
                while (tmp.right != null && tmp.right != cur) tmp = tmp.right;
                if (tmp.right == null) {
                    tmp.right = cur;
                    cur = cur.left;
                }
                else {
                    if (pre != null && cur.val < pre.val) {
                        if (first == null) {
                            first = pre;
                            second = cur;
                        }
                        else {
                            second = cur;
                        }
                    }
                    pre = cur;
                    tmp.right = null;
                    cur = cur.right;
                }
            }
        }

        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
    }
}
