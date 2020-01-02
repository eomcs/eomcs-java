// Reflection API : 클래스 로딩과 인스턴스 생성
package com.eomcs.reflect.ex01;


public class Exam05 {
  static class A {
    void m() {
      System.out.println("Hello!");
    }
  }

  public static void main(String[] args) throws Exception {
    Class clazz = Class.forName("step18.ex1.Exam05$A");

    // 타입(클래스) 정보만 있다면 인스턴스 생성할 수 있다.
    A obj = (A) clazz.newInstance();
    obj.m();
  }

}





