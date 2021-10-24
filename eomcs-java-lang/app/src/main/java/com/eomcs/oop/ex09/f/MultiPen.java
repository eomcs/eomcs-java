package com.eomcs.oop.ex09.f;

public class MultiPen extends Zebra implements Pen {
  @Override
  public void write(String text) {
    if (!this.use)
      return;
    System.out.println("제브라>> " + text);
  }
}
