package src;

import java.util.HashMap;

/**
 * Created by yellowstar on 8/12/15.
 */
public class LRUCache {
    private class DListNode {
        int key;
        int value;
        DListNode prev;
        DListNode next;
        DListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    private HashMap<Integer, DListNode> map;
    private DListNode head;
    private DListNode tail;
    private int capacity;
    private int size;

    public LRUCache(int capacity) {
        map = new HashMap<>();
        head = new DListNode(0, 0);
        tail = new DListNode(0, 0);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
    }

    public int get(int key) {
        DListNode node = map.get(key);
        if (node == null) return -1;
        premote(node);
        return node.value;
    }

    public void set(int key, int value) {
        DListNode node = map.get(key);
        if (node != null) {
            node.value = value;
            premote(node);
        }
        else {
            node = new DListNode(key, value);
            map.put(key, node);
            node.next = tail;
            node.prev = tail.prev;
            tail.prev.next = node;
            tail.prev = node;
            size++;
        }
        if (size > capacity) removeOldest();
    }

    private void premote(DListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = tail;
        node.prev = tail.prev;
        tail.prev.next = node;
        tail.prev = node;
    }

    private void removeOldest() {
        DListNode node = head.next;
        head.next = node.next;
        node.next.prev = head;
        map.remove(node.key);
        size--;
    }
}
