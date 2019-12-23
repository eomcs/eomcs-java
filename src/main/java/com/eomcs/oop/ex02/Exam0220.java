package com.eomcs.oop.ex02;

//# 메서드 분류 - 클래스 변수의 활용
//
public class Exam0220 {
  public static void main(String[] args) {
    // 계산 결과를 보관할 변수는 더이상 필요가 없다.
    // Calculator2 내부에서 계산 결과를 관리한다.
    //int result = 0;

    // 2 + 3 - 1 * 7 / 3 = ?
    // => 연산자 우선 순위를 고려하지 않고 순서대로 계산하라!
    Calculator2.plus(2);
    Calculator2.plus(3);
    Calculator2.minus(1);
    Calculator2.multiple(7);
    Calculator2.divide(3);

    System.out.printf("result = %d\n", Calculator2.result);  
  }
}

