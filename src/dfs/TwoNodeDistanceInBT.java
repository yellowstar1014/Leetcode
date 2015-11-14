package src.dfs;

import src.TreeNode;

/**
 * Created by yellowstar on 11/2/15.
 */
public class TwoNodeDistanceInBT {
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
        System.out.println(distance(a, h, i));
        System.out.println(distance(a, h, f));
        System.out.println(distance(a, b, i));
    }
    public static int distance (TreeNode root, TreeNode p, TreeNode q) {
        TreeNode lca = LowestCommonAncestor(root, p, q);
        return helper(lca, p, 0) + helper(lca, q, 0);
    }

    private static TreeNode LowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root == p || root == q) return root;
        TreeNode left = LowestCommonAncestor(root.left, p, q);
        TreeNode right = LowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root;
        return left != null ? left : right;
    }

    private static int helper(TreeNode root, TreeNode target, int dist) {
        if (root == null) return -1;
        if (root == target) return dist;
        int left = helper(root.left, target, dist + 1);
        int right = helper(root.right, target, dist + 1);
        return left != -1 ? left : right;
    }
}
