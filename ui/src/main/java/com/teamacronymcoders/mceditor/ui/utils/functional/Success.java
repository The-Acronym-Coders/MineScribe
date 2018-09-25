package com.teamacronymcoders.mceditor.ui.utils.functional;

import java.util.function.Function;

public class Success<T> extends Try<T> {
    private final T value;

    Success(T value) {
        this.value = value;
    }

    @Override
    public <U> Try<U> attemptMap(ThrowingFunction<T, U> function) {
        try {
            return Try.success(function.apply(value));
        } catch (Exception e) {
            return Try.failure(new RuntimeException("Map Failed", e));
        }
    }

    @Override
    public <U> Try<U> map(Function<T, U> function) {
        return success(function.apply(value));
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public boolean isFailure() {
        return false;
    }
}
