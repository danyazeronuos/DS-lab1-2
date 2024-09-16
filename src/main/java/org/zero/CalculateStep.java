package org.zero;

import org.zero.model.ArithmeticSumR;

import java.util.function.BiFunction;

public class CalculateStep implements BiFunction<Integer, ArithmeticSumR, Double> {

  @Override
  public Double apply(Integer i, ArithmeticSumR t) {
    var currentValue = t.a() + i * t.segmentLength();
    return t.function().apply(currentValue);
  }
}
