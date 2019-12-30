// 인스턴스 초기화 블록(initializer block) - 인스턴스 초기화 블록의 위치
package com.eomcs.oop.ex03;

public class Exam0613 {
  
  static class A {
    int a;
    int b;
    int c;

    // 인스턴스 초기화 블록의 위치
    {
      this.a = 101;
      System.out.println("첫 번째 인스턴스 초기화 블록");
    }
    
    A() {
      // 인스턴스 초기화 블록의 코드를 복사할 때는 선언된 순서대로 복사한다. 
      // 즉 다음과 같다.
      //this.a = 101;
      //System.out.println("첫 번째 인스턴스 초기화 블록");
      //this.a = 102;
      //System.out.println("두 번째 인스턴스 초기화 블록");
      //this.a = 103;
      //System.out.println("세 번째 인스턴스 초기화 블록");
      
      System.out.println("A()");
      b = 200;
      c = 300;
    }
    
    A(int b) {
      // 인스턴스 초기화 블록의 코드를 복사할 때는 선언된 순서대로 복사한다. 
      // 즉 다음과 같다.
      //this.a = 101;
      //System.out.println("첫 번째 인스턴스 초기화 블록");
      //this.a = 102;
      //System.out.println("두 번째 인스턴스 초기화 블록");
      //this.a = 103;
      //System.out.println("세 번째 인스턴스 초기화 블록");
      
      System.out.println("A(int)");
      this.b = b;
      this.c = 300;
    }
    
    {
      this.a = 102;
      System.out.println("두 번째 인스턴스 초기화 블록");
    }
    
    A(int b, int c) {
      // 인스턴스 초기화 블록의 코드를 복사할 때는 선언된 순서대로 복사한다. 
      // 즉 다음과 같다.
      //this.a = 101;
      //System.out.println("첫 번째 인스턴스 초기화 블록");
      //this.a = 102;
      //System.out.println("두 번째 인스턴스 초기화 블록");
      //this.a = 103;
      //System.out.println("세 번째 인스턴스 초기화 블록");
      
      System.out.println("A(int, int)");
      this.b = b;
      this.c = c;
    }
    
    {
      this.a = 103;
      System.out.println("세 번째 인스턴스 초기화 블록");
    }
  }
  public static void main(String[] args) {
    A obj1 = new A();
    System.out.printf("a=%d, b=%d, c=%d\n", 
        obj1.a, obj1.b, obj1.c);
    
    A obj2 = new A(222);
    System.out.printf("a=%d, b=%d, c=%d\n", 
        obj2.a, obj2.b, obj2.c);
    
    A obj3 = new A(222, 333);
    System.out.printf("a=%d, b=%d, c=%d\n", 
        obj3.a, obj3.b, obj3.c);
  }
}





