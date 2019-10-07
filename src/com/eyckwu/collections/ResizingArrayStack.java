package com.eyckwu.collections;

import java.util.Arrays;
import java.util.EmptyStackException;

public class ResizingArrayStack<E> {
    private E[] elementData = (E[]) new Object[1];
    private int elementCount = 0;
    public boolean isEmpty(){
        return elementCount == 0;
    }

    public int size(){
        return elementCount;
    }

    public void setSize(int newSize){
        if (newSize > elementCount){
            ensureCapacityHelper(newSize);
        } else {
            for (int i = newSize; i < elementCount; i++){
                elementData[i] = null;
            }
        }
        elementCount = newSize;
    }

    private void ensureCapacityHelper(int minCapacity){
        if (minCapacity - elementData.length > 0){
            grow(minCapacity);
        }
    }

    private void grow(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity;
        if (newCapacity - minCapacity < 0){
            newCapacity = minCapacity;
        }
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    public void push(E e){
        if (elementCount == elementData.length){
            setSize(2 * elementData.length);
        }
        elementData[elementCount++] = e;
    }

    public synchronized E peek(){
        int len = size();
        if (len <= 0){
            throw new EmptyStackException();
        }
        return elementAt(len - 1);
    }

    public synchronized E pop(){
        int len = size();
        E obj = peek();
        removeElementAt(len-1);
        if (elementCount > 0 && elementCount == elementData.length / 4){
            setSize(elementData.length / 2);
        }
        return obj;
    }

    private synchronized void removeElementAt(int index) {
        if (index >= elementCount) {
            throw new ArrayIndexOutOfBoundsException(index + " >= " +
                    elementCount);
        } else if (index < 0) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        int j = elementCount - index - 1;
        if (j > 0){
            System.arraycopy(elementData, index + 1, elementData, index, j);
        }
    }

    private E elementAt(int index) {
        if (index >= elementCount) {
            throw new ArrayIndexOutOfBoundsException(index + " >= " + elementCount);
        }

        return elementData(index);
    }

    private E elementData(int index) {
        return elementData[index];
    }
}
