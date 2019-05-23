package com.eomcs.basic.ch03.test;

//# 논리값 리터럴
//
//- 참, 거짓을 표현할 때 사용한다.
//
public class Exam5 {
  public static void main(String[] args) {
    
    //- 참을 표현할 때는 true, 거짓을 표현할 때는 'false'이다.
    //- 자바는 대소문자를 구분하기 때문에 반드시 소문자로 작성해야 한다.
    System.out.println(true);
    System.out.println(false);
    
    //- 비교 연산의 결과는 논리값이다.
    System.out.println(4 < 5); 
    System.out.println(4 > 5); 

    //- 논리 연산의 결과는 논리값이다.
    System.out.println(true && false);
    System.out.println(true || false);
  }
}

//## 실습
//1) 컴파일하기
//   eomcs-java-basic$ javac -d bin/main -encoding UTF-8 src/main/java/com/eomcs/basic/ch03/solution/Exam5.java
//
//2) 실행하기
//   eomcs-java-basic$ java -cp bin/main com.eomcs.basic.ch03.solution.Exam5
//