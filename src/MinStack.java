package src;

import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Created by yellowstar on 10/9/15.
 */

public class MinStack<E extends Comparable> {
    private Stack<Comparable> elements;
    private Stack<Comparable> mins;
    public MinStack() {
        elements = new Stack<>();
        mins = new Stack<>();
    }

    public void push(E e) {
        if (e instanceof Comparable) throw new IllegalArgumentException();
        if (mins.empty() || mins.peek().compareTo(e) >= 0) {
            mins.push((Comparable)e);
        }
        elements.push((Comparable)e);
    }

    public E pop() {
        if (elements.empty()) throw new NoSuchElementException();
        Comparable e = elements.pop();
        if (e.compareTo(mins.peek()) == 0) {
            mins.pop();
        }
        return (E)e;
    }

    public E min() {
        if (mins.empty()) throw new NoSuchElementException();
        return (E)mins.peek();
    }
}

