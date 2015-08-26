package src;

/**
 * Created by yellowstar on 8/26/15.
 */
public class ProductOfArrayExceptSelf {
    public static void main(String[] args) {
        ProductOfArrayExceptSelf productOfArrayExceptSelf = new ProductOfArrayExceptSelf();
        productOfArrayExceptSelf.productExceptSelf(new int[] {1, 0});
    }
    public int[] productExceptSelf(int[] nums) {
        // two pass
        // one from left to right, the other from right to left
        // a[i] = nums[0] * nums[1] * ... * nums[i - 1]
        // a[0] = 1
        // ret[i] = a[i] * nums[i + 1] * nums[i + 1] * ... * nums[len - 1]
        // ret and a could use one array.
        int len = nums.length;
        int[] ret = new int[len];
        ret[0] = 1;
        for (int i = 1; i < len; i++) {
            ret[i] = ret[i - 1] * nums[i - 1];
        }

        int partial = 1;
        for (int i = len - 1; i >= 0; i--) {
            ret[i] *= partial;
            partial *= nums[i];
        }

        return ret;
    }
}
