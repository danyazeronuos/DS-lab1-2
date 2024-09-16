package org.zero;

import org.zero.model.Manager;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class StandardThreadManager implements Manager<Double> {
    private final Integer threadCount;
    private final List<StandardThread> threadList = new ArrayList<>();

    public StandardThreadManager(Integer threadCount) {
        System.out.println("Init StandardThreadManager");
        this.threadCount = threadCount;
    }

    @Override
    public Integer getThreads() {
        return this.threadCount;
    }

    @Override
    public void createThread(Supplier<Double> function) {
        threadList.add(new StandardThread().init(function).start());
    }

    @Override
    public Stream<Double> join() {
        return threadList.stream()
                .map(
                        element -> {
                            try {
                                return element.join();
                            } catch (InterruptedException _) {}
                            return null;
                        });
    }
}
