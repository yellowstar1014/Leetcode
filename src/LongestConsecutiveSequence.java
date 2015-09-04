package src;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yellowstar on 8/28/15.
 */
public class LongestConsecutiveSequence {
    // O(n) -> normal sort algo won't work here
    // HashMap -> query time is O(1)
    // HashSet is enough
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for (int num : nums) {
            set.add(num);
        }
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int l = 0;
            int cur = nums[i];
            while (set.contains(--cur)) {
                l++;
                set.remove(cur);
            }
            int r = 0;
            cur = nums[i];
            while (set.contains(++cur)) {
                r++;
                set.remove(cur);
            }
            max = Math.max(max, l + r + 1);
        }
        return max;
    }
}
