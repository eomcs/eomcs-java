package com.eomcs.web;

public class Test {

  public static Box createBox() {
    int value = 100;

    class MyBox implements Box {
      public int getValue() {
        return value;
      }
    }

    return new MyBox();
  }

  public static void main(String[] args) {
    Box box = Test.createBox();
    System.out.println(box.getValue());
  }
}
