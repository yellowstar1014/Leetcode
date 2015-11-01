package src;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by yellowstar on 10/30/15.
 */
public class EncodeAndDecodeString {
    public static void main(String[] args) {
        List<String> strs = new LinkedList<>();
        strs.add("");
        strs.add("");
        String ret = encode(strs);
        strs = decode(ret);
    }
    public static String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str.length());
            sb.append("/");
            sb.append(str);
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public static List<String> decode(String s) {
        List<String> ret = new LinkedList<>();
        int len = s.length();
        if (len == 0) return ret;
        int i = 0;
        while (i < len) {
            int j = s.indexOf("/", i);
            int l = Integer.valueOf(s.substring(i, j));
            ret.add(s.substring(j + 1, j + 1 + l));
            i = j + 1 + l;
        }
        return ret;
    }
}
