package com.eomcs.lang.ex06;

//# 흐름 제어문 - if ~ else 문
//
public class Exam0140 {
  public static void main(String[] args) {
    int age = 17;

    // if 문은 else 문 없이 단독으로 사용할 수 있다.
    if (age < 19)
      System.out.println("미성년입니다.");

    // else문을 사용하는 아주 모범적인 예!
    if (age >= 19) // 조건이 거짓이면 다음 else 문을 실행한다.
      System.out.println("성인입니다.");
    else
      System.out.println("미성년입니다.");

    // 여러 문장을 실행할 때 블록으로 묶어야 한다.
    if (age >= 20) {
      System.out.println("--------------");
      System.out.println("성인입니다.");
    }
    else {
      System.out.println("--------------");
      System.out.println("미성년입니다.");
    }

    // if, else 둘 중 하나만 블록으로 묶을 수 있다.
    if (age >= 19) { // OK!
      System.out.println("성인입니다.");
      System.out.println("--------------------------");
    }
    else
      System.out.println("미성년입니다.");

    // if, else 둘 중 하나만 블록으로 묶을 수 있다.
    if (age >= 19)
      System.out.println("성인입니다.");
    else {
      System.out.println("미성년입니다.");
      System.out.println("--------------------------");
    }

    // 잘못 사용한 예:
    // => else 문만 단독으로 사용할 수 없다.
    //    항상 if 문과 짝을 이뤄 사용한다.
    /*
        else
            System.out.println("컴파일 오류!");
     */

    // => if 문과 else 문 사이에 다른 문장이 올 수 없다.
    /*
        if (age >= 19)
            System.out.println("성인입니다.");
        System.out.println("--------------------------");
        else
            System.out.println("미성년입니다.");
     */



  }
}