package src.dfs;

import src.Tree;
import src.TreeNode;

import java.util.*;

/**
 * Created by yellowstar on 10/24/15.
 */
public class PrintTree {
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
        //postorder(a);
        //columnOrder(a);
        lowestCommonAncestor(a, d, f);
    }

    public static List<List<Integer>> postorder(TreeNode root) {
        List<List<Integer>> rets = new LinkedList<>();
        if (root == null) return rets;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode lastVisited = null;
        while (cur != null || !stack.empty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode tmp = stack.peek();
                if (tmp.right != null && tmp.right != lastVisited) {
                    cur = tmp.right;
                } else {
                    if (tmp.left == null && tmp.right == null) {
                        List<Integer> path = new LinkedList<>();
                        for (TreeNode node : stack) {
                            path.add(node.val);
                        }
                        rets.add(path);
                    }
                    lastVisited = stack.pop();
                }
            }
        }
        return rets;
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode lastVisited = null;
        List<TreeNode> path1 = null;
        List<TreeNode> path2 = null;
        int count = 0;
        while (cur != null || !stack.empty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode pNode = stack.peek();
                if (pNode.right != null && pNode.right != lastVisited) {
                    cur = pNode.right;
                } else {
                    cur = stack.peek();
                    if (cur == p || cur == q) {
                        count++;
                        if (count == 1) {
                            path1 = new LinkedList<>(stack);
                        } else {
                            path2 = new LinkedList<>(stack);
                            break;
                        }
                    }
                    lastVisited = stack.pop();
                    cur = null;
                }
            }
        }
        if (path1 == null || path2 == null) return null;
        int len = Math.min(path1.size(), path2.size());
        TreeNode ret = null;
        for (int i = 1; i < len; i++) {
            if (path1.get(i) != path2.get(i)) {
                ret = path1.get(i - 1);
                break;
            }
        }
        if (ret == null) ret = path1.get(len - 1);
        return ret;
    }

    private static void bfsRoot2Leave(TreeNode root) {
        if (root == null) return;
        List<List<TreeNode>> paths = new ArrayList<>();
        List<TreeNode> onePath = new ArrayList<>();
        onePath.add(root);
        paths.add(onePath);
        while(true) {
            List<List<TreeNode>> newPaths = new ArrayList<>();
            boolean hasNextLevel = false;
            for (List<TreeNode> path : paths) {
                TreeNode cur = path.get(path.size() - 1);
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

    private static void columnOrder(TreeNode root) {
        List<List<Character>> left = new ArrayList<>();
        List<List<Character>> right = new ArrayList<>();
        helper(root, 0, 0, left, right);
        print(left, right);
    }

    private static void helper(TreeNode cur, int distance, int level, List<List<Character>> left, List<List<Character>> right) {
        if (cur == null) return;
        if (distance > 0) {
            insert(cur, distance - 1, level, right);
        } else {
            insert(cur, -distance, level, left);
        }
        helper(cur.left, distance - 1, level + 1, left, right);
        helper(cur.right, distance + 1, level + 1, left, right);
    }

    private static void insert(TreeNode cur, int distance, int level, List<List<Character>> list) {
        if (distance == list.size()) {
            List<Character> newList = new ArrayList<>();
            for (int i = 0; i < level; i++) {
                newList.add(' ');
            }
            newList.add((char)('0' + cur.val));
            list.add(newList);
        } else {
            List<Character> path = list.get(distance);
            for (int i = path.size(); i < level; i++) {
                path.add(' ');
            }
            path.add((char) ('0' + cur.val));
        }
    }

    private static void print(List<List<Character>> left, List<List<Character>> right) {
        for (int i = left.size() - 1; i >= 0; i--) {
            for (Character c : left.get(i)) {
                System.out.print(c + " ");
            }
            System.out.println();
        }

        for (List<Character> list : right) {
            for (Character c : list) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}
