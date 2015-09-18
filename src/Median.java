package src;

/**
 * Created by yellowstar on 9/16/15.
 */
public class Median {
    public static void main(String[] args) {
        Median median = new Median();
        int[] nums = new int[]{7, 9, 4, 5};
        median.median(nums);
    }

    public int median(int[] nums) {
        // write your code here
        return findKth(nums, 0, nums.length - 1, (nums.length - 1) / 2);
    }

    private int findKth(int[] nums, int start, int end, int k) {
        int l = start;
        int r = end;
        while (l < r) {
            int index = partition(nums, l, r);
            if (k < index) r = index - 1;
            else if (k > index) l = index + 1;
            else return nums[index];
        }
        return nums[k];
    }

    private int partition(int[] nums, int start, int end) {
        int pivot = nums[start];
        int l = start;
        int r = end + 1;
        while(true) {
            while(nums[++l] < pivot) if (l == end) break;
            while(nums[--r] > pivot) if (r == start) break;
            if (l >= r) break;
            swap(nums, l, r);
        }
        swap(nums, start, r);
        return r;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
