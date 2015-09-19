package src;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yellowstar on 9/18/15.
 */
public class SegmentTree {
    SegmentTreeNode root;
    public static void main(String[] args) {
        int[] A = new int[] {1,2,7,8,5};
        SegmentTree segmentTree = new SegmentTree();
        segmentTree.build(A);
        segmentTree.print();
        int ret = segmentTree.query(segmentTree.root, 1, 2);
    }

    public void build(int[] num) {
        int len = num.length;
        if (len == 0) return;
        root = buildHelper(num, 0, len - 1);
    }

    private SegmentTreeNode buildHelper(int[] A, int start, int end) {
        SegmentTreeNode cur = new SegmentTreeNode(start, end);
        if (start == end) {
            cur.min = A[start];
            return cur;
        }
        int mid = (start + end) >> 1;
        SegmentTreeNode left = buildHelper(A, start, mid);
        SegmentTreeNode right = buildHelper(A, mid + 1, end);
        cur.left = left;
        cur.right = right;
        cur.min = Math.min(left.min, right.min);
        return cur;
    }

    public int query(SegmentTreeNode root, int start, int end) {
        if (root.start == start && root.end == end) return root.min;
        int mid = (root.start + root.end) >> 1;
        if (end <= mid) {
            return query(root.left, start, end);
        }
        else if (start > mid) {
            return query(root.right, start, end);
        }
        else {
            return Math.min(query(root.left, start, mid), query(root.right, mid + 1, end));
        }
    }

    public void print() {
        if (root == null) return;
        Queue<SegmentTreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                SegmentTreeNode node = queue.poll();
                System.out.print("[" + node.start + "," + node.end + "," + node.min + "] ");
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
                size--;
            }
            System.out.println();
        }
    }
}
