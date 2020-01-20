// Wrapper 클래스 - wrapper 객체 생성
package com.eomcs.corelib.ex02;

public class Exam0230 {
  public static void main(String[] args) {

    Integer obj1 = new Integer(100); // Heap에 인스턴스 생성
    Integer obj2 = new Integer(100); // Heap에 인스턴스 생성
    System.out.println(obj1 == obj2); // false
    // 설명:
    // => new 연산자를 통해 Integer 객체를 생성하면 Heap에 인스턴스를 생성한다.
    // => 그래서 같은 값이더라도 다른 인스턴스가 생성된다.

    // auto-box 으로 Wrapper 객체를 생성할 경우,
    Integer obj3 = 100; // Integer.valueOf(100)
    Integer obj4 = 100; // Integer.valueOf(100);
    System.out.println(obj3 == obj4); // true
    // 설명:
    // => 정수 값이 -128 ~ 127 범위일 경우
    // auto-boxing으로 생성된 객체는 내부 보관소(cache)에 저장된다.
    // => 메모리를 효율적으로 사용하기 위해
    // 같은 값을 가지는 Integer 객체가 여러 개 존재하지 않게 한다.
    // => 그래서 가능한 이 방법을 사용해야 한다.
    //

    Integer obj5 = Integer.valueOf(100);
    Integer obj6 = Integer.valueOf(100);
    System.out.println(obj5 == obj6); // true
    // auto-boxing은 Wrapper 클래스의 valueOf()를 호출하는 코드로 처리된다.
    //

    // 다음과 같이 auto-boxing으로 생성된 객체와 valueOf()가 리턴한 객체를 비교해 보자!
    System.out.println(obj3 == obj5); // true

    // 주의!
    // -128 ~ 127 범위를 넘어가는 경우
    // 무조건 새 객체를 만들고 캐시에 보관하지 않는다.
    Integer obj7 = 128;
    Integer obj8 = 128;
    Integer obj9 = 128;
    System.out.println(obj7 == obj8); // false
    System.out.println(obj7 == obj9); // false
    System.out.println(obj8 == obj9); // false
  }
}


