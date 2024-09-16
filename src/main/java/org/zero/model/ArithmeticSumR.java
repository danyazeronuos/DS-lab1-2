package org.zero.model;

import java.util.function.Function;

public record ArithmeticSumR(Double a, Double segmentLength, Function<Double, Double> function) {}
