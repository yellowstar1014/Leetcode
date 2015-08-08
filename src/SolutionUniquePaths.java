package src;

/*
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
The robot can only move either down or right at any point in time. The robot is
trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
How many possible unique paths are there?
*/
public class SolutionUniquePaths  {
    public static void main(String[] args) {
      System.out.println(uniquePaths(5,5));
    }
    public static int uniquePaths(int m, int n) {
        if(n <= 1 || m <= 1) {
            return 1;
        }
        int a = m + n - 2;
        int b = n - 1;
        int c = 1;
        int d = 1;
        while (b > 0) {
            //System.out.println(d);
            c *= a;
            d *= b;
            a--;
            b--;
        }

        return c/d;
    }

    public static int mul(int i, int c) {
        int result = 1;
        while (c > 0 && i > 0) {
            result *= i;
            i--;
            c--;
        }
        return result;
    }
}
