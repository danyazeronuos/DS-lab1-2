package org.zero;

import java.util.function.Function;

public class MyFunction implements Function<Double, Double> {

  @Override
  public Double apply(Double t) {
    return Math.cos(4 * t) * Math.cos(2 * t);
  }
}
