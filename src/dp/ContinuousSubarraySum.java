package src.dp;

import java.util.ArrayList;

/**
 * Created by yellowstar on 10/15/15.
 */
public class ContinuousSubarraySum {
    public static void main(String[] args) {
        ContinuousSubarraySum continuousSubarraySum = new ContinuousSubarraySum();
        continuousSubarraySum.continuousSubarraySumII(new int[]{3,1,-100,-3,4});
    }

    public ArrayList<Integer> continuousSubarraySumII(int[] A) {
        int len = A.length;
        int max = A[0];
        int min = A[0];
        int gMax = A[0];
        int gMin = A[0];
        ArrayList<Integer> ret1 = new ArrayList<>();
        ArrayList<Integer> ret2 = new ArrayList<>();
        ret1.add(0);
        ret1.add(0);
        ret2.add(0);
        ret2.add(0);
        int[] cd1 = {0, 0};
        int[] cd2 = {0, 0};
        int sum = A[0];
        for (int i = 1; i < len; i++) {
            sum += A[i];
            if (max > 0) {
                max = max + A[i];
                cd1[1] = i;
            } else {
                max = A[i];
                cd1[0] = i;
                cd1[1] = i;
            }
            if (max > gMax) {
                gMax = max;
                ret1.set(0, cd1[0]);
                ret1.set(1, cd1[1]);
            }
            if (min < 0) {
                min = min + A[i];
                cd2[1] = i;
            } else {
                min = A[i];
                cd2[0] = i;
                cd2[1] = i;
            }
            if (min < gMin) {
                gMin = min;
                ret2.set(0, cd2[0]);
                ret2.set(1, cd2[1]);
            }
        }
        if (ret2.get(0) == 0 || ret2.get(1) == len - 1) return ret1;
        if (gMax >= sum - gMin) {
            return ret1;
        } else {
            int start = ret2.get(1) + 1;
            int end = ret2.get(0) - 1;
            ret2.set(0, start);
            ret2.set(1, end);
            return ret2;
        }
    }
}
