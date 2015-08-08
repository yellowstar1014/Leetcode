import java.util.HashMap;

/**
 * Created by yellowstar on 7/26/15.
 */
public class MinimumWindowSubstring {
    public static void main(String[] args) {
        MinimumWindowSubstring mws = new MinimumWindowSubstring();
        mws.minWindow("bdab","ab");
    }
    public String minWindow(String s, String t) {
        // two pointer, hashtable
        // count : finshed characters
        HashMap<Character, Integer> tmap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            if (tmap.containsKey(t.charAt(i))) {
                tmap.put(t.charAt(i), tmap.get(t.charAt(i)) + 1);
            }
            else {
                tmap.put(t.charAt(i), 1);
            }
        }
        int count = 0;
        int total = t.length();
        int minLen = Integer.MAX_VALUE;
        String ret = "";
        int left = 0;
        int right = 0;
        char[] cs = s.toCharArray();
        while (left < cs.length - total + 1 && !tmap.containsKey(cs[left])) left++;
        right = left;
        while (left < cs.length - total + 1 && right < cs.length) {
            if (tmap.containsKey(cs[right])) {
                if (tmap.get(cs[right]) > 0) {
                    tmap.put(cs[right], tmap.get(cs[right]) - 1);
                    count++;
                    if (count == total) {
                        if (minLen > right - left + 1) {
                            minLen = right - left + 1;
                            ret = s.substring(left, right + 1);
                        }
                        tmap.put(cs[left], tmap.get(cs[left]) - 1);
                        count--;
                        while (++left < cs.length - total + 1 && !tmap.containsKey(cs[left]));
                    }
                }
            }
            right++;
        }
        return ret;
    }
}
