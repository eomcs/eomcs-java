package com.eomcs.oop.ex09.h;

public class Exam0110 {
  public static void main(String[] args) {
    Car c1 = new Tico();

    if (CarCheckInfo.validate(c1)) {
      c1.start();
      c1.run();
      c1.shutdown();
    } else {
      System.out.println("자동차를 점검하시기 바랍니다. 문제 발생!");
    }

  }
}
