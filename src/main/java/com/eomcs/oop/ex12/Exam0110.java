// Lambda 문법 - 익명 클래스 vs 람다
package com.eomcs.oop.ex12;

public class Exam0110 {

  // 다음과 같이 추상 메서드가 한 개 있는 인터페이스를 "functional interface"라고 부른다.
  // => 이런 경우에 람다 문법으로 사용할 수 있다.
  // => 인터페이스는 static 을 붙이지 않아도 static 멤버가 사용할 수 있다.
  interface Player {
    void play();
  }

  public static void main(String[] args) {

    // 익명 클래스로 인터페이스 구현하기
    Player p1 = new Player() {
      @Override
      public void play() {
        System.out.println("익명 클래스");
      }
    };
    p1.play();

    // 람다 문법으로 인터페이스 구현하기
    Player p2 = () -> {
      System.out.println("람다");
    };
    p2.play();
  }
}


