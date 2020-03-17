// Reflection API : 클래스 로딩과 인스턴스 생성
package com.eomcs.reflect.ex01;


public class Exam05 {

  static class A {
    void m() {
      System.out.println("Hello!");
    }
  }

  public static void main(String[] args) throws Exception {
    Class clazz = Class.forName("com.eomcs.reflect.ex01.Exam05$A");

    // 타입(클래스) 정보만 있다면 인스턴스 생성할 수 있다.
    A obj = (A) clazz.newInstance();
    obj.m();

    // deprecated 메서드이다.
    // 자바는 생성자를 이용하여 인스턴스를 생성할 것을 권고하고 있다.
  }

}


