package src.LinkedList;

import src.ListNode;

/**
 * Created by yellowstar on 10/15/15.
 */
public class ReorderList {
    public static void main(String[] args) {
        ReorderList reorderList = new ReorderList();
        // 0->3->1->-1->null
        ListNode n1 = new ListNode(0);
        ListNode n2 = new ListNode(3);
        ListNode n3 = new ListNode(1);
        ListNode n4 = new ListNode(-1);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        reorderList.reorderList(n1);
    }
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode l2 = reverse(slow.next);
        slow.next = null;
        ListNode l1 = head;
        while (l2 != null) {
            ListNode next1 = l1.next;
            ListNode next2 = l2.next;
            l1.next = l2;
            l2.next = next1;
            l1 = next1;
            l2 = next2;
        }

    }

    private ListNode reverse(ListNode node) {
        ListNode head = node;
        node = node.next;
        head.next = null;
        while (node != null) {
            ListNode next = node.next;
            node.next = head;
            head = node;
            node = next;
        }
        return head;
    }

}
