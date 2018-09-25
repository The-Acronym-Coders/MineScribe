package com.teamacronymcoders.mceditor.ui.utils.functional;

@FunctionalInterface
public interface ThrowingFunction<T, U> {
    U apply(T t) throws Exception;
}
