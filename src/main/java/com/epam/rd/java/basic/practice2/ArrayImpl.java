package com.epam.rd.java.basic.practice2;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayImpl implements Array {
    private static final int DEFAULT_CAPACITY = 10;
    Object[] elementData = new Object[DEFAULT_CAPACITY];
    private int size = 0;

	@Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;
    }

	@Override
    public int size() {
        return size;
    }
	
	@Override
    public Iterator<Object> iterator() {
	    return new IteratorImpl();
    }
	
	private class IteratorImpl implements Iterator<Object> {
        int index;

        @Override
        public boolean hasNext() {
            if (index < size) {
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            return elementData[index];
        }

    }
	
	@Override
    public void add(Object element) {
        if (size >= elementData.length) {
            arrayIncrease();
        }
        elementData[size++] = element;
    }

	@Override
    public void set(int index, Object element) {
	    chekIndex(index);
	    elementData[index] = element;
    }

	@Override
    public Object get(int index) {
        chekIndex(index);
        return elementData[index];
    }

	@Override
    public int indexOf(Object element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null)
                    return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elementData[i]))
                    return i;
            }
        }
        return -1;
    }

	@Override
    public void remove(int index) {
        chekIndex(index);

        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }
        elementData[--size] = null;
    }

    @Override
    public String toString() {
        return null;
    }

    public void arrayIncrease() {
	    int oldCapacity = elementData.length;
	    int newCapacity = oldCapacity + (oldCapacity >> 1);
	    elementData = Arrays.copyOf(elementData, newCapacity);
    }

    public void chekIndex(int index) {
	    if (index > size || index < 0) {
	        throw new IndexOutOfBoundsException();
        }
    }

}
