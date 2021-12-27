package com.meli.xmen.service;

@FunctionalInterface
public interface ThreeFunction<T, R> {
    R apply(T c, int x, int y);
}
