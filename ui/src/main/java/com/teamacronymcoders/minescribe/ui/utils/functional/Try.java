package com.teamacronymcoders.minescribe.ui.utils.functional;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class Try<T> {
    public static <U> Try<U> success(U value) {
        return new Success<>(value);
    }

    public static <U> Try<U> failure(RuntimeException e) {
        return new Failure<>(e);
    }

    public abstract <U> Try<U> attemptMap(ThrowingFunction<T, U> function);

    public abstract <U> Try<U> map(Function<T, U> function);

    public abstract T get();

    public abstract boolean isFailure();

    public static <U> Try<List<U>> sequence(List<Try<U>> listInput) {
        List<U> properInputs = new ArrayList<>();
        for (Try<U> attempted: listInput) {
            if (attempted.isFailure()) {
                return attempted.map(Lists::newArrayList);
            } else {
                properInputs.add(attempted.get());
            }
        }
        return Try.success(properInputs);
    }


}
