package src.Design;

import java.util.*;

/**
 * Created by yellowstar on 9/24/15.
 */
public class DeepIterator<T> implements Iterator<T>{
    private T nextItem;
    private Stack<Iterator<?>> stack = new Stack<>();

    public DeepIterator(Collection<?> collection) {
        if (collection == null) throw new NullPointerException("can not iterator null");
        stack.push(collection.iterator());
    }

    public static void main(String[] args) {
        List list = new ArrayList<>();
        List subList = new ArrayList<>();
        List subsubList = new ArrayList<>();
        subsubList.add(4);
        subsubList.add(5);
        subList.add(3);
        subList.add(subsubList);
        subList.add(6);
        list.add(1);
        list.add(2);
        list.add(subList);
        list.add(8);
        DeepIterator<Integer> deepIterator = new DeepIterator<>(list);
        while (deepIterator.hasNext()) {
            System.out.print(deepIterator.next() + " ");
        }
    }

    @Override
    public boolean hasNext() {
        if (nextItem != null) return true;
        while (!stack.isEmpty()) {
            Iterator<?> iterator = stack.peek();
            if (iterator.hasNext()) {
                Object item = iterator.next();
                if (item instanceof Collection<?>) {
                    stack.push(((Collection<?>)item).iterator());
                } else {
                    nextItem = (T)item;
                    return true;
                }
            } else {
                stack.pop();
            }
        }
        return false;
    }

    @Override
    public T next() {
        if (hasNext()) {
            T toReturn = nextItem;
            nextItem = null;
            return toReturn;
        }
        throw new NullPointerException("Already reach the end of collection");
    }

    @Override
    public void remove() {

    }
}
