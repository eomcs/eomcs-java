package com.eomcs.basic.ch03.test;

//# 부동소수점 리터럴 - 크기에 따른 표기법

public class Exam31 {
  public static void main(String[] args) {
    
    //## 8바이트 크기의 부동소수점
    //- 숫자 맨 뒤에 d 또는 D를 붙인다.
    //- 생략해도 된다.
    System.out.println(3.14);
    System.out.println(3.14d);
    System.out.println(3.14D);

    //## 4바이트 크기의 부동소수점
    //- 숫자 맨 뒤에 f 또는 F를 붙인다.
    System.out.println(3.14f); // 주로 소문자 f를 많이 붙인다.
    System.out.println(3.14F);
  }
}

//## 실습
//1) 컴파일하기
//   eomcs-java-basic$ javac -d bin/main -encoding UTF-8 src/main/java/com/eomcs/basic/ch03/solution/Exam31.java
//
//2) 실행하기
//   eomcs-java-basic$ java -cp bin/main com.eomcs.basic.ch03.solution.Exam31
//
