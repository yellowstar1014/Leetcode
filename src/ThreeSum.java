package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yellowstar on 9/5/15.
 */
public class ThreeSum {
    // fix one element a of three -> two sum target = -a;
    // non-descending order -> sort
    // corner case
    // len < 3 -> empty
    // duplicates -> skip these duplicates -> unique
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> rets = new ArrayList<>();
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len - 2; i++) {
            int left = i + 1;
            int right = len - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0) right--;
                else if (sum < 0) left++;
                else {
                    List<Integer> ret = new ArrayList<>();
                    ret.add(nums[i]);
                    ret.add(nums[left]);
                    ret.add(nums[right]);
                    rets.add(ret);
                    // skip duplicates from left and right (pay attention to array bound)
                    while (++left < right && nums[left] == nums[left - 1]);
                    while (left < --right && nums[right] == nums[right + 1]);
                }

            }
            // skip duplicates
            while(i + 1 < len - 2 && nums[i] == nums[i + 1]) i++;
        }
        return rets;
    }
}
