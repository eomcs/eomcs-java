package com.eomcs.basic.ch03.test;

//# 부동소수점 리터럴 - 유효자릿수

public class Exam33 {
  public static void main(String[] args) {
    
    //1) 다음 부동소수점 값을 출력할 때 원래의 값과 다르게 출력된다. 이유는 무엇인가?
    //2) 제대로 출력하도록 코드를 변경하라.
    System.out.println(987654321.1234567f);
    
    //3) 다음 식의 출력 결과가 0.7 아닌 이유는 무엇인가?
    System.out.println(7 * 0.1); //결과: 0.7000000000000001
  }
}
