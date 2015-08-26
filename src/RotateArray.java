package src;

/**
 * Created by yellowstar on 8/26/15.
 */
public class RotateArray {
    public static void main(String[] args) {
        RotateArray rotateArray = new RotateArray();
        rotateArray.rotate(new int[]{1,2,3}, 2);
    }

    // time complexity O(n)
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int l, int r) {
        while (l < r) {
            swap(nums, l, r);
            l++;
            r--;
        }
    }
    // 1. use extra array[n]
    // 2. use extra array[k]
    // 3. k pass, k step one time -> wrong
    //    -> should be GCD(k ,len) pass
    //    -> len / GCD -> number of element in every pass
    //    -> len / GCD - 1 -> swap step in every pass
    // corner case
    // k > nums.length - 1
    // -> k = k % nums.length
    // time complexity O(n)
    public void rotate2(int[] nums, int k) {
        int len = nums.length;
        k = k % len;
        int gcd = getGCD(k, len);
        int count = len / gcd - 1;
        for (int i = 0; i < gcd; i++) {
            int pos = i;
            for (int j = 0; j < count; j++) {
                pos = (pos + k) % len;
                swap(nums, i, pos);
            }
        }
    }

    private int getGCD(int a, int b) {
        if (a == 0 || b == 0) return a + b;
        return getGCD(b, a % b);
    }

    private void swap(int[] nums, int i, int j) {
        nums[i] ^= nums[j];
        nums[j] ^= nums[i];
        nums[i] ^= nums[j];
    }

}
