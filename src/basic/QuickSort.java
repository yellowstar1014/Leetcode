package src.basic;

import java.util.Random;

/**
 * Created by yellowstar on 8/8/15.
 */
public class QuickSort implements Sorter{
    public void sort(int[] array, int start, int end) {
        if (start >= end) return;
        int index = partition4(array, start, end);
        sort(array, start, index - 1);
        sort(array, index + 1, end);
    }

    public void sort(int[] array) {
        shuffle(array);
        sort3Way(array, 0, array.length - 1);
    }

    public void sort3Way(int[] array, int start, int end) {
        if (start >= end) return;
        int lt = start;
        int gt = end;
        int i = lt;
        while (i <= gt) {
            if (array[i] < array[lt]) swap(array, i++, lt++);
            else if (array[i] > array[lt]) swap(array, i, gt--);
            else i++;
        }
        sort3Way(array, start, lt - 1);
        sort3Way(array, gt + 1, end);
    }

    private int partition(int[] array, int start, int end) {
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

    private int partition4(int[] array, int start, int end) {
        int pivot = array[start];
        int i = start + 1;
        int j = i;
        while (j <= end) {
            if (array[j] < pivot) swap(array, i++, j++);
            else j++;
        }
        swap(array, start, i - 1);
        return i - 1;
    }

    // without swap pivot and r
    private int partition3(int[] array, int start, int end) {
        int pivot = array[start];
        int l = start - 1;
        int r = end + 1;
        while (true) {
            while (array[++l] < pivot)
                if (l == end) break;
            while (array[--r] > pivot)
                if (r == start) break;
            if (l >= r) break;
            swap(array, l, r);
        }
        //swap(array, start, r);
        return r;
    }

    private int partition2(int[] array, int start, int end) {
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

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    //Fisher-Yates shuffle
    private void shuffle(int[] array) {
        int n = array.length;
        for (int i = 0; i < array.length; i++) {
            // Get a random index of the array past i.
            int random = i + (int) (Math.random() * (n - i));
            // Swap the random element with the present element.
            int randomElement = array[random];
            array[random] = array[i];
            array[i] = randomElement;
        }
    }
}
