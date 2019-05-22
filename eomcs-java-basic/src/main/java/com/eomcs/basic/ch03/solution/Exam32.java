package com.eomcs.basic.ch03.solution;

//# 부동소수점 리터럴 - 최소값과 최대값

public class Exam32 {
  public static void main(String[] args) {
    
    //## 4바이트 부동소수점의 최대값과 최소값
    System.out.println(Float.MAX_VALUE);
    System.out.println(Float.MIN_VALUE);
    
    //## 8바이트 부동소수점의 최대값과 최소값
    System.out.println(Double.MAX_VALUE);
    System.out.println(Double.MIN_VALUE);
  }
}

//## 실습
//1) 컴파일하기
//   eomcs-java-basic$ javac -d bin/main -encoding UTF-8 src/main/java/com/eomcs/basic/ch03/solution/Exam32.java
//
//2) 실행하기
//   eomcs-java-basic$ java -cp bin/main com.eomcs.basic.ch03.solution.Exam32
//
