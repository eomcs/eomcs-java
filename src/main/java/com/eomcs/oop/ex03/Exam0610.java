// 스태틱 초기화 블록(static initializer) - 레퍼런스 선언
package com.eomcs.oop.ex03;

public class Exam0610 {

  public static class A {
    static int a;

    static void m() {}

    // 클래스가 로딩된 후 즉시 스태틱 초기화 블록이 실행된다.
    // - 여러 개의 스태틱 블록이 있을 때, 컴파일러는 한 개의 블록으로 합친다.
    // - 바이트코드(Exam0610$A.class)를 확인해 보라.
    //
    static {
      System.out.println("Static{} 11111");
    }

    static {
      System.out.println("Static{} 22222");
    }
  }

  public static void main(String[] args) throws Exception {

    // 클래스가 로딩되는 경우,
    // - 레퍼런스를 선언할 때는 클래스가 로딩되지 않는다.
    // - 그래서 스태틱 초기화 블록이 실행되지 않는다.
    A obj1;
    A obj2;

    System.out.println("종료!");
  }
}


