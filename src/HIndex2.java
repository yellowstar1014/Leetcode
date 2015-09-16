package src;

/**
 * Created by yellowstar on 9/11/15.
 */
public class HIndex2 {
    public static void main(String[] args) {
        HIndex2 hIndex2 = new HIndex2();
        int ret = hIndex2.hIndex(new int[]{11, 15});
    }
    // c[i] >= len - i
    // c[i]...c[len - 1] >= len - i (size = len - i)
    // c[0]...c[i - 1] <= len - i
    // binary search
    // corner case
    // len == 0 -> 0
    public int hIndex(int[] citations) {
        int len = citations.length;
        if (len == 0) return 0;
        int left = 0;
        int right = len - 1;
        while(left < right) {
            int mid = (left + right) >> 1;
            if (citations[mid] < len - mid) left = mid + 1;
            else right = mid;
        }
        return citations[left] >= len - left ? len - left : 0;
    }
}
