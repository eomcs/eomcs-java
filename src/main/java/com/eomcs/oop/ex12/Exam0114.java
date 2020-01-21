// Lambda 문법 - functional interface를 효과적으로 다루는 문법
package com.eomcs.oop.ex12;

public class Exam0114 {

  // 다음과 같이 추상 메서드가 한 개 있는 인터페이스를 "functional interface"라고 부른다.
  // => 이런 경우에 람다 문법으로 사용할 수 있다.
  static interface Player {
    void play();
  }

  public static void main(String[] args) {
    // 람다 문법으로 인터페이스 구현 - 코드 정리
    // 주의!
    // => 파라미터가 없는 경우 괄호를 생략할 수 없다.
    Player p1 = -> System.out.println("테스트1"); // 컴파일 오류!

    p1.play();
  }
}


