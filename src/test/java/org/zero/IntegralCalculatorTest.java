package org.zero;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.zero.model.ArithmeticSumR;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;


public class IntegralCalculatorTest {

  @Test
  public void calculateTest() throws InterruptedException, ExecutionException {
    var function = new MyFunction();
    var standardThreadManager = new StandardThreadManager(8);
    var calculator = new IntegralCalculator(standardThreadManager);
    var result = calculator.calculate(0.0, Math.PI / 3.0, 100_000_000, function);
    Assertions.assertEquals(0.216506, result, 0.000001);
  }

  @Test
  public void calculateLeftPartTest()
      throws IllegalAccessException,
          IllegalArgumentException,
          InvocationTargetException,
          NoSuchMethodException,
          SecurityException {
    var function = new MyFunction();
    var result = this.getCalculateLeftPartMethod().invoke(null, 5, function);
    if (result instanceof Double value) Assertions.assertEquals(0.328795, value, 0.000001);
  }

  @Test
  public void getSegmentLengthTest()
      throws NoSuchMethodException,
          SecurityException,
          IllegalAccessException,
          IllegalArgumentException,
          InvocationTargetException {
    var result = this.getSegmentLength();
    if (result instanceof Double value) Assertions.assertEquals(2.0, value, 0.1);
  }

  @Test
  public void calculateArithmeticSumTest()
      throws IllegalAccessException,
          IllegalArgumentException,
          InvocationTargetException,
          NoSuchMethodException,
          SecurityException
           {
    var function = new MyFunction();
    var segmentLength = this.getSegmentLength();
    if (segmentLength instanceof Double sl) {
      var standardThreadManager = new StandardThreadManager(1);
      var manager = new SumTaskExecutor(standardThreadManager);

      var params = new ArithmeticSumR(0.0, sl, function);
      var calculateStep = new CalculateStep();

      Double calculatedSum = manager.process((Integer index) -> calculateStep.apply(index, params), 5);
      Assertions.assertEquals(-0.2065128, calculatedSum, 0.000001);
    }
  }

  private Object getSegmentLength()
      throws IllegalAccessException,
          IllegalArgumentException,
          InvocationTargetException,
          NoSuchMethodException,
          SecurityException {
    return this.getGetSegmentLengthMethod().invoke(null, 0.0, 10.0, 5);
  }

  public Method getGetSegmentLengthMethod() throws NoSuchMethodException, SecurityException {
    var method =
        IntegralCalculator.class.getDeclaredMethod(
            "getSegmentLength", Double.class, Double.class, int.class);
    method.setAccessible(true);
    return method;
  }

  public Method getCalculateLeftPartMethod() throws NoSuchMethodException, SecurityException {
    var method =
        IntegralCalculator.class.getDeclaredMethod("calculateLeftPart", int.class, Function.class);
    method.setAccessible(true);
    return method;
  }
}
