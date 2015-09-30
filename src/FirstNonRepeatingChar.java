package src;

import java.util.*;

/**
 * Created by yellowstar on 9/24/15.
 */
public class FirstNonRepeatingChar {
    public char firstNonRepeatingChar(String s) {
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.containsKey(c) ? map.get(c) + 1 : 1);
        }

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) return entry.getKey();
        }

        throw new RuntimeException();
    }

    public char firstNonRepeatingChar2(String s) {
        Set<Character> repeat = new HashSet<>();
        List<Character> nonRepeat = new LinkedList<>();
        for (char c : s.toCharArray()) {
            if (repeat.contains(c)) continue;
            if (nonRepeat.contains(c)) {
                nonRepeat.remove(c);
                repeat.add(c);
            } else {
                nonRepeat.add(c);
            }
        }
        return nonRepeat.get(0);
    }
}
