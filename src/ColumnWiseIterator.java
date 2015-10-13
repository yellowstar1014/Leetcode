package src;

import java.util.*;

/**
 * Created by yellowstar on 10/10/15.
 */
public class ColumnWiseIterator<E> {
    //private List<List<E>> lists;
    private List<Iterator<E>> iterators;
    private Queue<E> queue;
    public ColumnWiseIterator(List<List<E>> lists) {
        //this.lists = lists;
        queue = new LinkedList<>();
        iterators = new ArrayList<>();
        for (List list : lists) {
            iterators.add(list.iterator());
        }
    }

    public static void main(String[] args) {

    }

    public boolean hasNext() {
        if (!queue.isEmpty()) {
            return true;
        }
        for (Iterator<E> iterator : iterators) {
            if (iterator.hasNext()) {
                queue.add(iterator.next());
            }
        }
        return queue.isEmpty();
    }

    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return queue.poll();
    }
}
