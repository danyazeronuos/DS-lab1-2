package org.zero.model;

import java.util.function.Function;

public interface TaskExecutor<T> {
   T process(Function<Integer, T> function, int iterations);
}