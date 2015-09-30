package src;

/**
 * Created by yellowstar on 9/24/15.
 */
public class MaxSubArray {

    public static void main(String[] args) {
        int[] nums = {-1, -1, -3, 4, -1, 1};
        MaxSubArray maxSubArray = new MaxSubArray();
        int ret =  maxSubArray.maxSum(nums);
        System.out.println(ret);
    }

    // subarray.size() >= 2
    // sum[i] -> max sum from nums[0] to nums[i]
    // sum[i] = sum[i - 1] + nums[i]
    // left -> the left index of previous subarray with max sum
    // i - left == 2
    //      -> if (nums[left] < 0) sum[i] = sum[i - 1] - nums[left] + nums[i]
    //      -> else sum[i] = sum[i - 1] + nums[i]
    // optimization space O(n) -> O(1)
    // previous sum
    // i - left == 2
    //      -> if (nums[left] < 0) sum = sum - nums[left] + nums[i]
    //      -> else sum = sum + nums[i]
    public int maxSum(int[] nums) {
        int len = nums.length;
        if (len < 2) throw new IllegalArgumentException();
        // -1 -1 -3 -1
        int sum = nums[0] + nums[1]; // sum=-2
        int max = sum; // max=-2
        int left = 0;
        for (int i = 2; i < len; i++) { // i=2
            if (i - left <= 2 && nums[left] < 0) { // i -left=2 nums[left]=-1
                sum = sum - nums[left] + nums[i]; // sum=-2-(-1)+-3=-4
                left++; //left=1
            } else {
                sum = sum + nums[i];
            }
            max = Math.max(max, sum);
        }
        return max;
    }
}
