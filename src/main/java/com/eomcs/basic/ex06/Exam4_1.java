package com.eomcs.basic.ex06;

//# 흐름 제어문 - for 반복문
//
public class Exam4_1 {
  public static void main(String[] args) {
    // for (변수선언 및 초기화; 조건; 증감문) 문장;
    // for (변수선언 및 초기화; 조건; 증감문) {문장1; 문장2; ...}

    // for 문의 전형적인 예
    for (int i = 1; i <= 5; i++) 
      System.out.println(i);

    System.out.println("----------------------");
    for (int i = 1; i <= 5;) {
      System.out.println(i);
      i++; 
    }

    System.out.println("----------------------");
    int i = 1;
    for (; i <= 5;) {
      System.out.println(i);
      i++; 
    }

    System.out.println("----------------------");
    i = 1;
    for (;;) {
      if (i > 5)
        break;
      System.out.println(i);
      i++; 
    }

  }
}
