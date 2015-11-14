package src;

import java.util.*;

/**
 * Created by yellowstar on 11/4/15.
 */
public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        int[] nums = {6, 3, 5, 10, 11,2,9,14,13,7,4,8,12};
        List<List<Integer>> rets = calculate(nums);
        for (List<Integer> ret : rets) {
            for (int i : ret) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
    public static List<List<Integer>> calculate(int[] nums) {
        List<List<Integer>> rets = new LinkedList<>();
        int len = nums.length;
        if (len == 0) return rets;
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> lastPile = new LinkedList<>();
        int[] pile = new int[len];
        int l = 0;
        for (int num : nums) {
            int i = Arrays.binarySearch(pile, 0, l, num);
            if (i < 0) i = -(i + 1);
            if (i > 0) {
                map.put(num, pile[i - 1]);
            }
            pile[i] = num;
            if (i == l - 1) {
                lastPile.add(num);
            } else if (i == l) {
                lastPile.clear();
                lastPile.add(num);
                l++;
            }
        }

        for (int start : lastPile) {
            List<Integer> path = new LinkedList<>();
            int cur = start;
            while (map.containsKey(cur)) {
                path.add(cur);
                cur = map.get(cur);
            }
            path.add(cur);
            Collections.reverse(path);
            rets.add(path);
        }
        return rets;
    }
}
