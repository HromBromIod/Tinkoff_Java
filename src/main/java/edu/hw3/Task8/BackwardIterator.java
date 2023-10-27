package edu.hw3.Task8;

import org.jetbrains.annotations.NotNull;
import java.util.Collection;
import java.util.Iterator;

public class BackwardIterator<T> implements Iterator<T> {

    private Integer index;
    private final Collection<T> collection;

    public BackwardIterator(Collection<T> coll) {
        this.index = coll.size();
        this.collection = coll;
    }

    @Override
    public boolean hasNext() {
        return index > 0;
    }

    @Override
    public T next() {
        if (!hasNext())
            return null;
        index -= 1;
        T[] elements = null;
        collection.toArray(elements);
        return elements[index];
    }
}
