package src.bfs;

import src.TreeNode;

import java.util.*;

/**
 * Created by yellowstar on 11/2/15.
 */
public class PathSum {
    //bfs
    //all path sum
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
        List<Integer> pathSums = allPathSum(a);
    }

    public static List<Integer> allPathSum(TreeNode root) {
        List<Integer> ret = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        Map<TreeNode, Integer> map = new HashMap<>();
        map.put(root, root.val);
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.left != null) {
                map.put(cur.left, map.get(cur) + cur.left.val);
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                map.put(cur.right, map.get(cur) + cur.right.val);
                queue.offer(cur.right);
            }
            if (cur.left != null && cur.right != null) {
                map.remove(cur);
            }
        }
        ret.addAll(map.values());
        return ret;
    }
}
