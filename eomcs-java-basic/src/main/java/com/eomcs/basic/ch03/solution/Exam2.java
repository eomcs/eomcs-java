package com.eomcs.basic.ch03.solution;

//# 정수 리터럴 - 다양한 기수법으로 정수를 표현하기
//
//- 자바에서 정수 값을 표현하는 방법
//
public class Exam2 {
  public static void main(String[] args) {
    //## 정수 값 100을 다양한 진법으로 표현하기

    //### 10진수 리터럴 
    //- 코드에서 일반적으로 정수 값을 표현할 때 사용한다.
    System.out.println(100);
    
    //### 8진수 리터럴
    //- 코드를 작성할 때 잘 사용하지 않는다.
    //- 0으로 시작해야 한다.
    System.out.println(0144);
    
    //### 2진수 리터럴
    //- 메모리의 상태를 직설적으로 보여주고 싶을 때 사용한다.
    //- 0b 또는 0B로 시작한다.
    System.out.println(0b1100100);
    System.out.println(0B1100100);
    
    //- 숫자 앞에 0이 있어도 된다.
    System.out.println(0b01100100);
    System.out.println(0B01100100);
    
    //### 16진수 리터럴
    //- 2진수를 간결하게 표현하기 위해 사용한다.
    //- 0x 또는 0X 로 시작한다.
    System.out.println(0x64);
    System.out.println(0X64);
    
    //- 숫자 앞에 0이 있어도 된다.
    System.out.println(0x64);
    System.out.println(0X64);
  }
}


//## 실습
//1) 컴파일하기
//   eomcs-java-basic$ javac -d bin/main -encoding UTF-8 src/main/java/com/eomcs/basic/ch03/solution/Exam2.java
//
//2) 실행하기
//   eomcs-java-basic$ java -cp bin/main com.eomcs.basic.ch03.solution.Exam2
//