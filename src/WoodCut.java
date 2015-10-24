package src;

/**
 * Created by yellowstar on 10/22/15.
 */
public class WoodCut {
    public static void main(String[] args) {
        int count = woodCut(new int[]{232,124,456}, 7);
    }
    public static int woodCut(int[] L, int k) {
        int max = L[0];
        for (int i = 0; i < L.length; i++) {
            max = Math.max(max, L[i]);
        }
        int l = 0;
        int r = max;
        while (l < r) {
            int mid = l + ((r - l + 1) >> 1);
            int n = cal(L, mid);
            if (n < k) {
                r = mid - 1;
            } else {
                l = mid;
            }
        }
        return l;
    }

    private static int cal(int[] L, int len) {
        int count = 0;
        for (int i = 0; i < L.length; i++) {
            count += L[i] / len;
        }
        return count;
    }
}
