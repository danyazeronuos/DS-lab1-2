package org.zero;

import org.zero.model.Manager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class FutureThreadManager implements Manager<Double> {
    private final Integer threadCount;
    private final List<CompletableFuture<Double>> threadList = new ArrayList<>();

    public FutureThreadManager(Integer threadCount) {
        System.out.println("Init FutureThreadManager");
        this.threadCount = threadCount;
    }

    @Override
    public Integer getThreads() {
        return this.threadCount;
    }

    @Override
    public void createThread(Supplier<Double> function) {
        this.threadList.add(CompletableFuture.supplyAsync(function));
    }

    @Override
    public Stream<Double> join() {
        return this.threadList.stream().map(CompletableFuture::join);
    }
}
