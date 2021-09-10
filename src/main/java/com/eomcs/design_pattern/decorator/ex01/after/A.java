package com.eomcs.design_pattern.decorator.ex01.after;

public class A extends Printer {
  @Override
  public void f1(String name) {
    System.out.println("이름: " + name);
  }
}
