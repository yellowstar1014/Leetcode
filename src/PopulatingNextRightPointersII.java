package src;

/**
 * Created by yellowstar on 8/9/15.
 */
public class PopulatingNextRightPointersII {
    public static void main(String[] args) {
        PopulatingNextRightPointersII populatingNextRightPointersII = new PopulatingNextRightPointersII();
        TreeLinkNode root = TreeCreator.create("1,2,#,3,#,4,#,5");
        TreeCreator.print(root);
        populatingNextRightPointersII.connect(root);
    }

    public void connect(TreeLinkNode root) {
        if (root == null) return;
        TreeLinkNode cur = root;
        do {
            TreeLinkNode tmpChild = new TreeLinkNode(0);
            TreeLinkNode curChild = tmpChild;
            while (cur != null) {
                if (cur.left != null) {
                    curChild.next = cur.left;
                    curChild = cur.left;
                }
                if (cur.right != null) {
                    curChild.next = cur.right;
                    curChild = cur.right;
                }
                cur = cur.next;
            }
            cur = tmpChild.next;
        }
        while (cur != null);
    }
}
