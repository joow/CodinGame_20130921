package org.joow.cycliclist;

import java.util.ArrayList;

public class CyclicList<T> extends ArrayList<T> {
    public CyclicList() {}

    public CyclicList(int size) {
        super(size);
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index < size()) {
            return super.get(index);
        }

        final int cyclicIndex = (size() + index) % size();
        return super.get(cyclicIndex);
    }
}