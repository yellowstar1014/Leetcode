package src;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by yellowstar on 10/2/15.
 */
public class FrogJump {
    // dp[i] = dp[i - 1] A[i] > dp[i - 1] + d || A[i] <= dp[i - 1]
    //        = A[i] else
    public static void main(String[] args) {
        FrogJump frogJump = new FrogJump();
        int[] A = {3, 6, 0, 9, 4, 7, 10};
        int ret = frogJump.jump2(13, 3, A);
        System.out.println(ret);
    }
    public int jump(int x, int d, int[] A) {
        int cur = 0;
        int end = Integer.MAX_VALUE;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < A.length; i++) {
            if (A[i] <= cur + d && A[i] > cur) {
                cur = A[i];
                if (cur >= x) return i;
                while (!queue.isEmpty()) {
                    if (queue.peek() > cur + d) break;
                    cur = queue.poll();
                    if (cur >= x) return i;
                }
            } else if (A[i] > cur + d && A[i] < x + d) {
                queue.add(A[i]);
            }
        }
        return -1;
    }

    public int jump2(int x, int d, int[] A) {
        int count = 0;
        int num = (x + 1) / (d + 1);
        int[] min = new int[num + 1];
        int[] max = new int[num + 1];
        Arrays.fill(min, -1);
        Arrays.fill(max, - 1);
        max[num] = x;
        min[num] = x;
        boolean[] isRelated = new boolean[num];
        for (int i = 0; i < A.length; i++) {
            int j = A[i] / (d + 1);
            if (j > num) continue;
            if (min[j] == -1 || A[i] < min[j]) {
                if (j != 0 && !isRelated[j - 1] && max[j - 1] != -1 && max[j - 1] + d >= A[i]) {
                    isRelated[j - 1] = true;
                    count++;
                    if (count == num) return i;
                }
                min[j] = A[i];
            }
            if (max[j] == -1 || A[i] > max[j]) {
                if (j != num && !isRelated[j] && min[j + 1] != -1 && A[i] + d >= min[j + 1]) {
                    isRelated[j] = true;
                    count++;
                    if (count == num) return i;
                }
                max[j] = A[i];
            }
        }
        return -1;
    }
}
