package src;

/**
 * Created by yellowstar on 9/18/15.
 */
public class SegmentTreeNode {
    int start;
    int end;
    int min;
    SegmentTreeNode left;
    SegmentTreeNode right;
    public SegmentTreeNode(int start, int end, int min) {
        this.start = start;
        this.end = end;
        this.min = min;
    }

    public SegmentTreeNode(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
