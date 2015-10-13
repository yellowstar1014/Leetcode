package src;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yellowstar on 10/9/15.
 */
public class ContainsDuplicates {
    public static void main(String[] args) {
        ContainsDuplicates containsDuplicate = new ContainsDuplicates();
        int[] nums = {1, 4, 8, 10, 2};
        boolean ret = containsDuplicate.containsNearbyAlmostDuplicate(nums, 3, 2);
        System.out.println(ret);
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int len = nums.length;
        if (len <= 1) return true;
        Map<Long, Integer> map = new HashMap<>();
        for(int i = 0; i < len; i++) {
            long j = ((long)nums[i] - Integer.MIN_VALUE) /(t + 1);
            if (map.containsKey(j)) {
                return true;
            } else {
                if (map.containsKey(j - 1) && nums[map.get(j - 1)] + t >= nums[i]) return true;
                if (map.containsKey(j + 1) && nums[i] + t >= nums[map.get(j + 1)]) return true;
                map.put(j, i);
                if (map.size() > k) {
                    long old = ((long)nums[i - k] - Integer.MIN_VALUE)/ (t + 1);
                    map.remove(old);
                }
            }
        }
        return false;
    }
}
