package src;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yellowstar on 8/26/15.
 */
public class MyHashTable<K, V> {
    private class Entry<K, V> {
        K key;
        V value;
        int hash;

        public Entry(K key, V value, int hash) {
            this.key = key;
            this.value = value;
            this.hash = hash;
        }
    }
    private int initialCapacity = 1024;
    private List<Entry<K, V>>[] table;

    public MyHashTable() {
        this.table = new LinkedList[initialCapacity];
    }
    public MyHashTable(int initialCapacity) {
        if (initialCapacity <= 0) throw new IllegalArgumentException();
        this.initialCapacity = initialCapacity;
        this.table = new LinkedList[initialCapacity];
    }

    public void put(K key, V value) {
        if (key == null || value  == null) {
            throw new NullPointerException();
        }
        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % table.length;
        List<Entry<K, V>> list = table[index];
        if (list == null) {
            list = new LinkedList<>();
            table[index] = list;
        }
        for (Entry<K, V> entry : list) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }
        list.add(new Entry<>(key, value, hash));
    }

    public V get(K key) {
        if (key == null) {
            throw new NullPointerException();
        }
        int index = key.hashCode() % table.length;
        List<Entry<K, V>> list = table[index];
        if (list == null) return null;
        for (Entry<K, V> entry : list) {
            if (entry.key == key) {
                return entry.value;
            }
        }
        return null;
    }
}
