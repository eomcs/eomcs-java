package com.eomcs.oop.ex05.x1.test1;

public class CalculatorTest {
  public static void main(String[] args) {
    Calculator c = new Calculator();

    c.plus(100);
    c.minus(200);

    System.out.println(c.result);
  }
}
