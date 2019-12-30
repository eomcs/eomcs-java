// 인스턴스 초기화 블록(initializer block) - 생성자와 인스턴스 초기화 블록
package com.eomcs.oop.ex03;

public class Exam0612 {
  
  static class A {
    int a;
    int b;
    int c;

    // 인스턴스 초기화 블록 사용 후
    // => 여러 생성자에 공통으로 존재하는 코드가 있다면 
    //    별도의 블록으로 뽑아내는 것이 소스 코드 관리에 좋다.
    // => 이럴 때 사용하라고 만든 문법이 인스턴스 블록이다.
    // => 다음과 같이 인스턴스 초기화 블록을 사용하여 생성자에 공통으로 들어갈 
    //    코드를 작성하면 된다.
    //
    {
      a = 100;
      System.out.println("Hello!");
    }
    
    A() {
      // 자바 컴파일러는 필드 초기화(variable initializer)나 
      // 인스턴스 초기화 블록(initializer block)이 있으면 
      // 선언된 순서대로 모든 생성자의 첫 부분에 복사한다.
      // 즉 위에 선언된 초기화 블록에 안에 있는 코드가 그대로 다음과 같이 
      // 복사된다.
      // Java Tutorial 문서: Learning the Java Language > Classes and Objects
      // 'The Java compiler copies initializer blocks 
      //  into every constructor. 
      //  Therefore, this approach can be used 
      //  to share a block of code 
      //  between multiple constructors.'
      // 
      //a = 100;
      //System.out.println("Hello!");
      
      System.out.println("A()");
      b = 200;
      c = 300;
    }
    
    A(int b) {
      // 자바 컴파일러는 이 생성자에도 인스턴스 블록의 코드를 그대로 복사한다.
      //a = 100;
      //System.out.println("Hello!");
      
      System.out.println("A(int)");
      this.b = b;
      this.c = 300;
    }
    
    A(int b, int c) {
      // 자바 컴파일러는 이 생성자에도 인스턴스 블록의 코드를 그대로 복사한다.
      //a = 100;
      //System.out.println("Hello!");
      
      System.out.println("A(int, int)");
      this.b = b;
      this.c = c;
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





