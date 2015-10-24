package src.dfs;

import src.Tree;
import src.TreeNode;

import java.util.*;

/**
 * Created by yellowstar on 10/24/15.
 */
public class PrintTree {
    public void print() {

    }

    private void bfs(TreeNode root) {
        if (root == null) return;
        List<List<TreeNode>> paths = new ArrayList<>();
        List<TreeNode> onePath = new ArrayList<>();
        onePath.add(root);
        paths.add(onePath);
        while(true) {
            List<List<TreeNode>> newPaths = new ArrayList<>();
            boolean hasNextLevel = false;
            for (List<TreeNode> path : paths) {
                TreeNode cur = path.get(path.size());
                if (cur.left == null && cur.right == null) {
                    newPaths.add(path);
                    continue;
                }
                if (cur.left != null) {
                    List<TreeNode> newPath = new ArrayList<>(path);
                    newPath.add(cur.left);
                    newPaths.add(newPath);
                    hasNextLevel = true;
                }
                if (cur.right != null) {
                    path.add(cur.right);
                    newPaths.add(path);
                    hasNextLevel = true;
                }
            }
            paths = newPaths;
            if (!hasNextLevel) break;
        }
        for (List<TreeNode> path : paths) {
            for (TreeNode node : path) {
                System.out.print(node.val + " ");
            }
            System.out.println();
        }
    }
}
