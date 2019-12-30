// 초기화 블록 - 클래스 블록 = 스태틱 블록
package com.eomcs.oop.ex03;

public class Exam0646 {

  public static class A {
    static int a;
    static void m() {}

    // 스태틱 블록은 클래스가 로딩된 후에 자동으로 순서대로 실행된다.
    static {
      System.out.println("Static{} 11111");
    }

    static {
      System.out.println("Static{} 22222");
    }
  }

  public static void main(String[] args) throws Exception {
    
    // 클래스가 로딩되는 경우
    // 1) 클래스 멤버(변수와 메서드)를 최초로 사용할 때,  
    //      A.a = 100;
    //      A.m();
    // 2) 해당 클래스의 인스턴스를 최소로 생성할 때,
    //      new A();
    // 3) 자바에서 제공하는 도구를 사용하여 클래스를 로딩할 때, 
    //      Class.forName("step08.Exam06_4$A");
    //
    // 클래스가 로딩된 후
    // 1) 클래스 필드가 생성된다.
    // 2) 클래스 블록(스태틱 블록)이 실행된다.
    //
    
    // => 레퍼런스를 선언할 때는 클래스가 로딩되지 않는다.
    //A obj;
    
    // 클래스는 중복 로딩되지 않는다.
    //A.a = 100;
    //A.m();
    //new A();
    //Class.forName("com.eomcs.oop.ex03.Exam0646$A"); 
    System.out.println("------");
    
    // 만약 이전에 클래스가 로딩되었다면, 다시 로딩하지 않는다.
    // => 즉 static 블록을 다시 실행하지 않는다!
    new A(); 
    
  }
}





