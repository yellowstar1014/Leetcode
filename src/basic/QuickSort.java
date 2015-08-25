package src.basic;

/**
 * Created by yellowstar on 8/8/15.
 */
public class QuickSort implements Sorter{
    public void sort(int[] array, int start, int end) {
        if (start >= end) return;
        int index = partition(array, start, end);
        sort(array, start, index - 1);
        sort(array, index + 1, end);
    }

    public void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    private int partition(int[] array, int start, int end) {
        int pivot = array[start];
        int l = start + 1;
        int r = end;
        while (l <= r) {
            while (l <= r && array[l] < pivot) l++;
            while (l <= r && array[r] > pivot) r--;
            if (l <= r) {
                swap(array, l, r);
                l++;
                r--;
            }
        }
        swap(array, start, r);
        return r;
    }

    private int partition2(int[] array, int start, int end) {
        int pivot = array[start];
        int l = start;
        int r = end + 1;
        while (true) {
            while (array[++l] < pivot)
                if (l == end) break;
            while (array[--r] > pivot)
                if (r == start) break;
            if (l >= r) break;
            swap(array, l, r);
        }
        swap(array, start, r);
        return r;
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
