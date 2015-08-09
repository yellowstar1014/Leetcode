package src.basic;

/**
 * Created by yellowstar on 8/8/15.
 */
public class QuickSort implements Sorter{
    public void sort(int[] array, int start, int end) {
        int index = partition(array, start, end);
        swap(array, start, index);
        if (index > start + 1)
            sort(array, start, index - 1);
        if (index + 1 < end)
            sort(array, index + 1, end);
    }

    public void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    private int partition(int[] array, int start, int end) {
        int pivot = array[start];
        int l = start + 1;
        int r = end;
        while (l < r) {
            while (array[l] < pivot) l++;
            while (array[r] >= pivot) r--;
            if (l < r) {
                swap(array, l, r);
                l++;
                r--;
            }
        }
        return r;
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
