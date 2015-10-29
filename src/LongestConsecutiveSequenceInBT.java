package src;

/**
 * Created by yellowstar on 10/28/15.
 */
public class LongestConsecutiveSequenceInBT {
    public static void main(String[] args) {
        TreeNode a = new TreeNode(3);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(4);
        TreeNode d = new TreeNode(3);
        a.left = b;
        a.right = c;
        b.left = d;
        int ret = new LongestConsecutiveSequenceInBT().longestConsecutive(a);
    }
    private int ret = 1;
    public int longestConsecutive(TreeNode root) {
        if (root == null) return 0;
        int tmp = helper(root);
        ret = Math.max(ret, tmp);
        return ret;
    }

    private int helper(TreeNode root) {
        int len = 1;
        if (root.left != null) {
            int left = helper(root.left);
            if (root.val + 1 == root.left.val) {
                len = Math.max(len, left + 1);
            } else {
                ret = Math.max(ret, left);
            }
        }
        if (root.right != null) {
            int right = helper(root.right);
            if (root.val + 1 == root.right.val) {
                len = Math.max(len, right + 1);
            } else {
                ret = Math.max(ret, right);
            }
        }
        return len;
    }
}
