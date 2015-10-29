package src;

/**
 * Created by yellowstar on 10/27/15.
 */
public class FlipGame {
    public static void main(String[] args) {
        canWin("++++");
    }
    public static boolean canWin(String s) {
        int len = s.length();
        if (len <= 1) return false;
        char[] cs = s.toCharArray();
        return canWin(cs);
    }

    private static boolean canWin(char[] cs) {
        for (int i = 1; i < cs.length; i++) {
            if (cs[i] == '+' && cs[i - 1] == '+') {
                cs[i] = '-';
                cs[i - 1] = '-';
                boolean ret = !canWin(cs);
                cs[i] = '+';
                cs[i - 1] = '+';
                if (ret) return true;
            }
        }
        return false;
    }
}
