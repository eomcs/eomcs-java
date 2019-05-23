package com.eomcs.basic.ch03.test;

//# 정수 리터럴 - 자릿수 표기
//
public class Exam21 {
  public static void main(String[] args) {
    
    //1) 밑줄(_)을 사용하여 다음 리터럴을 세자리 단위로 끊어 표현하라. 
    System.out.println(12345678);

    //2) 다음 정수 리터럴은 무엇이 잘못되었는가?
    System.out.println(_12783406); 
    
    //3) 다음 2진수 리터럴을 네자리 마다 끊어서 표현하라.
    System.out.println(0b1100100);

    //4) 다음 2진수 리터럴은 무엇이 잘못되었는가?
    System.out.println(0b1100100_);
  }
}
