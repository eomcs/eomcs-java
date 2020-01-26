package com.eomcs.oop.ex02;

// # 관련된 기능(메서드)을 묶어 분류하기 - 인스턴스 변수와 인스턴스 메서드
//
public class Exam0260 {

  static class Calculator {
    int result = 0;

    // 인스턴스 변수를 다룰 때는 인스턴스 메서드를 사용하는 것이 편하다!
    // 왜?
    // - 파라미터로 따로 인스턴스의 주소를 받을 필요가 없기 때문이다.
    //
    void plus(int value) {
      // 인스턴스 메서드는 인스턴스의 주소를 this라는 내장 변수에 자동으로 받는다.
      this.result += value;
    }

    void minus(int value) {
      this.result -= value;
    }

    void multiple(int value) {
      this.result *= value;
    }

    void divide(int value) {
      this.result /= value;
    }

    // 인스턴스를 사용하지 않는 메서드라면 그냥 클래스 메서드로 두어라.
    static int abs(int a) {
      return a >= 0 ? a : a * -1;
    }
  }

  public static void main(String[] args) {
    // 다음 두 개의 식을 분리하여 계산해 보자!
    // - 연산자 우선 순위를 고려하지 않고 순서대로 계산하라!
    // 식1) 2 + 3 - 1 * 7 / 3 = ?
    // 식2) 3 * 2 + 7 / 4 - 5 = ?


    Calculator c1 = new Calculator(); // 식1의 계산 결과를 보관할 메모리 준비
    Calculator c2 = new Calculator(); // 식2의 계산 결과를 보관할 메모리 준비

    // 계산을 수행할 때 계산 결과를 보관할 메모리를 메서드 호출 앞에서 전달하라!
    // - 인스턴스 메서드를 사용하면 파라미터로 메모리 주소를 전달할 필요가 없다.
    c1.plus(2); // + 2
    c2.plus(3); // + 3

    c1.plus(3); // + 2 + 3
    c2.multiple(2); // + 3 * 2

    c1.minus(1); // + 2 + 3 - 1
    c2.plus(7); // + 3 * 2 + 7

    c1.multiple(7); // + 2 + 3 - 1 * 7
    c2.divide(4); // + 3 * 2 + 7 / 4

    c1.divide(3); // + 2 + 3 - 1 * 7 / 3 = ?
    c2.minus(5); // + 3 * 2 + 7 / 4 - 5 = ?

    // 식1의 계산 결과는 c1 인스턴스의 result에 들어 있고,
    // 식2의 계산 결과는 c2 인스턴스의 result에 들어 있다.
    System.out.printf("c1.result = %d\n", c1.result);
    System.out.printf("c2.result = %d\n", c2.result);
  }
}

