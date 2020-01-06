package com.eomcs.basic.ex05;

//# 비트 연산자 & 를 이용하여 % 연산 구현하기
//
public class Exam0351 {
  public static void main(String[] args) {
    System.out.println(57 % 2);
    System.out.println(57 & 0b1);
    // 어떤 값에 대해 2로 나눈 나머지 값을 구하고 싶다면,
    // & 연산자를 이용하여 그 값의 하위 1비트 값만 추출하면 된다. 
    
    System.out.println(57 % 4);
    System.out.println(57 & 0b11);
    // 어떤 값에 대해 4로 나눈 나머지 값을 구하고 싶다면,
    // & 연산자를 이용하여 그 값의 하위 2비트 값만 추출하면 된다.
    
    System.out.println(57 % 8);
    System.out.println(57 & 0b111);
    
    System.out.println(57 % 16);
    System.out.println(57 & 0b1111);
    
  }
}

