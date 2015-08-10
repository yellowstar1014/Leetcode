package src;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yellowstar on 8/9/15.
 */
public class TreeCreator {
    public static void main(String[] args) {
        TreeLinkNode root = create("1,#,2");
        print(root);
    }

    public static void print(TreeLinkNode root) {
        Queue<TreeLinkNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean flag = false;
            while (size > 0) {
                TreeLinkNode cur = queue.poll();
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

    public static TreeLinkNode create(String s) {
        String[] ss = s.split(",");
        int len = ss.length;
        Queue<TreeLinkNode> queue = new LinkedList<>();
        TreeLinkNode root = new TreeLinkNode(Integer.valueOf(ss[0]));
        queue.offer(root);
        int i = 1;
        while (!queue.isEmpty() && i < len) {
            int size = queue.size();
            while (size > 0) {
                TreeLinkNode cur = queue.poll();
                if (!ss[i].equals("#")) {
                    cur.left = new TreeLinkNode(Integer.valueOf(ss[i]));
                    queue.offer(cur.left);
                }
                i++;
                if (i >= ss.length) break;
                if (!ss[i].equals("#")) {
                    cur.right = new TreeLinkNode(Integer.valueOf(ss[i]));
                    queue.offer(cur.right);
                }
                i++;
                size--;
            }
        }
        return root;
    }
}
