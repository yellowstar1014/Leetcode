package src.String;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yellowstar on 10/12/15.
 */
public class LongestOrderedSubsquence {
    public static void main(String[] args) {
        LongestOrderedSubsquence longestOrderedSubsquence = new LongestOrderedSubsquence();
        String ret = longestOrderedSubsquence.getSubstring("abadbacabbaac", "abac");
        System.out.println(ret);
    }
    // walk through String s, if encounter a char which is the cur char in String p, increase cursor of p
    // cursor -> end of p -> get a answer
    // ababac, abac
    // ababac / abac  -> abac
    // char a -> head of p, start a new cursor of p
    public String getSubstring(String s, String p) {
        char[] cs = s.toCharArray();
        char[] ps = p.toCharArray();
        List<Integer> cursors = new ArrayList<>();
        List<Integer> starts = new ArrayList<>();
        int left = 0;
        int right = cs.length;
        for (int i = 0; i < cs.length; i++) {
            for (int j = 0; j < cursors.size(); j++) {
                int cursor = cursors.get(j);
                if (cursor < ps.length && cs[i] == ps[cursor]) {
                    cursor++;
                    cursors.set(j, cursor);
                    if (cursor == ps.length) {
                        if (i - starts.get(j) + 1 <= right - left) {
                            left = starts.get(j);
                            right = i + 1;
                        }
                    }
                }
            }
            if (cs[i] == ps[0]) {
                cursors.add(1);
                starts.add(i);
            }
        }
        return s.substring(left, right);
    }
}
