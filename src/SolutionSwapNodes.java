package src;

/*
Given a linked list, swap every two adjacent nodes and return its head.
For example,
Given 1->2->3->4, you should return the list as 2->1->4->3.
Your algorithm should use only constant space. You may not modify the values
in the list, only nodes itself can be changed.
*/
public class SolutionSwapNodes {
    public static void main(String[] args) {
      ListNode a = new ListNode(1);
      ListNode b = new ListNode(2);
      ListNode c = new ListNode(3);
      ListNode d = new ListNode(4);
      ListNode e = new ListNode(5);
      a.next = b;
      b.next = c;
      c.next = d;
      d.next = e;
      ListNode x = swapPairs(a);

      printLinkedList(x);
    }

    public static ListNode swapPairs(ListNode head) {
        if (head == null) {
            return null;
        }

        if (head.next == null) {
            return head;
        }

        ListNode oldCurNode = head;
        ListNode oldNextNode = head.next.next;
        ListNode newhead = head.next;
        newhead.next = head;
        ListNode newCurNode = newhead.next;

        while (oldNextNode != null && oldNextNode.next != null) {
            oldCurNode = oldNextNode;
            oldNextNode = oldNextNode.next.next;
            newCurNode.next = oldCurNode.next;
            newCurNode.next.next = oldCurNode;
            newCurNode = newCurNode.next.next;
        }

        if (oldNextNode != null) {
            newCurNode.next = oldNextNode;
        }
        else {
          newCurNode.next = null;
        }

        return newhead;
    }

    public static void printLinkedList(ListNode head) {
      while(head != null) {
        System.out.print(head.val + " ");
        head = head.next;
      }
      System.out.println();
    }
}
