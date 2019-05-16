package com.eomcs.basic.ch03.solution;

//# 정수 리터럴 - 크기에 따른 표기법
//
//- 크기에 따라 표기방법이 다르다.
//
public class Exam023 {
  public static void main(String[] args) {
    //## 4바이트 정수
    //- 기본이 4바이트 크기의 정수이다.
    System.out.println(2147483647); // 4바이트 양의 정수 최대값
    System.out.println(-2147483648); // 4바이트 음의 정수 최소값
    
    //- 4바이트 메모리 크기를 초과하면 컴파일 오류가 발생한다.
    //System.out.println(2147483648);
    //System.out.println(-2147483649);
    
    //## 8바이트 정수
    //- 4바이트 메모리에 저장할 수 없는 수를 표현할 때 사용한다.
    //- 숫자 뒤에 L 또는 l을 붙인다.
    //- 보통 대문자를 붙인다. 왜? 소문자 l은 숫자 1과 헷갈리기 때문이다.
    System.out.println(2147483648L); 
    System.out.println(-2147483649L); 
    
    //## 4바이트 또는 8바이트 정수의 최대값과 최소값
    //- 자바에서는 각 데이터 유형에 따라 최대/최소 값을 알아볼 수 있도록 특별한 명령을 제공한다.
    System.out.println(Integer.MAX_VALUE); // 4바이트로 표현할 수 있는 정수 최대값
    System.out.println(Integer.MIN_VALUE); // 4바이트로 표현할 수 있는 정수 최소값
    System.out.println(Long.MAX_VALUE); // 8바이트로 표현할 수 있는 정수 최대값 
    System.out.println(Long.MIN_VALUE); // 8바이트로 표현할 수 있는 정수 최소값
    
    //## 주의!
    //- 다음은 크기가 다른 정수이다.
    System.out.println(100); // 4바이트 크기의 정수 값 
    System.out.println(100L); // 8바이트 크기의 정수 값
  }
}

//## 실습
//1) 컴파일하기
//   eomcs-java-basic$ javac -d bin/main -encoding UTF-8 src/main/java/com/eomcs/basic/ch03/solution/Exam023.java
//
//2) 실행하기
//   eomcs-java-basic$ java -cp bin/main com.eomcs.basic.ch03.solution.Exam023
//