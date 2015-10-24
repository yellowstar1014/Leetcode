package src.sort;

/**
 * Created by yellowstar on 10/16/15.
 */
public class Median {
    public static void main(String[] args) {
        Median median = new Median();
        median.median(new int[]{7,9,4,5});
    }

    public int median(int[] nums) {
        return findKth(nums, 0, nums.length - 1, (nums.length - 1) / 2);
    }

    private int findKth(int[] nums, int l, int r, int k) {
        int index = partition(nums, l, r);
        if (k > index) {
            return findKth(nums, index + 1, r, k);
        } else if (k < index) {
            return findKth(nums, l, index - 1, k);
        } else {
            return nums[k];
        }
    }

    private int partition(int[] nums, int l, int r) {
        if (l == r) return l;
        int key = nums[l];
        int start = l;
        l++;
        while (l <= r) {
            if (nums[l] < key) l++;
            else {
                swap(nums, l, r--);
            }
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
