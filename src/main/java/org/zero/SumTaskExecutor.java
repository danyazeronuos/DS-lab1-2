package org.zero;

import org.zero.model.Manager;
import org.zero.model.TaskExecutor;

import java.util.function.Function;
import java.util.stream.IntStream;

public class SumTaskExecutor implements TaskExecutor<Double> {
    private final Manager<Double> manager;

    public SumTaskExecutor(Manager<Double> manager) {
        this.manager = manager;
    }

    @Override
    public Double process(Function<Integer, Double> function, int iterations) {
        var step = iterations / this.manager.getThreads();

        for (var i = 1; i < this.manager.getThreads(); i++) {
            var end = i * step;
            var start = getValueBetween(end - step);
            this.manager.createThread(() -> {
                return IntStream.range(start, end).mapToDouble(function::apply).sum();
            });

        }


        var start = getValueBetween((this.manager.getThreads() * step) - step);
        var mainThreadSum = IntStream.range(start, iterations).mapToDouble(function::apply).sum();


        return this.manager.join().mapToDouble(Double::doubleValue).sum() + mainThreadSum;
    }

    private static int getValueBetween(int value) {
        return Range.range(1, Integer.MAX_VALUE).isBetween(value);
    }
}
