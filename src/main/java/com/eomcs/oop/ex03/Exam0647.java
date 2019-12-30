// 실행 순서 종합
package com.eomcs.oop.ex03;

public class Exam0647 {

  public static class A {
    static int a;
    static void m() {}

    A() {
      System.out.println("생성자");
    }
    
    {
      System.out.println("인스턴스 블록 1111");
    }
    
    static {
      System.out.println("Static{} 11111");
    }
    
    {
      System.out.println("인스턴스 블록 2222");
    }

    static {
      System.out.println("Static{} 22222");
    }
  }

  public static void main(String[] args) throws Exception {
    
    new A();
    System.out.println("-------");
    
    // 클래스는 한 번 로딩되었기 때문에,
    // 스태틱 블록은 다시 실행하지 않는다.
    new A();
    
  }
}





