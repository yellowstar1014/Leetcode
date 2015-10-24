package src;

/**
 * Created by yellowstar on 10/19/15.
 */
public class DividTwoInteger {
    public static void main(String[] args) {
        DividTwoInteger dividTwoInteger = new DividTwoInteger();
        dividTwoInteger.divide(-2147483648, -1);
    }
    public int divide(int dividend, int divisor) {
        int sign = 1;
        if (dividend < 0 && divisor > 0 || dividend > 0 && divisor < 0) {
            sign = -1;
        }
        long l_dividend = Math.abs((long)dividend);
        long l_divisor = Math.abs((long)divisor);
        long ret = 0;
        while (l_dividend >= l_divisor) {
            long sum = l_divisor;
            long count = 1;
            while ((sum << 1) <= l_dividend) {
                sum = sum << 1;
                count = count << 1;
            }
            ret += count;
            l_dividend -= sum;
        }
        if (sign == -1) ret = -ret;
        if (ret > Integer.MAX_VALUE || ret < Integer.MIN_VALUE) return Integer.MAX_VALUE;
        return (int)ret;
    }
}
