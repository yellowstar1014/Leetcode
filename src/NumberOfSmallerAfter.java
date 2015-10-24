package src;

import java.util.Arrays;

/**
 * Created by yellowstar on 10/19/15.
 */
public class NumberOfSmallerAfter {
    public static void main(String[] args) {
        NumberOfSmallerAfter numberOfSmallerAfter = new NumberOfSmallerAfter();
        numberOfSmallerAfter.calculate(new int[] {5,6,1,4,3,2});
    }
    private int[] calculate(int[] A) {
        if (A.length == 0) return null;
        int l = 0;
        int r = A.length - 1;
        int[] AClone = Arrays.copyOf(A, A.length);
        int[] B = new int[A.length];
        int[] ret = new int[A.length];
        mergeSort(AClone, l, r, ret);
        for (int i = 0; i < B.length; i++) {
            B[i] = ret[A[i] - 1];
        }
        return B;
    }

    private void mergeSort(int[] A, int low, int high, int[] ret) {
        if (high == low) return;
        int mid = low + ((high - low) >> 1);
        mergeSort(A, low, mid, ret);
        mergeSort(A, mid + 1, high, ret);
        merge(A, low, mid, high, ret);
    }

    private void merge(int[] A, int low, int mid, int high, int[] ret) {
        int[] tmp = new int[high - low + 1];
        int l = low;
        int r = mid + 1;
        int i = 0;
        while (l <= mid && r <= high) {
            if (A[l] < A[r]) {
                tmp[i] = A[l];
                ret[A[l] - 1] += Math.max(0, i - (l - low));
                i++;
                l++;
            } else {
                tmp[i] = A[r];
                ret[A[r] - 1] += Math.max(0, i - (r - low));
                i++;
                r++;
            }
        }
        while (l <= mid) {
            tmp[i] = A[l];
            ret[A[l] - 1] += Math.max(0, i - (l - low));
            i++;
            l++;
        }
        while (r <= high) {
            tmp[i] = A[r];
            ret[A[r] - 1] += Math.max(0, i - (r - low));
            i++;
            r++;
        }
        for (int j = low; j <= high; j++) {
            A[j] = tmp[j - low];
        }
    }
}
