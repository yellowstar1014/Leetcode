package src;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by yellowstar on 8/26/15.
 */
public class Tester {
    public static void main(String[] args) {
        String i1 = "11";
        String i2 = "11";
        MyHashTable<Object, Integer> table = new MyHashTable<>(1024);
        table.put(i1, 1);
        int a = table.get("11");
    }
}
