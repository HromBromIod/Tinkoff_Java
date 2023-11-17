package edu.hw3.Task8;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BackwardIterator<T> implements Iterator<T> {

    private Integer index;
    private final List<T> collection;

    public BackwardIterator(Collection<T> collect) {
        this.index = collect.size() - 1;
        this.collection = collect.stream().toList();
    }

    @Override
    public boolean hasNext() {
        return index > 0;
    }

    @Override
    public T next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        index -= 1;
        return collection.get(index + 1);
    }
}
