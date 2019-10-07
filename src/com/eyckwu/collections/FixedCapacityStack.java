package com.eyckwu.collections;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;

public class FixedCapacityStack<Item> {
    private Item[] elements;
    private int N;
    public FixedCapacityStack(int cap){
        elements = (Item[]) new Objects[cap];
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    public void push(Item item){
        if (N >= elements.length){
            throw new IndexOutOfBoundsException("capacity is " + elements.length + ";size is " + N);
        }
        elements[N++] = item;
    }

    public Item pop(){
        if (N <= 0){
            throw new IndexOutOfBoundsException("inde is " + N);
        }
        return elements[--N];
    }

}
