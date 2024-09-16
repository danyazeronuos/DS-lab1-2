package org.zero;

import org.zero.model.ArithmeticSumR;
import org.zero.model.Manager;

import java.util.function.Function;

public class IntegralCalculator {
    private final Manager<Double> manager;

    public IntegralCalculator(Manager<Double> manager) {
        this.manager = manager;
    }

    public Double calculate(Double a, Double b, int n, Function<Double, Double> function) {
        var segmentLength = getSegmentLength(a, b, n);
        var leftPart = calculateLeftPart(n, function);

        var manager = new SumTaskExecutor(this.manager);
        var params = new ArithmeticSumR(a, segmentLength, function);
        var calculateStep = new CalculateStep();
        Double calculatedSum = manager.process((Integer index) -> calculateStep.apply(index, params), n);


        return segmentLength * (leftPart + calculatedSum);
    }

    private static Double calculateLeftPart(int n, Function<Double, Double> function) {
        return (function.apply(0.0) + function.apply((double) n)) / 2;
    }

    private static Double getSegmentLength(Double a, Double b, int n) {
        return (b - a) / n;
    }
}
