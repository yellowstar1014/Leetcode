package src.Graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by yellowstar on 10/24/15.
 */
public class AlienDictionary {
    public static void main(String[] args) {
        String[] words = {"wrt","wrf","er","ett","rftt"};
        String ret = alienOrder(words);
    }
    public static String alienOrder(String[] words) {
        Map<Character, Node> map = new HashMap<>();
        for(int j = 0; j < words[0].length(); j++) {
            char c = words[0].charAt(j);
            Node cur = map.get(c);
            if (cur == null) {
                cur = new Node(c);
                map.put(c, cur);
            }
        }
        for (int i = 1; i < words.length; i++) {
            int len = Math.min(words[i - 1].length(), words[i].length());
            int j = 0;
            boolean flag = false;
            while (j < len) {
                char first = words[i - 1].charAt(j);
                char second = words[i].charAt(j);
                if (first != second && !flag) {
                    Node pre = map.get(first);
                    Node cur = map.get(second);
                    if (cur == null) {
                        cur = new Node(second);
                        map.put(second, cur);
                    }
                    pre.neighbors.add(cur);
                    flag = true;
                } else if (first != second && flag) {
                    Node cur = map.get(second);
                    if (cur == null) {
                        cur = new Node(second);
                        map.put(second, cur);
                    }
                }
                j++;
            }
            while (j < words[i].length()) {
                char c = words[i].charAt(j);
                Node cur = map.get(c);
                if (cur == null) {
                    cur = new Node(c);
                    map.put(c, cur);
                }
                j++;
            }
        }
        Stack<Character> stack = new Stack<>();
        for (Node node : map.values()) {
            if (!dfs(node, stack)) return "";
        }
        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }
        return sb.toString();
    }

    private static boolean dfs(Node cur, Stack<Character> stack) {
        if (cur.status == 2) return true;
        if (cur.status == 1) return false;
        cur.status = 1;
        for (Node node : cur.neighbors) {
            if (!dfs(node, stack)) return false;
        }
        stack.push(cur.label);
        cur.status = 2;
        return true;
    }
}
