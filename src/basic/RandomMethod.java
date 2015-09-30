package src.basic;

/**
 * Created by yellowstar on 9/29/15.
 */
public class RandomMethod {
    public void shuffle(int[] array) {
        int len = array.length;
        for (int i = 0; i < len - 1; i++) {
            int j = i + (int)(Math.random() * (len - i));
            swap(array, i, j);
        }
    }

    public void shuffle2(int[] array) {
        int len = array.length;
        for (int i = len - 1; i > 0; i--) {
            int j = (int)(Math.random() * (i + 1));
            swap(array, i, j);
        }
    }

    // To initialize an array a of n elements to a randomly shuffled copy of source, both 0-based:
    public void insideOutShuffle(int[] array, int[] source) {
        int len = array.length;
        for (int i = 0; i < len; i++) {
            int j = (int)(Math.random() * (i + 1));
            array[i] = array[j];
            array[j] = source[i];
        }
    }

    public void reservoirSample(int[] sample, int[] reservoir) {
        int slen = sample.length;
        int rlen = reservoir.length;
        for (int i = 0; i < rlen; i++) {
            reservoir[i] = sample[i];
        }
        for (int i = rlen; i < slen; i++) {
            int j = (int)(Math.random() * (i + 1));
            if (j < rlen) {
                reservoir[j] = sample[i];
            }
        }
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
