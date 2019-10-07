package com.eyckwu.lang;

import com.eyckwu.utils.Iterator;

import java.util.Objects;
import java.util.function.Consumer;

public interface Iterable<T> {

    Iterator<T> iterator();

    default void foreach(Consumer<? extends T> action){
        Objects.requireNonNull(action);
        for (T a : this){
            action.accept(a);
        }
    }
}
