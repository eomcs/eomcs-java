package com.eomcs.basic.ch03.solution;

//# 리터럴(literal)
//
//- 자바에서 값을 표현한 것
//

public class Exam1 {
  public static void main(String[] args) {
    //## 정수 리터럴
    System.out.println(10);
    System.out.println(+10);
    System.out.println(-10);
    
    //## 부동소수점
    System.out.println(3.14);
    System.out.println(31.4e-1);
    
    //## 논리값 
    System.out.println(true);
    System.out.println(false);
    
    //## 한 개의 문자
    System.out.println('가');
    
    //## 문자열
    System.out.println("오호라..");
    
    //## null
    System.out.println(args == null);
  }
}

//## 실습
//1) 컴파일하기
//   eomcs-java-basic$ javac -d bin/main -encoding UTF-8 src/main/java/com/eomcs/basic/ch03/solution/Exam1.java
//
//2) 실행하기
//   eomcs-java-basic$ java -cp bin/main com.eomcs.basic.ch03.solution.Exam1
//