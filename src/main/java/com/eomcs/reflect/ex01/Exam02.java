// Reflection API : 클래스 로딩과 레퍼런스
package com.eomcs.reflect.ex01;

public class Exam02 {
  static class A {
    static int s_var = 100;
    int i_var = 200;

    static void s_m() {}
    void i_m() {}

    static {
      System.out.println("A 클래스 로딩!");
    }
  }

  public static void main(String[] args) throws Exception {
    Class clazz = Class.forName("step18.ex1.Exam02$A");
    // 중첩 클래스는 "클래스명$중첩클래스명" 형식의 이름을 갖는다.
  }

}





