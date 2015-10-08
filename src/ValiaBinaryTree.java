package src;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by yellowstar on 10/4/15.
 */
public class ValiaBinaryTree {
    // 1. only one root
    // 2. have no cycle
    // 3. one node has at most one father
    public boolean isValid(TreeNode[] nodes) {
        int len = nodes.length;
        Map<TreeNode, TreeNode> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            if (map.containsKey(nodes[i].left) || map.containsKey(nodes[i].right)) {
                return false;
            } else {
                map.put(nodes[i].left, nodes[i]);
                map.put(nodes[i].right, nodes[i]);
            }
        }
        TreeNode root = null;
        for (int i = 0; i < len; i++) {
            TreeNode r = parent(nodes[i], map);
            if (r == null) return false;
            if (root == null) root = r;
            else if (root != r) return false;
        }
        return true;
    }

    private TreeNode parent(TreeNode node, Map<TreeNode, TreeNode> map) {
        HashSet<TreeNode> visited = new HashSet<>();
        TreeNode cur = node;
        TreeNode parent = map.get(cur);
        visited.add(cur);
        while (parent != null && cur != parent) {
            cur = parent;
            visited.add(cur);
            parent = map.get(cur);
            if (visited.contains(parent)) return null;
        }

        while (cur != node) {
            TreeNode p = map.get(node);
            map.put(node, cur);
            node = p;
        }
        return cur;
    }
}
