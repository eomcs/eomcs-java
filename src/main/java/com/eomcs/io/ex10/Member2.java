package com.eomcs.io.ex10;

// 인스턴스의 값을 바이트 배열로 자동 변환(Serialize)할 수 있도록 허가한다.
// => java.io.Serializable 인터페이스를 구현한다.
// => Serializable 인터페이스는 아무런 메서드가 정의되어 있지 않다.
//    단지 Serialize를 활성화시키는 기능을 수행한다.
// => 이 인터페이스를 구현한 객체만 ObjectInputStream/ObjectOutputStream으로 
//    입출력 할 수 있다.
public class Member2 implements java.io.Serializable {
  String name;
  int age;
  boolean gender; // true(여자), false(남자)

  // Exam02_4.java 를 실행하기 전에 다음 변수를 추가하고, toString()을 개정하라!
  String tel;

  @Override
  public String toString() {
    return "Member2 [name=" + name + ", age=" + age + ", gender=" + gender + ", tel=" + tel + "]";
  }
}
