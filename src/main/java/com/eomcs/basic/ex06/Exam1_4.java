package com.eomcs.basic.ex06;

//# 흐름 제어문 - if ~ else 문
//
public class Exam1_4 {
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

    // => 블록에 들어 있는 문장은 if 문에 소속되기 때문에 
    //    블록 안에 여러 개의 문장이 와도 상관없다.
    if (age >= 19) { // OK!
      System.out.println("성인입니다.");
      System.out.println("--------------------------");
    }
    else 
      System.out.println("미성년입니다.");

  }
}