package src;

/**
 * Created by yellowstar on 10/26/15.
 */
public class WiggleSort {
    public static void main(String[] args) {
        int[] nums = {2, 1};
        wiggleSort(nums);
    }
    public static void wiggleSort(int[] nums) {
        int len = nums.length;
        if (len == 0) return;
        for (int i = 0; i < len - 1; i += 2) {
            if (nums[i] > nums[i + 1]) swap(nums, i, i + 1);
        }
        for (int i = 1; i < len - 1; i += 2) {
            if (nums[i] < nums[i + 1]) swap(nums, i, i + 1);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
