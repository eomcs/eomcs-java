// 메서드 레퍼런스 - 활용예
package com.eomcs.oop.ex12;

import java.util.function.Predicate;

public class Exam0630 {

  public static void main(String[] args) {

    // Predicate<String> 인터페이스 구현체 준비하기

    // 1) 로컬 클래스로 인터페이스 구현체 만들기
    class MyPredicate<T> implements Predicate<T> {
      @Override
      public boolean test(T value) {
        return ((String)value).isEmpty();
      }
    }
    Predicate<String> p1 = new MyPredicate<>();

    // 2) 익명 클래스로 인터페이스 구현체 만들기
    Predicate<String> p2 = new Predicate<>() {
      @Override
      public boolean test(String value) {
        return value.isEmpty();
      }
    };

    // 3) 람다로 인터페이스 구현체 만들기
    Predicate<String> p3 = value -> value.isEmpty();

    // 4) 메서드 레퍼런스를 사용하여 기존 클래스의 메서드를 인터페이스 구현체로 사용하기
    Predicate<String> p4 = String::isEmpty;
  }

}


