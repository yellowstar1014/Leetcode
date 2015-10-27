package src;

/**
 * Created by yellowstar on 10/19/15.
 */
public class DividTwoInteger {
    public static void main(String[] args) {
        DividTwoInteger dividTwoInteger = new DividTwoInteger();
        dividTwoInteger.divide(-1, 1);
    }
    public int divide(int dividend, int divisor) {
        if (divisor == 0) return Integer.MAX_VALUE;
        int sign = dividend < -1 ^ divisor < -1 ? -1 : 1;
        long l_dividend = Math.abs((long)dividend);
        long l_divisor = Math.abs((long)divisor);
        long ret = 0;
        while (l_dividend >= l_divisor) {
            int count = 1;
            long sum = l_divisor;
            while ((sum << 1) <= l_dividend) {
                sum = sum << 1;
                count = count << 1;
            }
            ret += count;
            l_dividend -= sum;
        }
        ret = ret * sign;
        if (ret > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        else return (int)ret;
    }
}
