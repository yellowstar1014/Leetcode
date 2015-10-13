package src.String;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yellowstar on 10/10/15.
 */
public class AnagramSubstring {
    public static void main(String[] args) {
        AnagramSubstring anagramSubstring = new AnagramSubstring();
        int ret = anagramSubstring.anagramSubstring("abacba", "abc");
    }
    // t -> hashmap<char, count>
    // i, j
    // s(j) not in map -> i move to j+1
    // s(j) in map -> map(s(j)) > 0 -> map(s(j))--, j++
    //              -> map(s(j)) = 0 -> i++, map(s(i)--
    // total(count) == t.len -> find a answer
    // corner cases
    // slen = 0, tlen = 0; return 1;
    // slen = 0, tlen > 0; return 0;
    // slen > 0, tlen = 0; return 1;
    // tlen > slen; return 0;
    public int anagramSubstring(String s, String t) {
        int slen = s.length();
        int tlen = t.length();
        if (slen >= 0 && tlen == 0) return 1;
        if (tlen > slen) return 0;
        Map<Character, Integer> original = new HashMap<>();
        for (char c : t.toCharArray()) {
            original.put(c, original.containsKey(c) ? original.get(c) + 1 : 1);
        }
        int total = 0;
        int ret = 0;
        char[] cs = s.toCharArray();
        int i = 0;
        int j = 0;
        Map<Character, Integer> map = new HashMap<>(original);
        while (j < slen) {
            //if (i > slen - tlen) break;
            if (!map.containsKey(cs[j])) {
                i = ++j;
                map = new HashMap<>(original);
                total = 0;
                continue;
            }
            int num = map.get(cs[j]);
            if (num > 0) {
                map.put(cs[j], map.get(cs[j]) - 1);
                total++;
                if (total == tlen) {
                    ret++;
                    map.put(cs[i], map.get(cs[i]) + 1);
                    total--;
                    i++;
                }
                j++;
            } else {
                map.put(cs[i], map.get(cs[i]) + 1);
                total--;
                i++;
            }
        }
        return ret;
    }
}
