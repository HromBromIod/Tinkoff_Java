package edu.hw3.Task8;

import java.util.Collection;
import java.util.Iterator;

public class BackwardIterator<T> implements Iterator<T> {

    private Integer index;
    private final Collection<T> collection;

    public BackwardIterator(Collection<T> collect) {
        this.index = collect.size() - 1;
        this.collection = collect;
    }

    @Override
    public boolean hasNext() {
        return index > 0;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            return null;
        }
        index -= 1;
        T[] elements = (T[]) new Object[0];
        collection.toArray(elements);
        return elements[index];
    }
}
