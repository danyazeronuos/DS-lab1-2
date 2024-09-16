package org.zero.model;

import java.util.function.Supplier;
import java.util.stream.Stream;

public interface Manager<T> {
   Integer getThreads();
   void createThread(Supplier<T> function);
   Stream<T> join();
}