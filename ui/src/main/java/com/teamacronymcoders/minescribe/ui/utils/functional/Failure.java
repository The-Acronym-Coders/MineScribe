package com.teamacronymcoders.minescribe.ui.utils.functional;

import java.util.function.Function;

public class Failure<T> extends Try<T> {
    private final RuntimeException runtimeException;

    Failure(RuntimeException runtimeException) {
        this.runtimeException = runtimeException;
    }

    @Override
    public <U> Try<U> attemptMap(ThrowingFunction<T, U> f) {
        return failure(runtimeException);
    }

    @Override
    public <U> Try<U> map(Function<T, U> function) {
        return failure(runtimeException);
    }

    @Override
    public T get() {
        throw runtimeException;
    }

    @Override
    public boolean isFailure() {
        return true;
    }
}
