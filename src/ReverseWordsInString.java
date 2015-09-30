package src;

/**
 * Created by yellowstar on 9/24/15.
 */
public class ReverseWordsInString {
    public static void main(String[] args) {
        ReverseWordsInString reverseWordsInString = new ReverseWordsInString();
        String ret = reverseWordsInString.reverse(" 1");
    }
    public String reverse(String s) {
        if (s == null || s.isEmpty()) return s;
        String[] arr = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] != "") {
                sb.append(arr[i]);
                sb.append(" ");
            }
        }
        String ret = sb.toString();
        return ret.length() > 0 ? ret.substring(0, ret.length() - 1) : ret;
    }
}
