//# 소스 파일의 인코딩 지정하기
//
//- 컴파일 할 때 소스 파일의 문자집합을 지정하는 방법
//
//## 컴파일할 때 소스 파일의 인코딩 문자집합을 지정하기
//- 컴파일할 때 소스 파일의 인코딩 문자집합을 지정하지 않으면, 운영체제의 기본 문자 집합으로 간주하고 컴파일한다.
//- 만약 소스 파일의 인코딩 문자집합이 운영체제의 기본 문자표(character set)와 다르다면, 컴파일 오류가 발생한다.
//- 소스 파일의 인코딩 문자집합이 운영체제의 기본 문자집합과 다를 경우 컴파일할 때 옵션을 지정해야 한다.
//- 컴파일 옵션
//   javac -encoding [문자집합] [소스파일명]
//
package com.eomcs.basic.ch01.solution;

class Exam2 {
  public static void main(String[] args) {
    System.out.println("안녕하세요!");
  }
}

//## 실습
//1) 컴파일하기
//     eomcs-java-basic$ javac -encoding [문자집합] [소스파일명]
//     예) eomcs-java-basic$ javac -d bin/main -encoding UTF-8 src/main/java/com/eomcs/basic/ch01/solution/Exam2.java
//2) 실행하기
//     eomcs-java-basic$ java -cp bin/main com.eomcs.basic.ch01.solution.Exam2