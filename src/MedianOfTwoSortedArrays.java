package src;

/**
 * Created by yellowstar on 8/24/15.
 */
public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        MedianOfTwoSortedArrays medianOfTwoSortedArrays = new MedianOfTwoSortedArrays();
        int[] a = {10000};
        int[] b = {10001};
        double result = medianOfTwoSortedArrays.findMedianSortedArrays2(a, b);
    }
    public double findMedianSortedArrays(int A[], int B[]) {
        int len = A.length + B.length;
        if ((len & 1) == 1) return findK((len - 1) >> 1, A, B, 0, 0);
        else return (findK((len - 1) >> 1, A, B, 0, 0) + findK(len >> 1, A, B, 0, 0)) / 2;
    }

    public double findK(int k, int A[], int B[], int as, int bs) {
        if (A.length - as > B.length - bs) return findK(k, B, A, bs, as);
        if (as >= A.length) return B[k];
        if (k == 0) return Math.min(A[as], B[bs]);
        int posA = Math.min(A.length - 1, as + ((k - 1) >> 1));
        int posB = bs + (k - 1) - (posA - as);
        if (A[posA] < B[posB]) return findK(k - (posA - as + 1), A, B, posA + 1, bs);
        else if (A[posA] > B[posB]) return findK(k - (posB - bs + 1) , A, B, as, posB + 1);
        else {
            return A[posA];
        }
    }

    public double findMedianSortedArrays2(int A[], int B[]) {
        int m = A.length;
        int n = B.length;
        if (m > n) return findMedianSortedArrays(B, A);
        int left = 0;
        int right = m;
        while (left <= right) {
            int i = (left + right) / 2;
            int j = (m + n + 1) / 2 - i;
            if (i > 0 && j < n && A[i - 1] > B[j]) {
                right = i - 1;
            }
            else if (j > 0 && i < m && B[j - 1] > A[i]) {
                left = i + 1;
            }
            else {
                int num1 = 0;
                int num2 = 0;
                if (i == 0) num1 = B[j - 1];
                else if (j == 0) num1 = A[i - 1];
                else num1 = Math.max(A[i - 1], B[j - 1]);
                if (((m + n) & 1) == 1) return num1;

                if (i>= m) num2 = B[j];
                else if (j >= n) num2 = A[i];
                else num2 = Math.min(A[i], B[j]);

                return (num1 + num2) / 2.0;
            }
        }
        return (n >> 1) == 0 ? B[(n - 1) >> 1] : (B[(n - 1) >> 1] + B[n >> 1]) / 2.0;
    }
}
