package com.meli.xmen.utils;

@FunctionalInterface
public interface ThreeFunction<T, R> {
    R apply(T c, int x, int y);
}
