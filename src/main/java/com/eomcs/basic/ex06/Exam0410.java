package com.eomcs.basic.ex06;

//# 흐름 제어문 - for 반복문
//
public class Exam0410 {
  public static void main(String[] args) {
    // for (변수선언 및 초기화; 조건; 증감문) 문장;
    // for (변수선언 및 초기화; 조건; 증감문) {문장1; 문장2; ...}

    // for 문의 전형적인 예
    for (int i = 1; i <= 5; i++) 
      System.out.println(i);

    // for 문에서 선언한 변수는 그 for 문 안에서만 사용할 수 있다.
    //System.out.println(i); // 컴파일 오류!
    
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
    System.out.println(i);
    
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
