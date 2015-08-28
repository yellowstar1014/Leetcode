package src;

/**
 * Created by yellowstar on 8/26/15.
 */
public class PlusOne {
    public static void main(String[] args) {
        PlusOne plusOne = new PlusOne();
        plusOne.plusOne(new int[] {0});
    }
    public int[] plusOne(int[] digits) {
        int carry = 1;
        int len = digits.length;
        for (int i = len - 1; i >= 0; i--) {
            int val = digits[i] + carry;
            digits[i] = val % 10;
            carry = val / 10;
            if (carry == 0) break;
        }
        int j = 0;
        if (carry == 1) {
            len++;
            j++;
        }
        int[] ret = new int[len];
        if (j != 0) ret[0] = carry;
        for (int i = 0; i < len; i++) {
            ret[j++] = digits[i];
        }
        return ret;
    }


}
