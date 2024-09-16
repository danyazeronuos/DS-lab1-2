package org.zero;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyFunctionTest {

  @Test
  public void calculate() {
    var function = new MyFunction();
    var result = function.apply(5.0);
    Assertions.assertEquals(-0.34241, result, 0.0000001);
  }
}
