package org.zero;

import java.util.function.Supplier;

public class StandardThread {
  private Thread thread;
  private ThreadTask task;

  public StandardThread init(Supplier<Double> function) {
    this.task = new ThreadTask();
    this.thread = new Thread(() -> this.task.run(function));
    return this;
  }

  public StandardThread start() {
    this.thread.start();
    return this;
  }

  public Double join() throws InterruptedException {
    this.thread.join();
    return this.task.getValue();
  }

  private class ThreadTask {
    private volatile Double value;

    public void run(Supplier<Double> function) {
      value = function.get();
    }

    public Double getValue() {
      return value;
    }
  }
}
