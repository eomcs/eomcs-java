// Wrapper 클래스 - wrapper 객체 생성
package com.eomcs.corelib.ex02;

public class Exam0230 {
  public static void main(String[] args) {
    
    Integer obj1 = new Integer(100); // Heap에 인스턴스 생성 
    Integer obj2 = new Integer(100); // Heap에 인스턴스 생성 
    System.out.println(obj1 == obj2);
    // 설명:
    // => new 연산자를 통해 Integer 객체를 생성하면 Heap에 인스턴스를 생성한다.
    // => 그래서 같은 값이더라도 다른 인스턴스가 생성된다.
    
    
    Integer obj3 = 100; // Integer.valueOf(100)
    Integer obj4 = 100; // Integer.valueOf(100)
    System.out.println(obj3 == obj4);
    // 설명:
    // => 정수 리터럴을 이용하여 auto-boxing으로 객체를 생성할 경우 constants pool에 생성된다.
    // => 같은 값을 가지는 Integer 객체가 여러 개 존재해야 할 필요가 없다.
    // => 그래서 가능한 이 방법을 사용해야 한다.
    // => 자바에서도 메모리 절약을 위해 이 방법을 사용하도록 유도하기 위해 
    //    Wrapper 클래스의 생성자를 Deprecated로 선언하였다.
    
    
    Integer obj5 = Integer.valueOf(100); 
    Integer obj6 = Integer.valueOf(100);
    System.out.println(obj5 == obj6);
    // Wrapper 클래스의 valueOf()로 객체를 생성하는 경우 constants pool에 생성된다.
    // 즉 auto-boxing과 같다.
    // 
    
    // 다음과 같이 auto-boxing으로 생성된 객체와 valueOf()가 리턴한 객체를 비교해 보자! 
    System.out.println(obj3 == obj5); // true
  }
}






