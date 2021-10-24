// 제네릭(Generic) 문법 정리 - 레퍼런스와 인스턴스 생성 
package com.eomcs.generic.ex02;

import java.util.ArrayList;

public class Exam0211 {
  public static void main(String[] args) {
    // 만약 <타입명> 을 생략한다면, 제네릭이 적용된 객체를 담을 수 있다.
    ArrayList list1; 
    list1 = new ArrayList();
    list1 = new ArrayList<>();
    list1 = new ArrayList<Object>();
    list1 = new ArrayList<String>();
    list1 = new ArrayList<Member>();

    //=> 단  ArrayList에 적용된 제네릭 문법은 무용지물이 된다.
    list1.add(new String());
    list1.add(new Integer(100));
    list1.add(new java.util.Date());
    list1.add(new Member("홍길동", 20));


    // ArrayList가 다루는 타입에 상관없이 ArrayList 레퍼런스를 선언하고 싶다면,
    // list1 처럼 선언하지 말고 다음과 같이 명확하게 <?> 를 붙여라!
    ArrayList<?> list2; 
    list2 = new ArrayList(); // 이렇게 사용하지 말고, 명확히 제네릭의 타입을 지정하라.
    list2 = new ArrayList<>();
    list2 = new ArrayList<Object>();
    list2 = new ArrayList<String>();
    list2 = new ArrayList<Member>();

    //=> 단 이 경우에는 제네릭의 타입이 명확하게 선언되어 있지 않기 때문에
    //   제네릭 검사가 필요한 코드를 컴파일 할 수 없다.
    //    list2.add(new String()); // 컴파일 오류!
    //    list2.add(new Integer(100)); // 컴파일 오류!
    //    list2.add(new java.util.Date()); // 컴파일 오류!
    //    list2.add(new Member("홍길동", 20)); // 컴파일 오류!
  }
}








