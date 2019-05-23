package com.eomcs.basic.ch03.test;

//# 정수 리터럴 - 크기에 따른 표기법
//
public class Exam22 {
  public static void main(String[] args) {
    
    System.out.println(/* 1) 4바이트 양의 정수 최대값을 리터럴로 표현하라 */);
    
    // 2) 다음 문장에서 컴파일 오류가 발생한 이유는 무엇인가?
    // 3) 값을 출력할 때 오류가 발생하지 않도록 코드를 변경하라.
    System.out.println(2147483648);
    
    // 4) 다음 두 수의 차이점은 무엇인가?
    System.out.println(100);
    System.out.println(100L);
  }
}