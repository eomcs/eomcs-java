// 메서드 레퍼런스(method reference) - 인스턴스 메서드 레퍼런스
package com.eomcs.oop.ex12;

public class Exam0520 {

  static interface Interest {
    double compute(int money);
  }

  static class Calculator {
    double rate;

    public Calculator(double rate) {
      this.rate = rate;
    }

    public double year(int money) {
      return money * rate / 100;
    }

    public double month(int money) {
      return money * rate / 100 / 12;
    }

    public double day(int money) {
      return money * rate / 100 / 365;
    }
  }

  public static void main(String[] args) {
    // '람다'는 새로 메서드를 구현해야 하지만,
    // '메서드 레퍼런스'는 기존 클래스의 메서드를 재활용 할 수 있다.
    //

    // 인스턴스 메서드 레퍼런스
    // - 특정 인스턴스의 값을 바탕으로 작업을 수행하는 메서드를 지정할 수 있다.
    // 문법:
    // => 객체::메서드명
    //
    Calculator c1 = new Calculator(1.5);

    Interest i1 = c1::year;
    System.out.printf("년 이자: %.1f\n", i1.compute(1_0000_0000));

    i1 = c1::month;
    System.out.printf("월 이자: %.1f\n", i1.compute(1_0000_0000));

    i1 = c1::month;
    System.out.printf("일 이자: %.1f\n", i1.compute(1_0000_0000));

    System.out.println("--------------------------");

    Calculator c2 = new Calculator(2.5);

    Interest i2 = c2::year;
    System.out.printf("년 이자: %.1f\n", i2.compute(1_0000_0000));

    i2 = c2::month;
    System.out.printf("월 이자: %.1f\n", i2.compute(1_0000_0000));

    i2 = c2::month;
    System.out.printf("일 이자: %.1f\n", i2.compute(1_0000_0000));
  }

}


