package org.zero;

public class Range {
  private final int start;
  private final int end;

  public Range(int start, int end) {
    this.start = start;
    this.end = end;
  }

  public static Range range(int start, int end) {
    return new Range(start, end);
  }

  public int isBetween(int num) {
    var startBorder = Math.max(start, num);
    return Math.min(end, startBorder);
  }
}
